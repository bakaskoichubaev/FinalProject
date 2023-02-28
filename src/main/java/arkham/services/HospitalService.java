package arkham.services;

import arkham.models.Hospital;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface HospitalService {
    List<Hospital> findAll();

    void save(Hospital hospital);

    void deleteHospital(Long hospitalId);

    Hospital getHospitalById(Long hospitalId);

    void update(Hospital hospital);

}
