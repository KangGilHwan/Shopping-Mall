package riverway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.domain.Product;
import riverway.domain.repository.ItemRepository;
import riverway.dto.ProductDto;

import javax.persistence.EntityNotFoundException;

@Service
public class ProductService {

    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ItemRepository itemRepository;

    public Product register(ProductDto item){
        return itemRepository.save(item.toProduct());
    }

    public Product findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
