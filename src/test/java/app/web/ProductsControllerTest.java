package app.web;

import app.repository.ProductRepository;
import app.service.Impl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ProductsController.class)
class ProductsControllerTest {

    @Autowired
    private MockMvc mvc;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;


    @Test
    public void whenGetProductShouldReturnAllProducts() throws Exception {
        //TODO
    }

    //TODO other Unit tests
}