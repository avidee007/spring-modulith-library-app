package com.avi.modulith.notification;

import com.avi.modulith.events.RentalSuccessEvent;
import com.avi.modulith.events.ReturnSuccessEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @ApplicationModuleListener
    public void notifyUser(RentalSuccessEvent event) {
        log.info("Book Id: {} booked at: {}. Your rental ends at: {}",
                event.bookId(), event.rentalStartDateTime(),
                event.rentalEndDateTime());

    }

    @ApplicationModuleListener
    public void notifyUser(ReturnSuccessEvent event) {
        log.info("Book Id: {} returned at: {} ", event.bookId(), event.returnDateTime());

    }
}
