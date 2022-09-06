package app.web;

import app.model.binding.CreateProductDto;
import app.model.binding.ProductDto;
import app.model.binding.UpdateProductDto;
import app.model.entity.ProductEntity;
import app.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/products")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    //TODO create test for controller - getId
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.of(productService.getProductById(id));
    }

    //TODO create test for controller - get
    @GetMapping
    public ResponseEntity<Page<ProductDto>> getProducts(@RequestParam(name = "page", defaultValue = "0") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                                        @RequestParam(name = "orderBy", defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(productService.getProducts(pageNo, pageSize, sortBy));
    }

    //TODO create test for controller - delete
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id) throws Exception {
        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }

    //TODO create test for controller - put
    @PutMapping("/{id}")
    public ResponseEntity<UpdateProductDto> updateProduct(@PathVariable("id") long id,
                                                          @Valid @RequestBody UpdateProductDto updateProductDto) throws Exception {
        productService.updateProduct(updateProductDto, id);

        return ResponseEntity.noContent().build();
    }

    //TODO create test for controller - create
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductEntity> createProduct(@Valid @RequestBody CreateProductDto createProductDto) throws Exception {
        ProductEntity createdProduct = productService.createProduct(createProductDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }
}
