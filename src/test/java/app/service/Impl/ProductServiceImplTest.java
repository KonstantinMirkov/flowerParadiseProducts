package app.service.Impl;


import app.model.binding.CreateProductDto;
import app.model.entity.ProductEntity;
import app.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;


@RunWith(MockitoJUnitRunner.class)
class ProductServiceImplTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductServiceImpl productService;

    private ModelMapper modelMapper;


    // given/when/then format - BDD style
    @Test
    public void whenCreatedShouldReturnProduct() {
        // given - setup or precondition
        CreateProductDto product = new CreateProductDto();

        modelMapper.map(product, ProductEntity.class);

        ProductEntity createdProduct = productService.createProduct(product);

        //when - action
        //TODO

        //then - verify the output
        //TODO

    }

    //TODO other Unit tests
}