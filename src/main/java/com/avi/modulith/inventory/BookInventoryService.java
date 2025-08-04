package com.avi.modulith.inventory;

import com.avi.modulith.events.RentalEvent;
import com.avi.modulith.events.RentalSuccessEvent;
import com.avi.modulith.events.ReturnEvent;
import com.avi.modulith.events.ReturnSuccessEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookInventoryService {

    private final BookRepository repository;
    private final ApplicationEventPublisher publisher;

    @ApplicationModuleListener
    public void handleBookRentedEvent(RentalEvent event) {
        BookEntity book = repository.findById(event.bookId()).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        repository.save(book);
        log.info("Available copies decreased.");
        publisher.publishEvent(new RentalSuccessEvent(event.bookId(), event.rentedAt(),
                event.rentedAt().plusDays(7)));
        log.info("Book rent completed, notifying user");
    }

    @ApplicationModuleListener
    public void handleBookReturnEvent(ReturnEvent event) {
        BookEntity book = repository.findById(event.bookId()).orElseThrow();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        repository.save(book);
        log.info("Available copies increased");
        publisher.publishEvent(new ReturnSuccessEvent(event.bookId(), event.returnDateTime()));
        log.info("Book return completed, notifying user");
    }
}
