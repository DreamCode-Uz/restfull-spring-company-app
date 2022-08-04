package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.repository.CompanyRepository;
import uz.pdp.restfullspringcompanyapp.repository.DepartmentRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final CompanyRepository companyRepository;

    @Autowired
    public DepartmentService(DepartmentRepository repository, CompanyRepository companyRepository) {
        this.repository = repository;
        this.companyRepository = companyRepository;
    }
}
