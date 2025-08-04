package com.avi.modulith.events;

import java.time.LocalDateTime;

public record ReturnEvent(long bookId, LocalDateTime returnDateTime) {
}
