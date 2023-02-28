package arkham.repositories.repoImpl;

import arkham.models.Department;
import arkham.repositories.DepartmentRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;


@RequiredArgsConstructor
@Repository
@Transactional
public class DepartmentRepoImpl implements DepartmentRepo {
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public List<Department> findAll(Long hospitalId) {
        return entityManager.createQuery("SELECT d FROM Department d JOIN d.hospital h WHERE h.id = :id",
                Department.class).setParameter("id",hospitalId).getResultList();
    }


    @Override
    @Transactional
    public void save(Department department) {
        entityManager.merge(department);
    }

    @Override
    public Department findById(Long departmentId) {
        return entityManager.find(Department.class, departmentId);
    }

    @Override
    public void update(Long departmentId, Department department) {
        try {
            Department department1 = entityManager.find(Department.class, departmentId);
            department1.setName(department.getName());
            entityManager.merge(department);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id, Long hospitalId) {
        Department department = entityManager.find(Department.class, id);
        department.setHospital(null);
        department.setDoctors(null);
        entityManager.remove(department);

//        entityManager.createQuery("delete from Department where id=:id",
//                Department.class).setParameter("id",id).executeUpdate();

//        List<Hospital> hospitals = entityManager.createQuery("select h from Hospital h where id=:id",
//                Hospital.class).setParameter("id", hospitalId).getResultList();
//
//        hospitals.forEach(d -> d.getDepartments().removeIf(s -> s.getId().equals(id)));

    }













}
