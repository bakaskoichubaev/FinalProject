package arkham.services.serviceImpl;

import arkham.models.Hospital;
import arkham.repositories.HospitalRepo;
import arkham.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class HospitalServiceImpl implements HospitalService {

    private final HospitalRepo hospitalRepo;
    @Override
    public List<Hospital> findAll() {
        return hospitalRepo.findAll();
    }

    @Override
    public void save(Hospital hospital) {
        hospitalRepo.save(hospital);
    }

    @Override
    public Hospital getHospitalById(Long hospitalId) {
        return hospitalRepo.getHospitalById(hospitalId);
    }

    @Override
    public void update( Hospital updateHospital) {
        hospitalRepo.update(updateHospital);
    }


    @Override
    public void deleteHospital(Long hospitalId) {
        hospitalRepo.deleteHospital(hospitalId);
    }
}
