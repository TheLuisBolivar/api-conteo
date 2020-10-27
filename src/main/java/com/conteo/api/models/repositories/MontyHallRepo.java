package com.conteo.api.models.repositories;

import com.conteo.api.models.entities.MontyHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MontyHallRepo extends JpaRepository<MontyHall, Long> {

    MontyHall findByTransactionId(Long transactionId);
}
