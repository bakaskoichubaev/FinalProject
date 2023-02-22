package peaksoft.repository;

import peaksoft.entity.Hospital;

import java.util.List;

public interface HospitralRepository {
    List<Hospital> getAll();
    void save (Hospital hospital);
    Hospital findById(Long id);
    void deleteById(Long id);
}
