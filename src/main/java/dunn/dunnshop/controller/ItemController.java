package dunn.dunnshop.controller;

import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.entity.Items;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/item")
//    public List<ItemDto> getItemForm() {
//        return itemService.getAllItems();
//    }

    @GetMapping("/item/{id}")
    public ItemDto searchItem(@PathVariable Long id) {
        return itemService.searchItem(id);
    }

    @PostMapping("/item")
    public Items itemForm(@RequestBody Items items) {

        Items savedItem = itemService.saveItem(items);

        log.info("itemDto={}", items);
        log.info("name={}, price={}", items.getName(), items.getPrice());

        return savedItem;
    }

    @PostMapping("/items")
    public List<Items> itemListForm(@RequestBody List<Items> itemsList) {
        return itemService.saveListItem(itemsList);
    }
}
