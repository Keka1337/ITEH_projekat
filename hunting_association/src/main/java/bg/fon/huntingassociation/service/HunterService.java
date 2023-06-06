package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.HunterMapper;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;
    private final HunterMapper hunterMapper;
    Logger LOG = LoggerFactory.getLogger(HunterService.class.getName());

    @Autowired
    public HunterService(HunterRepository hunterRepository, HunterMapper hunterMapper) {
        this.hunterRepository = hunterRepository;
        this.hunterMapper = hunterMapper;
    }

    public HunterDto addHunter(HunterDto hunter) {
        LOG.info("Hunter: " + hunter.getTeam().getId());
        Hunter created = hunterMapper.dtoToEntity(hunter);
        LOG.info("Hunter: " + created);
        return hunterMapper.entityToDto(hunterRepository.save(created));
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

    public List<HunterDto> findAllPageable(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        List<Hunter> hunters = this.hunterRepository.findAll(pageable).getContent();
        return hunters.stream().map(hunter -> hunterMapper.entityToDto(hunter)).collect(Collectors.toList());
    }
}
