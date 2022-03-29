package ru.spring.springmarket.integration.product;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.spring.springmarket.integration.IntegrationTest;
import ru.spring.springmarket.test.TestBeanConfiguration;

@Import(TestBeanConfiguration.class)
public class ProductControllerTestBeanIntegrationTest extends IntegrationTest {
    private static final String BASE_URL = "/api/v1/products";


    @Test
    @DisplayName("Успешное удаление по идентификатору")
    public void DeleteByIdTestBeanSuccess() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete(BASE_URL + "/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
