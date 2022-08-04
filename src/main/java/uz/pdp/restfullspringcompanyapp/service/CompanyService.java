package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.repository.AddressRepository;
import uz.pdp.restfullspringcompanyapp.repository.CompanyRepository;

@Service
public class CompanyService {
    private final CompanyRepository repository;
    private final AddressRepository addressRepository;

    @Autowired
    public CompanyService(CompanyRepository repository, AddressRepository addressRepository) {
        this.repository = repository;
        this.addressRepository = addressRepository;
    }
}
