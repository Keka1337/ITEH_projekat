package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.HunterMapper;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;
    private final TeamService teamService;
    private final HunterMapper hunterMapper;

    @Autowired
    public HunterService(HunterRepository hunterRepository, HunterMapper hunterMapper, TeamService teamService) {
        this.hunterRepository = hunterRepository;
        this.hunterMapper = hunterMapper;
        this.teamService = teamService;
    }

    public HunterDto addHunter(HunterDto hunter) {
        Hunter created = hunterMapper.dtoToEntity(hunter);
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

}
