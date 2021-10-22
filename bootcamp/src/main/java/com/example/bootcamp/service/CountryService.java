package com.example.bootcamp.service;

import com.example.bootcamp.exception.CountryNotFound;
import com.example.bootcamp.model.Country;
import com.example.bootcamp.repository.CountryRepository;
import com.example.bootcamp.request.CountryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    //Get all countries
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    //Get country by id
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(() -> new CountryNotFound("Country doesn't exists."));
    }

    //Save country
    public Country addCountry(CountryRequest country) {
        Country newCountry = Country.builder().name(country.getName()).build();
        countryRepository.save(newCountry);
        return newCountry;
    }

    //Save updated country
    public Country updateCountry(Long id, String name) {
        Country country = countryRepository.findById(id).orElseThrow(() -> new CountryNotFound("Country doesn't exists."));
        country.setName(name);
        return countryRepository.save(country);
    }

    //Delete country
    public void deleteById(Long countryId) {
        countryRepository.deleteById(countryId);
    }
}