package com.example.bootcamp.controller;


import com.example.bootcamp.model.Country;
import com.example.bootcamp.request.CountryRequest;
import com.example.bootcamp.request.UpdateNameRQ;
import com.example.bootcamp.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Validated // validar informcao
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @GetMapping("/getCountries")
    public List<Country> getCountry() {
        return countryService.findAll();
    }

    //Get country by id
    @GetMapping("/get-CountriesById/{id}")
    public Country getCountryById(@PathVariable(value = "id") Long id) {
        return countryService.findById(id);
    }
    //Create country
    @PostMapping(value = "/create-Countries")
    public Country createCountry(@RequestBody CountryRequest newCountry) {
        return countryService.addCountry(newCountry);
    }
//    @PostMapping(value = "createCountry", consumes = "application/json", produces = "application/json")
//    public Country createCountry(@RequestBody Country country) {
//        Country newCountry = Country.builder().name(country.getName()).build();
//        countryService.save(newCountry);
//        return newCountry;
//    }
    @PutMapping("/country/{id}/name")
    public ResponseEntity updateName(@PathVariable Long id, @RequestBody UpdateNameRQ request) {
        final Country updatedCountry = countryService.updateCountry(id, request.getName());
        return ResponseEntity.created(URI.create("/country/" + id + "/name")).body("Name updated");
    }

//    @PutMapping(value = "updateCountry/{id}", consumes = "application/json", produces = "application/json")
//    public ResponseEntity updateCountry(Long id, @RequestBody UpdateNameRQ request) {
//        Optional<Country> countryToUpdate = countryService.findById(id);
//        if (countryToUpdate.isPresent()) {
//            countryToUpdate.get().setName(country.getName());
//            countryService.save(countryToUpdate.get());
//            return countryToUpdate.get();
//        } else {
//            ResponseEntity.badRequest().body("Country not found");
//            return null;
//        }
//    }

    @DeleteMapping(value = "/deleteCountry/{id}")
    public void deleteCountry(Long id) {
        countryService.deleteById(id);
    }

}
