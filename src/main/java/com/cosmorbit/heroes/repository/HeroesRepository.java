package com.cosmorbit.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cosmorbit.heroes.model.Heroes;

@Repository
public interface HeroesRepository extends JpaRepository<Heroes, Long>{

}
