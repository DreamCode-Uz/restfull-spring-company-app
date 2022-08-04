package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.repository.AddressRepository;
import uz.pdp.restfullspringcompanyapp.repository.DepartmentRepository;
import uz.pdp.restfullspringcompanyapp.repository.WorkerRepository;

@Service
public class WorkerService {
    private final WorkerRepository repository;
    private final AddressRepository addressRepository;
    private final DepartmentRepository departmentRepository;

    public WorkerService(WorkerRepository repository, AddressRepository addressRepository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.departmentRepository = departmentRepository;
    }
}
