package ru.spring.springmarket.unit.controller.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import ru.spring.springmarket.controllers.ProductController;
import ru.spring.springmarket.test.TestBeanConfiguration;
import ru.spring.springmarket.unit.controller.ControllerUnitTest;


@WebMvcTest(controllers = ProductController.class)
@Import(TestBeanConfiguration.class)
public class ProductControllerTestBeanUnitTest extends ControllerUnitTest {

    private static final String BASE_URL = "/api/v1/product";

    @Autowired
    private MockMvc mvc;

}
