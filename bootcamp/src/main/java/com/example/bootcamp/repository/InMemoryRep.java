package com.example.bootcamp.repository;

import com.example.bootcamp.model.Hero;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InMemoryRep implements HeroRepository{
//    String InMe
    public InMemoryRep(@Value("${spring.datasource.url}") String url) {
        System.out.println(url);
    }

    @Override
    public List<Hero> findAll() {
        return null;
    }

    @Override
    public List<Hero> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Hero> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Hero> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Hero entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Hero> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Hero> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Hero> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Hero> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Hero> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Hero> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Hero> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Hero getOne(Long aLong) {
        return null;
    }

    @Override
    public Hero getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Hero> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Hero> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Hero> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Hero> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Hero> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Hero> boolean exists(Example<S> example) {
        return false;
    }
}
