package arkham.repositories.repoImpl;

import arkham.models.Department;
import arkham.models.Doctor;
import arkham.repositories.DoctorRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@RequiredArgsConstructor
@Transactional
public class DoctorRepoImpl implements DoctorRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    public List<Doctor> getAllDoctors(Long id) {
        return entityManager.createQuery("select d from Doctor d join d.hospital h where h.id= :id",
                Doctor.class).setParameter("id",id).getResultList();
    }

    @Override
    public void save(Doctor doctor) {
        entityManager.merge(doctor);
    }

    @Override
    public Doctor findById(Long doctorId) {
        return entityManager.find(Doctor.class, doctorId);
    }

    @Override
    public void update(Long doctorId,Doctor doctor) {
        Doctor doctor1 = entityManager.find(Doctor.class, doctorId);
        doctor1.setFirsName(doctor.getFirsName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setEmail(doctor.getEmail());
        doctor1.setPosition(doctor.getPosition());
        entityManager.merge(doctor1);
    }





    @Override
    public void assignDoctor(Long departmentId, Long doctorId) {
        Department department = entityManager.find(Department.class, departmentId);
        Doctor doctor = entityManager.find(Doctor.class, doctorId);

        try {
            doctor.addDepartment(department);
            department.addDoctor(doctor);
        }catch (NullPointerException e){
            e.printStackTrace();
        }

        entityManager.merge(department);
        entityManager.merge(doctor);
    }




    @Override
    public void deleteDoctor(Long id) {
        Doctor doctor = entityManager.find(Doctor.class, id);
        doctor.setHospital(null);
        doctor.setAppointments(null);
        doctor.setDepartments(null);
        entityManager.remove(doctor);

//        entityManager.createQuery("delete from Doctor where id=:id", Doctor.class)
//                .setParameter("id",id).executeUpdate();

    }
}
