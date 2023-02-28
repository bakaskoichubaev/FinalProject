package arkham.services.serviceImpl;

import arkham.models.Doctor;
import arkham.models.Hospital;
import arkham.repositories.DoctorRepo;
import arkham.repositories.HospitalRepo;
import arkham.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;

    @Autowired
    public DoctorServiceImpl(DoctorRepo doctorRepo, HospitalRepo hospitalRepo) {
        this.doctorRepo = doctorRepo;
        this.hospitalRepo = hospitalRepo;
    }



    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return doctorRepo.getAllDoctors(id);
    }

    @Override
    public void save(Long hospitalId, Doctor doctor) {
        Hospital hospital = hospitalRepo.getHospitalById(hospitalId);
        hospital.addDoctor(doctor);
        doctor.setHospital(hospital);
        doctorRepo.save(doctor);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return doctorRepo.findById(doctorId);
    }

    @Override
    public void update(Long doctorId,Doctor doctor) {
        doctorRepo.update(doctorId, doctor);
    }


    //1
    @Override
    public void assignDoctor(Long departmentId, Long doctorId) {
        doctorRepo.assignDoctor(departmentId, doctorId);
    }

    @Override
    public void deleteDoctor(Long id) {
        doctorRepo.deleteDoctor(id);
    }
}
