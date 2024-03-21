package dunn.dunnshop.controller.main;

import dunn.dunnshop.response.main.CategoryDto;
import dunn.dunnshop.response.main.MainDto;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ItemService itemService;

    @GetMapping("/category/coat")
    public List<CategoryDto> searchCoat() {
        return itemService.searchCoat();
    }

    @GetMapping("/category/pants")
    public List<CategoryDto> searchPants() {
        return itemService.searchPants();
    }

    @GetMapping("/category/sweater")
    public List<CategoryDto> searchSweat() {
        return itemService.searchSweat();
    }
}
