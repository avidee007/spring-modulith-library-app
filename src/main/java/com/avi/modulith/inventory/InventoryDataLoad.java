package com.avi.modulith.inventory;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
@RequiredArgsConstructor
class InventoryDataLoad {

    private final BookRepository repository;

    @Bean
    public CommandLineRunner inventoryLoad() {
        return args -> {
            BookEntity book1 = new BookEntity();
            book1.setName("Ramayana");
            book1.setAuthor("Valmiki");
            book1.setEdition("1st");
            book1.setAvailableCopies(100L);

            BookEntity book2 = new BookEntity();
            book2.setName("Mahabharata");
            book2.setAuthor("Ved Vyas");
            book2.setEdition("1st");
            book2.setAvailableCopies(100L);

            repository.saveAll(List.of(book1, book2));

        };
    }

}
