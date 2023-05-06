package app.web;

import app.model.binding.CreateFlowerDto;
import app.model.binding.FlowerDto;
import app.model.binding.UpdateFlowerDto;
import app.model.entity.FlowerEntity;
import app.service.FlowerService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/flowers")
public class FlowersController {
    private final FlowerService flowerService;

    public FlowersController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlowerDto> getProductById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.of(flowerService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<FlowerDto>> getProducts(@RequestParam(name = "page", defaultValue = "0") int pageNo,
                                                        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                                        @RequestParam(name = "orderBy", defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(flowerService.getProducts(pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlowerDto> deleteProduct(@PathVariable("id") Long id) throws Exception {
        flowerService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public long updateProduct(@PathVariable("id") long id,
                              @Valid @RequestBody UpdateFlowerDto updateFlowerDto) throws Exception {
        return flowerService.updateProduct(updateFlowerDto, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<FlowerEntity> createProduct(@Valid @RequestBody CreateFlowerDto createFlowerDto) throws Exception {
        FlowerEntity createdProduct = flowerService.createProduct(createFlowerDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }
}
