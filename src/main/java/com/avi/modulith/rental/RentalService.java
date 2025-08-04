package com.avi.modulith.rental;

import com.avi.modulith.events.RentalEvent;
import com.avi.modulith.events.ReturnEvent;
import com.avi.modulith.rental.domain.commands.RentCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
class RentalService {
    private final RentalRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public long rentBook(RentCommand request) {
        RentalEntity rental = repository.save(getRentalEntity(request));
        eventPublisher.publishEvent(new RentalEvent(rental.getBookId(), rental.getRentedAt()));
        return rental.getId();
    }

    private static RentalEntity getRentalEntity(RentCommand request) {
        RentalEntity entity = new RentalEntity();
        entity.setBookId(request.bookId());
        entity.setUserId(request.userId());
        entity.setRentedAt(LocalDateTime.now());
        return entity;
    }

    @Transactional
    public long returnBook(long rentalId) {
        RentalEntity updated = updateRental(rentalId);
        eventPublisher.publishEvent(new ReturnEvent(updated.getBookId(), updated.getReturnedAt()));
        return updated.getBookId();

    }

    private RentalEntity updateRental(long rentalId) {
        RentalEntity rentalEntity = repository.findById(rentalId).orElseThrow();
        rentalEntity.setReturnedAt(LocalDateTime.now());
        return repository.save(rentalEntity);
    }
}
