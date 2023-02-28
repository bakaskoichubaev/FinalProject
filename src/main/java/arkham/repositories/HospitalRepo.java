package arkham.repositories;

import arkham.models.Hospital;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HospitalRepo {
    List<Hospital> findAll();

    void save(Hospital hospital);

    void deleteHospital(Long hospitalId);

    Hospital getHospitalById(Long hospitalId);


    void update(Hospital updateHospital);
}
