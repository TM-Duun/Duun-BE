package dunn.dunnshop.controller;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.dto.category.CategoryResponseDto;
import dunn.dunnshop.dto.main.MainResponseDto;
import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value="/items")
@RequiredArgsConstructor
@RestController
@Slf4j
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/save")
    public void save(@RequestBody Item item){
        log.info("Item = {}", item);
        itemService.save(item);
    }
//    @GetMapping()
    public List<ItemDto>findAllItems(){
        return itemService.findAllItems();
    }

    @GetMapping("/{id}")
    public ItemDto findItem(@PathVariable("id") Long id){
        return itemService.findItem(id);
    }

    // main의 home
    // 상위 like 아이템 4개
    @GetMapping("/home")
    public MainResponseDto gethome(){
        return itemService.gethome();
    }

    //main
    @GetMapping("/new")
    public  MainResponseDto getNew(){
        return itemService.getNew();
    }
    //main

    @GetMapping
    public MainResponseDto getCategoryTopItem(@RequestParam("category") String category){
        return itemService.getCategoryTopItem(category);
    }
}
