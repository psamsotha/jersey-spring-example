
package com.underdog.jersey.spring.example.repository;

import com.underdog.jersey.spring.example.domain.Customer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Paul Samsotha
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
}
