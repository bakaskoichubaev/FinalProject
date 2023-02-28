package arkham.services.serviceImpl;

import arkham.models.Appointment;
import arkham.models.Hospital;
import arkham.repositories.*;
import arkham.services.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepo appointmentRepo;
    private final DepartmentRepo departmentRepo;
    private final PatientRepo patientRepo;
    private final DoctorRepo doctorRepo;
    private final HospitalRepo hospitalRepo;


    @Override
    public List<Appointment> findAll(Long id) {
        return appointmentRepo.findAll(id);
    }

    @Override
    public Appointment findById(Long appointmentId) {
        return appointmentRepo.findById(appointmentId);
    }

    @Override
    public void update(Long appointmentId, Appointment appointment) {
        appointmentRepo.update(appointmentId, appointment);
    }

    @Override
    public Appointment save(Long hospitalId, Appointment appointment) {

        Hospital hospital= hospitalRepo.getHospitalById(hospitalId);
        Appointment newAppointment=new Appointment();
        newAppointment.setDate(appointment.getDate());
        newAppointment.setId(appointment.getId());

        newAppointment.setDoctor(doctorRepo.findById(appointment.getDoctorId()));
        newAppointment.setDepartment(departmentRepo.findById(appointment.getDepartmentId()));
        newAppointment.setPatient(patientRepo.findById(appointment.getPatientId()));
        hospital.addAppointment(newAppointment);
        return appointmentRepo.save(newAppointment);
    }

    @Override
    public void deleteAppointment(Long hospitalId, Long appointmentId) {
        Appointment appointment = appointmentRepo.findById(appointmentId);
        Hospital hospital = hospitalRepo.getHospitalById(hospitalId);
        appointment.getDoctor().setAppointments(null);
        appointment.getPatient().setAppointments(null);

        hospital.getAppointments().remove(appointment);

        appointmentRepo.deleteAppointment(hospital, appointmentId);
    }


}
