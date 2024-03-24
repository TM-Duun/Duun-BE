package dunn.dunnshop.controller;

import dunn.dunnshop.dto.category.CategoryResponseDto;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/category")
public class CategoryController {
    private final ItemService itemService;

    @GetMapping("/{category}")
    public CategoryResponseDto getCategoryItem(@PathVariable ("category") String category){
        return itemService.getCategoryItem(category);
    }

}
