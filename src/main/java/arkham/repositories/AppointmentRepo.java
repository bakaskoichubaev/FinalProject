package arkham.repositories;

import arkham.models.Appointment;
import arkham.models.Hospital;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepo {
    List<Appointment> findAll(Long id);

    Appointment findById(Long appointmentId);

    void update(Long appointmentId, Appointment appointment);

    Appointment save(Appointment appointment);

    void deleteAppointment(Hospital hospital, Long appointmentId);
}
