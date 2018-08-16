package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import riverway.domain.Product;
import riverway.domain.User;
import riverway.dto.ProductDto;
import riverway.security.LoginUser;
import riverway.service.ProductService;

import java.io.IOException;
import java.net.URI;

@RestController
@RequestMapping("/api/products")
public class ApiProductController {

    private static final Logger log = LoggerFactory.getLogger(ApiProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<Void> create(@LoginUser User loginUser, ProductDto productDto, MultipartFile image) throws IOException{
        log.debug("New Product : {}", productDto);
        log.debug("Image : {}", image.getOriginalFilename());

        Product product = productService.register(loginUser, productDto, image);
        URI uri = URI.create(String.format("/api/products/%d", product.getId()));
        log.debug("Check : {}", product);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ProductDto show(@PathVariable Long id) {
        return productService.findItem(id).toProductDto();
    }
}
