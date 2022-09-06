package app.service.Impl;

import app.model.binding.CreateProductDto;
import app.model.binding.ProductDto;
import app.model.binding.UpdateProductDto;
import app.model.entity.ProductEntity;
import app.repository.ProductRepository;
import app.service.ProductService;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static app.constant.ConstantMessages.*;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    //TODO createTest - with correct values and 1 with false values with exception
    @Override
    public ProductEntity createProduct(CreateProductDto createProductDto) {
        ProductEntity productEntity = fromCreatedProductEntity(createProductDto);

        return productRepository.save(productEntity);
    }

    //TODO createTest - with correct values and 1 with false values with exception
    @Override
    public long updateProduct(UpdateProductDto updateProductDto, long id) throws NotFoundException {
        productRepository.findById(id).orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        ProductEntity newProductEntity = fromUpdatedProductEntity(updateProductDto);

        // TODO validation if any fields of product entity is different

        return productRepository.save(newProductEntity).getId();
    }

    //TODO createTest - with correct values and 1 with false values with exception
    @Override
    public void deleteProduct(long id) throws NotFoundException {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        productRepository.deleteById(productEntity.getId());
    }

    //TODO createTest - with correct values and 1 with false values with exception
    @Override
    public Page<ProductDto> getProducts(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        return productRepository.findAll(pageable).map(this::asProduct);
    }

    //TODO createTest - with correct values and 1 with false values with exception
    @Override
    public Optional<ProductDto> getProductById(long id) throws NotFoundException {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NO_PRODUCT_WITH_THIS_ID));

        return productRepository.findById(productEntity.getId()).map(this::asProduct);
    }

    private ProductDto asProduct(ProductEntity productEntity) {
        return modelMapper.map(productEntity, ProductDto.class);
    }

    private ProductEntity fromCreatedProductEntity(CreateProductDto createProductDto) {
        return modelMapper.map(createProductDto, ProductEntity.class);
    }

    private ProductEntity fromUpdatedProductEntity(UpdateProductDto updateProductDto) {
        return modelMapper.map(updateProductDto, ProductEntity.class);
    }
}
