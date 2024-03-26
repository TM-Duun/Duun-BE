package dunn.dunnshop.controller.main;

import dunn.dunnshop.dto.response.main.MainDto;
import dunn.dunnshop.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final ItemService itemService;

    @GetMapping("/item/home")
    public List<MainDto> mainHome() {
        return itemService.MainHome();
    }

    @GetMapping("/item/new")
    public List<MainDto> mainNew() {
        return itemService.MainNew();
    }

    @GetMapping("/item/coat")
    public List<MainDto> mainCoat() {
        return itemService.MainCoat();
    }

    @GetMapping("/item/pants")
    public List<MainDto> mainPants() {
        return itemService.MainPants();
    }

    @GetMapping("/item/sweater")
    public List<MainDto> mainSweater() {
        return itemService.MainSweater();
    }
}
