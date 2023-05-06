package app.service;

import app.model.binding.*;
import app.model.entity.GiftEntity;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface GiftService {
    GiftEntity createProduct(CreateGiftDto createProductDto) throws Exception;

    long updateProduct(UpdateGiftDto updateProductDto, long id) throws Exception;

    void deleteProduct(long id) throws Exception;

    Page<GiftDto> getProducts(Integer pageNo, Integer pageSize, String sortBy);

    Optional<GiftDto> getProductById(long id) throws Exception;
}
