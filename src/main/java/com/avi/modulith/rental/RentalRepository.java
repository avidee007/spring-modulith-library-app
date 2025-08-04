package com.avi.modulith.rental;

import org.springframework.data.jpa.repository.JpaRepository;

interface RentalRepository extends JpaRepository<RentalEntity, Long> {
}
