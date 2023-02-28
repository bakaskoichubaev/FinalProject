package arkham.repositories;

import arkham.models.Patient;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PatientRepo {
    List<Patient> findAll(Long id);

    Patient findById(Long patientId);

    void update(Long id,Patient patient);

    void save(Patient patient);

    void deletePatient(Long id);
}
