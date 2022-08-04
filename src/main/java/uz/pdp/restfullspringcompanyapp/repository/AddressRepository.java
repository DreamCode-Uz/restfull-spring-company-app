package uz.pdp.restfullspringcompanyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.restfullspringcompanyapp.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
