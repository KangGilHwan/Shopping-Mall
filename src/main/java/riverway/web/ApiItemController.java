package riverway.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import riverway.domain.Item;
import riverway.domain.User;
import riverway.dto.ItemDto;
import riverway.security.LoginUser;
import riverway.service.ItemService;

import java.net.URI;

@RestController
@RequestMapping("/api/items")
public class ApiItemController {

    private static final Logger log = LoggerFactory.getLogger(ApiItemController.class);

    @Autowired
    private ItemService itemService;

    @PostMapping("")
    public ResponseEntity<Void> create(@LoginUser User loginUser, @RequestBody ItemDto itemDto) {
        log.debug("New Item : {}", itemDto);
        Item item = itemService.register(itemDto);

        URI uri = URI.create(String.format("/api/items/%d", item.getId()));
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("{id}")
    public ItemDto show(@PathVariable Long id){
        return itemService.findItem(id).toItemDto();
    }
}
