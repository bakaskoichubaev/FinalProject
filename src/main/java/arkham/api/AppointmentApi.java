package arkham.api;

import arkham.models.Appointment;
import arkham.services.AppointmentService;
import arkham.services.DepartmentService;
import arkham.services.DoctorService;
import arkham.services.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentApi {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    private final DoctorService doctorService;
    private final DepartmentService departmentService;

    @GetMapping("/{hospitalId}")
    public String getAllAppointments(Model model,
                                     @PathVariable("hospitalId") Long hospitalId){
        model.addAttribute("appointments",appointmentService.findAll(hospitalId));
//        model.addAttribute("hospitalId", hospitalId);
        return "appointment/appointmentPage";
    }

    @GetMapping("/new/{hospitalId}")
    public String addAppointment(@PathVariable Long hospitalId,
                                 Model model){
        model.addAttribute("newAppointment", new Appointment());
        model.addAttribute("patients",patientService.findAll(hospitalId));
        model.addAttribute("departments", departmentService.findAll(hospitalId));
        model.addAttribute("doctors", doctorService.getAllDoctors(hospitalId));
        model.addAttribute(hospitalId);
        return "appointment/saveAppointment";
    }


    @PostMapping("/save/{hospitalId}")
    public String save(@PathVariable("hospitalId") Long hospitalId,
                       @ModelAttribute("newAppointment") Appointment appointment){
        appointmentService.save(hospitalId,appointment);
        return "redirect:/appointments/" + hospitalId;
    }



    @GetMapping("/edit/{appointmentId}")
    public String edit(@PathVariable("appointmentId") Long appointmentId, Model model){
        Appointment appointment = appointmentService.findById(appointmentId);
        model.addAttribute("appointment", appointmentService.findById(appointmentId));
        model.addAttribute("hospitalId", appointment.getDoctor().getHospital().getId());
        return "appointment/update";
    }
    @PutMapping("/{hospitalId}/{appointmentId}/update")
    public String update(@ModelAttribute("appointment")Appointment appointment,
                         @PathVariable("appointmentId")Long appointmentId,
                         @PathVariable("hospitalId")Long hospitalId){
        appointmentService.update(appointmentId, appointment);
        return "redirect:/appointments/" + hospitalId;
    }








    @DeleteMapping("/{hospitalId}/{appointmentId}/delete")
    public String deleteDoctor(@PathVariable("appointmentId")Long appointmentId,
                               @PathVariable("hospitalId")Long hospitalId){
        appointmentService.deleteAppointment(hospitalId, appointmentId);
        return"redirect:/appointments/" + hospitalId;
    }


}
