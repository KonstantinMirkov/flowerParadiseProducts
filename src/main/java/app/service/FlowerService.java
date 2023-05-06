package app.service;

import app.model.binding.CreateFlowerDto;
import app.model.binding.FlowerDto;
import app.model.binding.UpdateFlowerDto;
import app.model.entity.FlowerEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface FlowerService {
    FlowerEntity createProduct(CreateFlowerDto createProductDto) throws Exception;

    long updateProduct(UpdateFlowerDto updateProductDto, long id) throws Exception;

    void deleteProduct(long id) throws Exception;

    Page<FlowerDto> getProducts(Integer pageNo, Integer pageSize, String sortBy);

    Optional<FlowerDto> getProductById(long id) throws Exception;
}
