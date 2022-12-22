package com.telran.bankapp.repository;

import com.telran.bankapp.entity.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByCreationDate(LocalDate creationDate, Sort sort);
    List<Account> findByCityInIgnoreCase(List<String> cities, Sort sort);
    List<Account> findByCreationDateAndCityInIgnoreCase(LocalDate creationDate, List<String> cities, Sort sort);
}
