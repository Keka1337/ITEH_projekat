package bg.fon.huntingassociation.service;

import bg.fon.huntingassociation.domain.Venison;
import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import bg.fon.huntingassociation.mappers.VenisonMapper;
import bg.fon.huntingassociation.repository.VenisonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenisonService {

    private final VenisonRepository venisonRepository;
    private final VenisonMapper venisonMapper;


    @Autowired
    public VenisonService(VenisonRepository venisonRepository, VenisonMapper venisonMapper) {
        this.venisonRepository = venisonRepository;
        this.venisonMapper = venisonMapper;
    }

    public VenisonDto addVenison(VenisonDto venisonDto) {
        Venison venison = venisonMapper.dtoToEntity(venisonDto);
        return venisonMapper.entityToDto(venisonRepository.save(venison));
    }
    public List<VenisonDto> findALlVenisons() {
        return venisonRepository.findAll().stream().map(venison -> venisonMapper.entityToDto(venison)).collect(Collectors.toList());
    }
    public VenisonDto findVenisonById(Long id) {
        Venison venison = venisonRepository.findById(id).get();
        return venisonMapper.entityToDto(venison);
    }

    public VenisonDto updateVenison(VenisonDto venison) {
        Venison updated = this.venisonRepository.save(venisonMapper.dtoToEntity(venison));
        return venisonMapper.entityToDto(updated);
    }

    public void deleteVenison(Long id) {
        this.venisonRepository.deleteVenisonById(id);
    }

    public HashMap<String,Object> findAllPageable(int pageNumber, int pageSize, String sortBy, String filter) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize, Sort.by(sortBy));

        Page page;
        if(filter == null)
            page = this.venisonRepository.findAll(pageable);
        else
            page = this.venisonRepository.findByNameContaining(filter,pageable);

        List<Venison> hunters = page.getContent();
        List<VenisonDto> dtos = hunters.stream().map(venison -> venisonMapper.entityToDto(venison)).collect(Collectors.toList());
        Long total = page.getTotalElements();

        HashMap<String,Object> map = new HashMap();
        map.put("total", total);
        map.put("content", dtos);

        return map;
    }
}
