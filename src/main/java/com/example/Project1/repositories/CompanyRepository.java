package com.example.Project1.repositories;

import com.example.Project1.models.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Override
    @RestResource(exported = false)
    <S extends Company> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    <S extends Company> S save(S entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Company> entities);

    @Override
    @RestResource(exported = false)
    void delete(Company entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);


}
