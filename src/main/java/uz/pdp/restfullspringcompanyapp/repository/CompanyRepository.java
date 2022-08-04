package uz.pdp.restfullspringcompanyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.restfullspringcompanyapp.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
