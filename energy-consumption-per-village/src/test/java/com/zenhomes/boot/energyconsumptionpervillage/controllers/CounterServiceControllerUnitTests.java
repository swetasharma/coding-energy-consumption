package com.zenhomes.boot.energyconsumptionpervillage.controllers;

import com.zenhomes.boot.energyconsumptionpervillage.dto.CounterRegister;
import com.zenhomes.boot.energyconsumptionpervillage.services.CounterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test counter service controller
 */
@WebMvcTest(CounterController.class)
public class CounterServiceControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Since we are only focused on the controller code, we need to mock the service Layer for our unit tests.
     */
    @MockBean
    private CounterService counterService;

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCounterWithZeroAmount() throws Exception {
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.counter_id = 1;
        counterRegister.amount = 0;

        given(counterService.save(counterRegister)).willThrow(IllegalArgumentException.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateCounterWithNegativeAmount() throws Exception {
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.counter_id = 1;
        counterRegister.amount = -1000.00;
        given(counterService.save(counterRegister)).willThrow(IllegalArgumentException.class);
    }

    @Test
    public void testSuccessPostCounterCallback() throws Exception{
        CounterRegister counterRegister = new CounterRegister();
        counterRegister.counter_id = 1;
        counterRegister.amount = 1000.00;

        given(counterService.save(counterRegister)).willReturn(counterRegister);

        mockMvc.perform(post("/counter_callback")
                .accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk());
    }

}
