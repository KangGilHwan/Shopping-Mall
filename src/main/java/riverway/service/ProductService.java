package riverway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import riverway.domain.Attachment;
import riverway.domain.Product;
import riverway.domain.User;
import riverway.domain.repository.AttachmentRepository;
import riverway.domain.repository.ItemRepository;
import riverway.dto.ProductDto;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Value("${file.path}")
    private String path;

    @Transactional
    public Product register(User seller, ProductDto item, MultipartFile image) throws IOException {
        Product product = itemRepository.save(item.setSeller(seller).toProduct());
        Attachment attachment = attachmentRepository.save(Attachment.of(path, product, image));
        image.transferTo(attachment.save());
        log.debug("이미지 파일 : {}", attachment);
        return product;
    }

    public Product findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<ProductDto> findAll() {
        return itemRepository.findAll().stream()
                .map(p -> p.toProductDto())
                .collect(Collectors.toList());
    }
}
