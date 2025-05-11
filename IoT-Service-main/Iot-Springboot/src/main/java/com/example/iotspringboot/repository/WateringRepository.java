package com.example.iotspringboot.repository;

import com.example.iotspringboot.model.Watering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WateringRepository extends JpaRepository<Watering, Long>
{

}
