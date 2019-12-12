package com.example.Project1.repositories;

import com.example.Project1.models.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
@RestResource(exported = false)
public interface DepartmentRepository extends CrudRepository<Department,Long> {

    @Override
    @RestResource(exported = false)
    <S extends Department> S save(S entity);

    @Override
    @RestResource(exported = false)
    <S extends Department> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void delete(Department entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Department> entities);


}
