package app.web;

import app.model.binding.CreateGiftDto;
import app.model.binding.GiftDto;
import app.model.binding.UpdateGiftDto;
import app.model.entity.GiftEntity;
import app.service.GiftService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/gifts")
public class GiftsController {
    private final GiftService giftService;

    public GiftsController(GiftService giftService) {
        this.giftService = giftService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<GiftDto> getProductById(@PathVariable("id") Long id) throws Exception {
        return ResponseEntity.of(giftService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<Page<GiftDto>> getProducts(@RequestParam(name = "page", defaultValue = "0") int pageNo,
                                                     @RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
                                                     @RequestParam(name = "orderBy", defaultValue = "id") String sortBy) {

        return ResponseEntity.ok(giftService.getProducts(pageNo, pageSize, sortBy));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GiftDto> deleteProduct(@PathVariable("id") Long id) throws Exception {
        giftService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public long updateProduct(@PathVariable("id") long id,
                              @Valid @RequestBody UpdateGiftDto updateGiftDto) throws Exception {
        return giftService.updateProduct(updateGiftDto, id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<GiftEntity> createProduct(@Valid @RequestBody CreateGiftDto createGiftDto) throws Exception {
        GiftEntity createdProduct = giftService.createProduct(createGiftDto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/products/{id}").buildAndExpand(createdProduct.getId()).toUri();

        return ResponseEntity.created(location).body(createdProduct);
    }
}
