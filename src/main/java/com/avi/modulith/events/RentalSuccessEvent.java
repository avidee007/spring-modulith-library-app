package com.avi.modulith.events;

import java.time.LocalDateTime;

public record RentalSuccessEvent(long bookId, LocalDateTime rentalStartDateTime, LocalDateTime rentalEndDateTime) {
}
