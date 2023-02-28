package arkham.api;

import arkham.models.Department;
import arkham.models.Doctor;
import arkham.services.DepartmentService;
import arkham.services.DoctorService;
import arkham.services.HospitalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Controller
@RequestMapping("/departments")
@RequiredArgsConstructor
public class DepartmentApi {

    private final DepartmentService departmentService;
    private final HospitalService hospitalService;
    private final DoctorService doctorService;


    @GetMapping("/{id}")
    public String getAllDepartments(Model model,
                                    @PathVariable("id") Long id ,
                                    @ModelAttribute("doctor") Doctor doctor ){
        model.addAttribute("departments", departmentService.findAll(id));
        model.addAttribute("doctors", doctorService.getAllDoctors(id));
        model.addAttribute("hospitalId",id);
        return "department/departments";
    }






    @PostMapping("/save/{hospitalId}")
    public String save(@ModelAttribute("newDepartment") Department department,
                       @PathVariable("hospitalId") Long hospitalId) {
        departmentService.save(hospitalId, department);
        return "redirect:/departments/"+hospitalId;
    }

    @GetMapping("/new/{id}")
    public String create(Model model,
                         @PathVariable("id")Long id) {
        model.addAttribute("newDepartment", new Department());
        model.addAttribute("hospitalId", id);
        return "/department/saveDepartment";
    }


//1
    @PostMapping("{hospitalId}/{departmentId}/assignDoctor")
    private String assignDepartment(@PathVariable("hospitalId") Long hospitalId,
                               @PathVariable("departmentId") Long departmentId,
                               @ModelAttribute("doctor") Doctor doctor)
            throws IOException {
        System.out.println(doctor);
        Long id = doctor.getId();
        doctorService.assignDoctor(departmentId, id);
        return "redirect:/departments/" + hospitalId;
    }










    @GetMapping("/edit/{departmentId}")
    public String edit(@PathVariable("departmentId")Long departmentId,
                       Model model){
        Department department = departmentService.findById(departmentId);
        model.addAttribute("department", department);
        model.addAttribute("hospitalId",department.getHospital().getId());
        return "/department/update";
    }

    @PostMapping("/{hospitalId}/{departmentId}/update")
    public String update(@ModelAttribute("department") Department department,
                         @PathVariable("departmentId") Long departmentId,
                         @PathVariable("hospitalId") Long hospitalId){
        departmentService.update(departmentId,department);
        return "redirect:/departments/" + hospitalId;
    }




    @GetMapping("/{hospitalId}/{departmentId}/delete")
    public String deletePatient(@PathVariable("departmentId")Long id,
                                @PathVariable("hospitalId")Long hospitalId){
        departmentService.deletePatient(id, hospitalId);
        return"redirect:/departments/" + hospitalId;
    }


}
