package com.avi.modulith.inventory;

import com.avi.modulith.events.RentalEvent;
import com.avi.modulith.events.RentalSuccessEvent;
import com.avi.modulith.events.ReturnEvent;
import com.avi.modulith.events.ReturnSuccessEvent;
import org.junit.jupiter.api.Test;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest
@ActiveProfiles("test")
class BookInventoryServiceTest {

    @Test
    void handleBookRentedEvent(Scenario scenario) {
        var rentDate = LocalDateTime.of(2025, 8, 5, 10, 24);
        scenario.publish(new RentalEvent(1L, rentDate))
                .andWaitAtMost(Duration.ofSeconds(2))
                .andWaitForEventOfType(RentalSuccessEvent.class)
                .toArriveAndVerify(evt -> assertThat(evt)
                        .hasFieldOrPropertyWithValue("bookId", 1L)
                        .hasFieldOrPropertyWithValue("rentalStartDateTime", rentDate)
                        .hasFieldOrPropertyWithValue("rentalEndDateTime", rentDate.plusDays(7)));

    }

    @Test
    void handleBookReturnEvent(Scenario scenario) {
        var returnDate = LocalDateTime.of(2025, 8, 5, 10, 24);
        scenario.publish(new ReturnEvent(1L, returnDate))
                .andWaitAtMost(Duration.ofSeconds(2))
                .andWaitForEventOfType(ReturnSuccessEvent.class)
                .toArriveAndVerify(evt -> assertThat(evt)
                        .hasFieldOrPropertyWithValue("bookId", 1L)
                        .hasFieldOrPropertyWithValue("returnDateTime", returnDate));
    }
}