package uz.pdp.restfullspringcompanyapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.restfullspringcompanyapp.payload.CompanyDTO;
import uz.pdp.restfullspringcompanyapp.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService service;

    @Autowired
    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false, name = "page", defaultValue = "0") Integer page, @RequestParam(name = "size", defaultValue = "10", required = false) Integer size) {
        return service.getAllCompany(page, size);
    }

    @GetMapping("/id={companyId}")
    public ResponseEntity<?> getOne(@PathVariable("companyId") Integer id) {
        return service.getOneCompany(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody CompanyDTO companyDTO) {
        return service.addNewCompany(companyDTO);
    }

    @PutMapping("/id={companyId}")
    public ResponseEntity<?> update(@PathVariable("companyId") Integer id, @Valid @RequestBody CompanyDTO companyDTO) {
        return service.editCompany(id, companyDTO);
    }

    @DeleteMapping("/id={companyId}")
    public ResponseEntity<?> delete(@PathVariable("companyId") Integer id) {
        return service.deleteCompany(id);
    }
}
