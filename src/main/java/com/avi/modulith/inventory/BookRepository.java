package com.avi.modulith.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

interface BookRepository extends JpaRepository<BookEntity, Long> {
}
