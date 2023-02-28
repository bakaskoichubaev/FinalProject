package arkham.services.serviceImpl;

import arkham.models.Hospital;
import arkham.models.Patient;
import arkham.repositories.HospitalRepo;
import arkham.repositories.PatientRepo;
import arkham.services.PatientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PatientServiceImpl implements PatientService {
    private final PatientRepo patientRepo;
    private final HospitalRepo hospitalRepo;


    public PatientServiceImpl(PatientRepo patientRepo, HospitalRepo hospitalRepo) {
        this.patientRepo = patientRepo;
        this.hospitalRepo = hospitalRepo;
    }


    @Override
    public List<Patient> findAll(Long id) {
        return patientRepo.findAll(id);
    }

    @Override
    public Patient findById(Long patientId) {
        return patientRepo.findById(patientId);
    }

    @Override
    public void update(Long patientId,Patient patient) {
        patientRepo.update(patientId,patient);
    }

    @Override
    public void save(Long hospitalId, Patient patient) {
        Hospital hospitalById = hospitalRepo.getHospitalById(hospitalId);
        hospitalById.addPatient(patient);
        patient.setHospital(hospitalById);
        patientRepo.save(patient);
    }


    @Override
    public void deletePatient(Long id) {
        patientRepo.deletePatient(id);
    }
}
