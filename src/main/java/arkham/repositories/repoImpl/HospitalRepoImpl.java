package arkham.repositories.repoImpl;

import arkham.models.Hospital;
import arkham.repositories.DepartmentRepo;
import arkham.repositories.HospitalRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class HospitalRepoImpl implements HospitalRepo {
    @PersistenceContext
    private final EntityManager entityManager;
    private final DepartmentRepo departmentRepo;
    @Override
    public List<Hospital> findAll() {
        return entityManager.createQuery("select h from Hospital h",Hospital.class).getResultList();
    }

    @Override
    public void save(Hospital hospital) {
        entityManager.persist(hospital);
    }

    @Override
    public Hospital getHospitalById(Long hospitalId) {
        return entityManager.find(Hospital.class, hospitalId);
    }

    @Override
    public void update(Hospital updateHospital) {
        entityManager.merge(updateHospital);
    }


    @Override
    public void deleteHospital(Long hospitalId) {
        entityManager.remove(entityManager.find(Hospital.class, hospitalId));
    }
}
