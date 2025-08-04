package com.avi.modulith.rental;

import com.avi.modulith.rental.domain.commands.RentCommand;
import com.avi.modulith.rental.domain.commands.ReturnCommand;
import com.avi.modulith.rental.domain.dto.BookRentalResponse;
import com.avi.modulith.rental.domain.dto.BookReturnResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;


    @PostMapping("rent")
    public ResponseEntity<BookRentalResponse> bookRental(@RequestBody RentCommand rentCommand) {
        long rentalId = rentalService.rentBook(rentCommand);
        return ResponseEntity.ok(new BookRentalResponse(rentalId));
    }

    @PostMapping("return")
    public ResponseEntity<BookReturnResponse> bookReturn(@RequestBody ReturnCommand returnCommand) {
        long returnedBookId = rentalService.returnBook(returnCommand.rentalId());
        return ResponseEntity.ok(new BookReturnResponse(returnedBookId));
    }
}
