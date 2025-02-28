package fh_campuswien.banking_applications.accounts.repository;

import fh_campuswien.banking_applications.accounts.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmailOrMobileNumber(String email, String mobileNumber);
}
