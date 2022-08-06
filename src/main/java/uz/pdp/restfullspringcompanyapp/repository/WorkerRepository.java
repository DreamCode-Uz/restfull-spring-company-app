package uz.pdp.restfullspringcompanyapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.restfullspringcompanyapp.entity.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByIdNotAndPhoneNumber(Integer id, String phoneNumber);
}
