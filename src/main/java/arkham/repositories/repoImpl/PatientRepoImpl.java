package arkham.repositories.repoImpl;


import arkham.models.Patient;
import arkham.repositories.PatientRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class PatientRepoImpl implements PatientRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<Patient> findAll(Long hospitalId) {
        return entityManager.createQuery("select p from Patient p join p.hospital f where f.id = :id",
                Patient.class).setParameter("id",hospitalId).getResultList();
    }

    @Override
    public Patient findById(Long patientId) {
        return entityManager.find(Patient.class, patientId);
    }

    @Override
    public void update(Long id,Patient patient) {
        Patient patient1 = entityManager.find(Patient.class, id);
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setEmail(patient.getEmail());
        patient1.setGender(patient.getGender());
        patient1.setPhoneNumber(patient.getPhoneNumber());
        entityManager.merge(patient1);

    }

    @Override
    public void save(Patient patient) {
        entityManager.merge(patient);

    }

    @Override
    public void deletePatient(Long id) {
        Patient patient = entityManager.find(Patient.class, id);
        patient.setHospital(null);
        patient.setAppointments(null);
        entityManager.remove(patient);

//        entityManager.createQuery("delete from Patient where id=:id",
//                Patient.class).setParameter("id", id).executeUpdate();
    }
}
