package uz.pdp.restfullspringcompanyapp.service;

import org.springframework.stereotype.Service;
import uz.pdp.restfullspringcompanyapp.repository.AddressRepository;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
