package com.example.rockets3withapi;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropellantRepository extends CrudRepository<Propellant,Long> {
}
