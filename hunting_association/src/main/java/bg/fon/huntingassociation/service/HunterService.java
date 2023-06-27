package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.Team;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.HunterMapper;
import bg.fon.huntingassociation.repository.HunterRepository;
import bg.fon.huntingassociation.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;
    private final TeamRepository teamRepository;
    private final HunterMapper hunterMapper;
    Logger LOG = LoggerFactory.getLogger(HunterService.class.getName());

    @Autowired
    public HunterService(HunterRepository hunterRepository, TeamRepository teamRepository, HunterMapper hunterMapper) {
        this.hunterRepository = hunterRepository;
        this.teamRepository = teamRepository;
        this.hunterMapper = hunterMapper;
    }

    public HunterDto addHunter(HunterDto hunter) throws Exception {
        if(hunterRepository.existsByJmbg(hunter.getJmbg()) || hunterRepository.existsByLicenceNum(hunter.getLicenceNum()))
            throw new Exception("Hunter with provided jmbg or licence number already exists!");
        Hunter created = hunterRepository.save(hunterMapper.dtoToEntity(hunter));
        Team team = teamRepository.findById(created.getTeam().getId()).get();
        created.setTeam(team);
        return hunterMapper.entityToDto(created);
    }

    public List<HunterDto> findAllHunters() {
        return hunterRepository.findAll().stream().map(hunter -> hunterMapper.entityToDto(hunter)).collect(Collectors.toList());
    }

    public HunterDto findHunterById(Long id) throws ObjectNotFoundException {
        Hunter hunter = hunterRepository.findById(id).get();
        return hunterMapper.entityToDto(hunter);
    }

    public void deleteHunter(Long hunterId) {
        Hunter hunter = this.hunterRepository.findById(hunterId).get();
        hunterRepository.deleteById(hunterId);
    }

    public HashMap<String, Object> findAllPageable(int pageNumber, int pageSize, String sortBy, String filter) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page page;
        if(filter ==null)
            page = this.hunterRepository.findAll(pageable);
        else
            page = this.hunterRepository.findByNameContaining(filter, pageable);

        List<Hunter> hunters = page.getContent();
        List<HunterDto> dtos = hunters.stream().map(hunter -> hunterMapper.entityToDto(hunter)).collect(Collectors.toList());
        Long total = page.getTotalElements();

        HashMap<String, Object> map = new HashMap();
        map.put("total", total);
        map.put("content", dtos);
        return map;
    }

    public HunterDto editHunter(HunterDto hunterDto) {
        Hunter hunter = this.hunterRepository.save(hunterMapper.dtoToEntity(hunterDto));
        return hunterMapper.entityToDto(hunter);
    }
}
