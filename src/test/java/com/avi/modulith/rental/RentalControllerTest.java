package com.avi.modulith.rental;

import com.avi.modulith.rental.domain.commands.RentCommand;
import com.avi.modulith.rental.domain.commands.ReturnCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class RentalControllerTest {

    @MockitoBean
    private RentalService rentalService;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void bookRental() throws Exception {

        var rentalCmd = new RentCommand(1L, 100L);
        Mockito.when(rentalService.rentBook(rentalCmd)).thenReturn(1L);
        mockMvc.perform(
                        post("/api/books/rent")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(rentApiPayload()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rentalId").value(1L));
    }

    @Test
    void bookReturn() throws Exception {
        var returnCommand = new ReturnCommand(1L);
        Mockito.when(rentalService.returnBook(returnCommand.rentalId())).thenReturn(1L);
        mockMvc.perform(
                        post("/api/books/return")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(returnApiPayload()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.returnedBookId").value(1L));
    }

    private String rentApiPayload() {
        return """
                {
                  "bookId": 1,
                  "userId": 100
                }
                """;
    }

    private String returnApiPayload(){
        return """
                {
                  "rentalId": 1
                }
                """;
    }
}