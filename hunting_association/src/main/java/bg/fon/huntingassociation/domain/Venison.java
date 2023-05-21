package bg.fon.huntingassociation.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Venison {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "venison_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "venison")
    private List<Appointment> appointments;

    public Venison() {
    }

    public Venison(Long id, String name, List<Appointment> appointments) {
        this.id = id;
        this.name = name;
        this.appointments = appointments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Appointment> getAppoitments() {
        return appointments;
    }

    public void setAppoitments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String toString() {
        return "Venison{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", appointments=" + appointments +
                '}';
    }
}
