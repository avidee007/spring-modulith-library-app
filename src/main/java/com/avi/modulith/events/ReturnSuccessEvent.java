package com.avi.modulith.events;

import java.time.LocalDateTime;

public record ReturnSuccessEvent(long bookId,  LocalDateTime returnDateTime) {
}
