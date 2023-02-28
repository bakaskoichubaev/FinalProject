package arkham.services.serviceImpl;

import arkham.exeptions.BudRequestExeption;
import arkham.models.Department;
import arkham.models.Hospital;
import arkham.repositories.DepartmentRepo;
import arkham.repositories.DoctorRepo;
import arkham.repositories.HospitalRepo;
import arkham.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service

@Transactional
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;
    private final HospitalRepo hospitalRepo;
    private final DoctorRepo doctorRepo;


    @Autowired
    public DepartmentServiceImpl(DepartmentRepo departmentRepo, HospitalRepo hospitalRepo, DoctorRepo doctorRepo) {
        this.departmentRepo = departmentRepo;
        this.hospitalRepo = hospitalRepo;
        this.doctorRepo = doctorRepo;
    }

    @Override
    public List<Department> findAll(Long hospitalId) {
        return departmentRepo.findAll(hospitalId);
    }



    @Override
    public void save(Long hospitalId, Department department) throws BudRequestExeption {
        Hospital hospital = hospitalRepo.getHospitalById(hospitalId);
        for (Department dep : departmentRepo.findAll(hospital.getId())) {
            if (dep.getName().equals(department.getName())) {
                throw new BudRequestExeption();
            }
        }
        hospital.addDepartment(department);
        department.setHospital(hospital);
        departmentRepo.save(department);
    }


    @Override
    public Department findById(Long departmentId) {
        return departmentRepo.findById(departmentId);
    }

    @Override
    public void update(Long departmentId, Department department) {
        departmentRepo.update(departmentId,department);
    }

    @Override
    public void deletePatient(Long id, Long hospitalId) {
        departmentRepo.delete(id, hospitalId);
    }






}
