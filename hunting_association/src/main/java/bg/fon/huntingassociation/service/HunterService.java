package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Hunter;
import bg.fon.huntingassociation.domain.dtos.HunterDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.HunterMapper;
import bg.fon.huntingassociation.repository.HunterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HunterService {

    private final HunterRepository hunterRepository;
    private final TeamService teamService;
    private final HunterMapper hunterMapper;

    @Autowired
    public HunterService(HunterRepository hunterRepository,HunterMapper hunterMapper,TeamService teamService) {
        this.hunterRepository = hunterRepository;
        this.hunterMapper = hunterMapper;
        this.teamService = teamService;
    }

    public Hunter addHunter(HunterDto hunterDto){
        Hunter hunter = hunterMapper.dtoToEntity(hunterDto);
        return hunterRepository.save(hunter);
    }

    public List<Hunter> findAllHunters() {
        return hunterRepository.findAll();
    }

    public Hunter findHunterById(Long id) throws ObjectNotFoundException {
        return hunterRepository.findById(id).get();
    }

    public void deleteHunter(Long hunterId) {
        Hunter hunter = this.hunterRepository.findById(hunterId).get();
        hunterRepository.deleteById(hunterId);
    }

}
