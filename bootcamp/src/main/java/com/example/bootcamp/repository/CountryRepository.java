package com.example.bootcamp.repository;

import com.example.bootcamp.model.Country;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Primary
@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
