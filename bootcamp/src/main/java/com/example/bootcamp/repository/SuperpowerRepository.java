package com.example.bootcamp.repository;

import com.example.bootcamp.model.Hero;
import com.example.bootcamp.model.Superpower;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Primary
@Repository
public interface SuperpowerRepository extends JpaRepository<Superpower, Long> {
}
