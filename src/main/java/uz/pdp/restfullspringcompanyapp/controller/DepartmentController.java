package uz.pdp.restfullspringcompanyapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullspringcompanyapp.payload.DepartmentDTO;
import uz.pdp.restfullspringcompanyapp.service.DepartmentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(name = "page", defaultValue = "1")Integer page,
                                    @RequestParam(name = "size", defaultValue = "10")Integer size) {
        return service.getAllDepartment(page, size);
    }

    @GetMapping("/id={departmentId}")
    public ResponseEntity<?> getOne(@PathVariable("departmentId") Integer id) {
        return service.getOneDepartment(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody DepartmentDTO departmentDTO) {
        return service.addNewDepartment(departmentDTO);
    }

    @PutMapping("/id={departmentId}")
    public ResponseEntity<?> update(@PathVariable("departmentId") Integer id, @Valid @RequestBody DepartmentDTO departmentDTO) {
        return service.editDepartment(id, departmentDTO);
    }

    @DeleteMapping("/id={departmentId}")
    public ResponseEntity<?> delete(@PathVariable("departmentId") Integer id) {
        return service.deleteDepartment(id);
    }
}
