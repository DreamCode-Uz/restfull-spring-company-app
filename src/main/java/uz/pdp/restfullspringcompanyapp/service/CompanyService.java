package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.entity.Address;
import uz.pdp.restfullspringcompanyapp.entity.Company;
import uz.pdp.restfullspringcompanyapp.payload.CompanyDTO;
import uz.pdp.restfullspringcompanyapp.repository.AddressRepository;
import uz.pdp.restfullspringcompanyapp.repository.CompanyRepository;

import java.util.Optional;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.*;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    private final AddressRepository addressRepository;

    @Autowired
    public CompanyService(CompanyRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }

    /**
     * @param page
     * @param size
     * @return ResponseEntity<>
     */
    public ResponseEntity<Page<Company>> getAllCompany(Integer page, Integer size) {
        return ok(repository.findAll(PageRequest.of(page > 0 ? page - 1 : 0, size > 0 ? size : 10)));
    }

    public ResponseEntity<?> getOneCompany(Integer companyId) {
        Optional<Company> optionalCompany = repository.findById(companyId);
        if (!optionalCompany.isPresent()) return status(NOT_FOUND).body(format("Company id={%s} not found", companyId));
        return ok(optionalCompany.get());
    }

    public ResponseEntity<?> addNewCompany(CompanyDTO dto) {
        Address save = addressRepository.save(new Address(dto.getStreet(), dto.getHomeNumber()));
        return status(CREATED).body(repository.save(new Company(dto.getName(), dto.getDirector(), save)));
    }

    public ResponseEntity<?> editCompany(Integer companyId, CompanyDTO dto) {
        Optional<Company> optionalCompany = repository.findById(companyId);
        if (!optionalCompany.isPresent())
            return status(NOT_FOUND).body(format("Company id=[%s] not found.", companyId));
        Company company = optionalCompany.get();
        company.setName(dto.getName());
        company.setDirector(dto.getDirector());
        Address address = company.getAddress();
        address.setHomeNumber(dto.getHomeNumber());
        address.setStreet(dto.getStreet());
        address = addressRepository.save(address);
        company.setAddress(address);
        return status(CREATED).body(repository.save(company));
    }

    public ResponseEntity<?> deleteCompany(Integer companyId) {
        Optional<Company> optionalCompany = repository.findById(companyId);
        if (!optionalCompany.isPresent()) return status(NOT_FOUND).body(format("Company id=[%s] not found.", companyId));
        addressRepository.delete(optionalCompany.get().getAddress());
        repository.delete(optionalCompany.get());
        return ok("Company successfully deleted.");
    }
}
