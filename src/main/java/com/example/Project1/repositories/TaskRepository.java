package com.example.Project1.repositories;

import com.example.Project1.models.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository <Task, Long> {
    /*
    @Override
    @RestResource(exported = false)
    <S extends Task> Iterable<S> saveAll(Iterable<S> entities);

    @Override
    @RestResource(exported = false)
    <S extends Task> S save(S entity);

    @Override
    @RestResource(exported = false)
    void deleteAll(Iterable<? extends Task> entities);

    @Override
    @RestResource(exported = false)
    void delete(Task entity);

    @Override
    @RestResource(exported = false)
    void deleteAll();

    @Override
    @RestResource(exported = false)
    void deleteById(Long aLong);
 */
}
