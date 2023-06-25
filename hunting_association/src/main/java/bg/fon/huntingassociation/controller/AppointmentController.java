package bg.fon.huntingassociation.controller;

import bg.fon.huntingassociation.domain.Appointment;
import bg.fon.huntingassociation.service.AppointmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;



@Transactional
@RestController
@RequestMapping("api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/add")
    public ResponseEntity<?> addAppointment(@RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(appointmentService.createAppointment(appointment), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAppointments() {
        return new ResponseEntity<>(appointmentService.findAllAppointments(), HttpStatus.OK);
    }

    @GetMapping("/all-pageable")
    public ResponseEntity<?> getAllAppointmentsPageable(@RequestParam("pageNumber") int pageNumber,
                                                        @RequestParam("pageSize") int pageSize,
                                                        @RequestParam(defaultValue = "date") String sortBy) {
        return new ResponseEntity<>(appointmentService.findAllPageable(pageNumber,pageSize, sortBy), HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(appointmentService.findAppointmentById(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable("id") Long id) {
        try {
            this.appointmentService.deleteAppoitment(id);
            return ResponseEntity.ok("Appointment has been successfully removed!");
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}