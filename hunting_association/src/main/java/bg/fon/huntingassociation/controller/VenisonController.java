package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.dtos.VenisonDto;
import bg.fon.huntingassociation.service.VenisonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Transactional
@RestController
@RequestMapping("/venison")
public class VenisonController {

    private final VenisonService venisonService;
    private final Logger LOG = LoggerFactory.getLogger(VenisonController.class);


    public VenisonController(VenisonService venisonService) {
        this.venisonService = venisonService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVenison(@RequestBody VenisonDto venisonDto) {
        try {
            return new ResponseEntity<>(venisonService.addVenison(venisonDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllVenisons() {
        return new ResponseEntity<>(venisonService.findALlVenisons(), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findVenisonById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(venisonService.findVenisonById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateVenison(@RequestBody VenisonDto venison) {
        try {
            return new ResponseEntity<>(venisonService.updateVenison(venison), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVenison(@PathVariable("id") Long id) {
        try {
            this.venisonService.deleteVenison(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
