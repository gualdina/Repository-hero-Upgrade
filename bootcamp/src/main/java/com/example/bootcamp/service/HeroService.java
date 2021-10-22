package com.example.bootcamp.service;

import com.example.bootcamp.model.Hero;
import com.example.bootcamp.repository.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HeroService {
    private final HeroRepository heroRepository;

    public HeroService(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    //Get all heroes
    public List<Hero> findAll() {
        return heroRepository.findAll();
    }
}