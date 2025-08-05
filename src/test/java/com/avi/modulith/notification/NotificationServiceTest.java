package com.avi.modulith.notification;

import com.avi.modulith.events.RentalSuccessEvent;
import com.avi.modulith.events.ReturnSuccessEvent;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ActiveProfiles("test")
class NotificationServiceTest {

    @Test
    void testNotifyUserWhenRentalSuccessEventReceived(Scenario scenario) {
        var rentalStart = LocalDateTime.of(2025, 8, 5, 10, 24);
        var rentalEnd = rentalStart.plusDays(7);
        scenario.publish(new RentalSuccessEvent(1L, rentalStart, rentalEnd))
                .andWaitForEventOfType(RentalSuccessEvent.class)
                .matching(event -> event.bookId() == 1L)
                .toArriveAndVerify(evt -> assertThat(evt)
                        .hasFieldOrPropertyWithValue("bookId", 1L)
                        .hasFieldOrPropertyWithValue("rentalStartDateTime", rentalStart)
                        .hasFieldOrPropertyWithValue("rentalEndDateTime", rentalEnd)

                );

    }

    @Test
    void testNotifyUserWhenReturnSuccessEventReceived(Scenario scenario) {
        var returnDate = LocalDateTime.of(2025, 8, 5, 10, 24);
        scenario.publish(new ReturnSuccessEvent(1L, returnDate))
                .andWaitForEventOfType(ReturnSuccessEvent.class)
                .matching(event -> event.bookId() == 1L)
                .toArriveAndVerify(evt -> assertThat(evt)
                        .hasFieldOrPropertyWithValue("bookId", 1L)
                        .hasFieldOrPropertyWithValue("returnDateTime", returnDate)
                );

    }

}