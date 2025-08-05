package com.avi.modulith.rental;

import com.avi.modulith.events.RentalEvent;
import com.avi.modulith.events.ReturnEvent;
import com.avi.modulith.rental.domain.commands.RentCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

@ApplicationModuleTest(extraIncludes = "inventory")
@ActiveProfiles("test")
class RentalServiceTest {

    @Autowired
    private RentalService rentalService;

    @Test
    void rentBook(Scenario scenario) {
        scenario.stimulate(() -> rentalService.rentBook(new RentCommand(1L, 100L)))
                .andWaitAtMost(Duration.ofSeconds(1L))
                .andWaitForEventOfType(RentalEvent.class)
                .toArriveAndVerify(rentalEvent ->
                        assertThat(rentalEvent)
                                .hasFieldOrPropertyWithValue("bookId", 1L)
                                .hasFieldOrProperty("rentedAt"));

    }

    @Test
    void returnBook(Scenario scenario) {
        scenario.stimulate(() -> rentalService.returnBook(1L))
                .andWaitAtMost(Duration.ofSeconds(1L))
                .andWaitForEventOfType(ReturnEvent.class)
                .toArriveAndVerify(returnEvent ->
                        assertThat(returnEvent)
                                .hasFieldOrPropertyWithValue("bookId", 1L)
                                .hasFieldOrProperty("returnDateTime"));
    }
}