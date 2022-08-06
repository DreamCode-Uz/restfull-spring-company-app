package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.entity.Address;
import uz.pdp.restfullspringcompanyapp.entity.Department;
import uz.pdp.restfullspringcompanyapp.entity.Worker;
import uz.pdp.restfullspringcompanyapp.exceptions.ConflictException;
import uz.pdp.restfullspringcompanyapp.exceptions.RecordNotFoundException;
import uz.pdp.restfullspringcompanyapp.payload.WorkerDTO;
import uz.pdp.restfullspringcompanyapp.repository.AddressRepository;
import uz.pdp.restfullspringcompanyapp.repository.DepartmentRepository;
import uz.pdp.restfullspringcompanyapp.repository.WorkerRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.*;

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

    public ResponseEntity<?> getAllWorkers(Integer page, Integer size) {
        return ok(repository.findAll(PageRequest.of(page > 0 ? page - 1 : page, size > 0 ? size : 10)));
    }

    public ResponseEntity<?> getOneWorker(Integer workerId) {
        Optional<Worker> optionalWorker = repository.findById(workerId);
        if (!optionalWorker.isPresent()) throw new RecordNotFoundException(format("Worker id=%s not found", workerId));
        return ok(optionalWorker.get());
    }

    public ResponseEntity<?> addNewWorker(WorkerDTO dto) {
        if (repository.existsByPhoneNumber(dto.getPhoneNumber())) throw new ConflictException("Worker phone number conflicted");
        Set<Department> departments = checkDepartment(dto.getDepartmentsId());
        if (departments.isEmpty()) throw new RecordNotFoundException("Departments not found");
        Address savedAddress = addressRepository.save(new Address(dto.getStreet(), dto.getHomeNumber()));
        Worker worker = new Worker(dto.getName(), dto.getPhoneNumber(), savedAddress, departments);
        return status(HttpStatus.CREATED).body(repository.save(worker));
    }

    public ResponseEntity<?> editWorker(Integer workerId, WorkerDTO dto) {
        Optional<Worker> optionalWorker = repository.findById(workerId);
        if (!optionalWorker.isPresent()) throw new RecordNotFoundException("Worker not found");
        if (repository.existsByIdNotAndPhoneNumber(workerId, dto.getPhoneNumber()))
            throw new ConflictException("Worker phone number conflicted");
        Set<Department> departments = checkDepartment(dto.getDepartmentsId());
        if (departments.isEmpty()) throw new RecordNotFoundException("Departments not found");
        Worker worker = optionalWorker.get();
        Address address = worker.getAddress();
        address.setStreet(dto.getStreet());
        address.setHomeNumber(dto.getHomeNumber());
        address = addressRepository.save(address);
        worker.setName(dto.getName());
        worker.setPhoneNumber(dto.getPhoneNumber());
        worker.setDepartment(departments);
        worker.setAddress(address);
        return ok(repository.save(worker));
    }

    public ResponseEntity<?> deleteWorker(Integer workerId) {
        Optional<Worker> optionalWorker = repository.findById(workerId);
        if (!optionalWorker.isPresent()) throw new RecordNotFoundException("Worker not found");
        Address address = optionalWorker.get().getAddress();
        repository.delete(optionalWorker.get());
        addressRepository.delete(address);
        return ok("Department successfully deleted");
    }

    //    ACTION
    public Set<Department> checkDepartment(Set<Integer> departmentsId) {
        Set<Department> departments = new HashSet<>();
        departmentsId.stream().iterator().forEachRemaining(departmentId -> {
            Optional<Department> optionalDepartment = departmentRepository.findById(departmentId);
            optionalDepartment.ifPresent(departments::add);
        });
        return departments;
    }
}
