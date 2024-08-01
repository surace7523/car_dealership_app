package com.udacity.pricing.tests;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class) // Use SpringExtension to integrate Spring with JUnit Jupiter
@WebMvcTest(PricingController.class) // Use WebMvcTest to test PricingController class
public class PricingControllerUnitTest {

    @Autowired
    private MockMvc mockMvc; // Use MockMvc to quickly test MVC Controller without an HTTP server

    @Mock
    PricingService priceService;

    @Test
    public void getPriceOfAVehicle() throws Exception {
        this.mockMvc.perform(get("/services/price?vehicleId=1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Set expectations on HTTP response status received from Controller class

        // Verify the number of times (1 time) a mock method has been called
        Mockito.verify(this.priceService, times(1)).getPrice(1L);
    }
}
