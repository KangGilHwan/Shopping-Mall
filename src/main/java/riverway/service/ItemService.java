package riverway.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import riverway.domain.Item;
import riverway.domain.repository.ItemRepository;
import riverway.dto.ItemDto;

import javax.persistence.EntityNotFoundException;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public Item register(ItemDto newItem){
        return itemRepository.save(newItem.toItem());
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
