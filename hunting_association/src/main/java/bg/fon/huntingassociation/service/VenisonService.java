package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import bg.fon.huntingassociation.exception.ObjectNotFoundException;
import bg.fon.huntingassociation.mappers.VenisonMapper;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;
    private final VenisonMapper venisonMapper;


    @Autowired
    public VenisonService(VenisonRepository venisonRepository, VenisonMapper venisonMapper) {
        this.venisonRepository = venisonRepository;
        this.venisonMapper = venisonMapper;
    }

    public Venison addVenison(VenisonDto venisonDto) {
        Venison venison = venisonMapper.dtoToEntity(venisonDto);
        return venisonRepository.save(venison);
    }
    public List<Venison> findALlVenisons() {
        return venisonRepository.findAll();
    }
    public Venison findVenisonById(Long id) {
        return venisonRepository.findById(id).get();
    }

    public Venison updateVenison(Venison venison) {
        return this.venisonRepository.save(venison);
    }

    public void deleteVenison(Long id) {
        this.venisonRepository.deleteVenisonById(id);
    }

}
