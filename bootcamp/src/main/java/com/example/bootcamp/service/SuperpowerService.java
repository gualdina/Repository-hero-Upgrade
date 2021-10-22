package com.example.bootcamp.service;

import com.example.bootcamp.model.Superpower;
import com.example.bootcamp.repository.SuperpowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuperpowerService {
    private final SuperpowerRepository superpowerRepository;

    public SuperpowerService(SuperpowerRepository superPowerRepository) {
        this.superpowerRepository = superPowerRepository;
    }

    //Get all superpowers
    public List<Superpower> findAll() {
        return superpowerRepository.findAll();
    }

}