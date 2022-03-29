package ru.spring.springmarket.unit.controller.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.spring.springmarket.controllers.ProductController;
import ru.spring.springmarket.services.ProductService;
import ru.spring.springmarket.unit.controller.ControllerUnitTest;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerUnitTest extends ControllerUnitTest {

    private static final String BASE_URL = "/api/v1/product";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;
}
