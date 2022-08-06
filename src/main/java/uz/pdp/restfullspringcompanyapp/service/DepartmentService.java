package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.entity.Company;
import uz.pdp.restfullspringcompanyapp.entity.Department;
import uz.pdp.restfullspringcompanyapp.exceptions.RecordNotFoundException;
import uz.pdp.restfullspringcompanyapp.payload.DepartmentDTO;
import uz.pdp.restfullspringcompanyapp.repository.CompanyRepository;
import uz.pdp.restfullspringcompanyapp.repository.DepartmentRepository;

import java.util.Optional;

import static java.lang.String.*;
import static org.springframework.http.ResponseEntity.*;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final CompanyRepository companyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }

    public ResponseEntity<?> getAllDepartment(Integer page, Integer size) {
        return ok(repository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10)));
    }

    public ResponseEntity<?> getOneDepartment(Integer departmentId) {
        Optional<Department> optionalDepartment = repository.findById(departmentId);
        if (!optionalDepartment.isPresent())
            throw new RecordNotFoundException(format("Department id=%s not found", departmentId));
        return ok(optionalDepartment.get());
    }

    public ResponseEntity<?> addNewDepartment(DepartmentDTO dto) {
        Optional<Company> optionalCompany = companyRepository.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent())
            throw new RecordNotFoundException(format("Company id=%s not found", dto.getCompanyId()));
        return status(HttpStatus.CREATED).body(repository.save(new Department(dto.getName(), optionalCompany.get())));
    }

    public ResponseEntity<?> editDepartment(Integer id, DepartmentDTO dto) {
        Optional<Department> optionalDepartment = repository.findById(id);
        if (!optionalDepartment.isPresent())
            throw new RecordNotFoundException(format("Department id=%s not found", id));
        Optional<Company> optionalCompany = companyRepository.findById(dto.getCompanyId());
        if (!optionalCompany.isPresent()) throw new RecordNotFoundException("Company id=% not found");
        Department department = optionalDepartment.get();
        department.setName(dto.getName());
        department.setCompany(optionalCompany.get());
        return ok(repository.save(department));
    }

    public ResponseEntity<?> deleteDepartment(Integer departmentId) {
        Optional<Department> optionalDepartment = repository.findById(departmentId);
        if (!optionalDepartment.isPresent()) throw new RecordNotFoundException(format("Department id=%s not found", departmentId));
        repository.delete(optionalDepartment.get());
        return ok("Department successfully deleted");
    }
}
