package app.service;

import app.model.binding.CreateProductDto;
import app.model.binding.ProductDto;
import app.model.binding.UpdateProductDto;
import app.model.entity.ProductEntity;
import org.springframework.data.domain.Page;
import java.util.Optional;

public interface ProductService {

    void deleteProduct(long id) throws Exception;

    ProductEntity createProduct(CreateProductDto createProductDto) throws Exception;

    long updateProduct(UpdateProductDto updateProductDto, long id) throws Exception;

    Page<ProductDto> getProducts(Integer pageNo, Integer pageSize, String sortBy);

    Optional<ProductDto> getProductById(long id) throws Exception;

}
