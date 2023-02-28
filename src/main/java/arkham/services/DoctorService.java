package arkham.services;

import arkham.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService {
    List<Doctor> getAllDoctors(Long id);

    void save(Long hospitalId, Doctor doctor);

    Doctor findById(Long doctorId);

    void update(Long doctorId,Doctor doctor);

    //1
    void assignDoctor(Long departmentId, Long doctorId);

    void deleteDoctor(Long id);
}
