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

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/item/all")
    public List<ItemDto> getItemForm() {
        return itemService.getAllItems();
    }

    @ResponseBody
    @PostMapping("/item/new")
    public Items itemForm(@RequestBody Items items) {

        Items savedItem = itemService.saveItem(items);

        log.info("itemDto={}", items);
        log.info("name={}, price={}", items.getName(), items.getPrice());

        return savedItem;
    }
}
