package com.avi.modulith.events;

import java.time.LocalDateTime;

public record RentalEvent(long bookId, LocalDateTime rentedAt) {
}
