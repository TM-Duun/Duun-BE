package dunn.dunnshop.service;

import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.entity.Items;
import dunn.dunnshop.repository.ItemRepository;
import dunn.dunnshop.dto.response.main.CategoryDto;
import dunn.dunnshop.dto.response.main.MainDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    // =============================== Item CRUD 하기 ===============================

    // 특정 아이템 조회하기
    public ItemDto searchItem(Long id) {
        Optional<Items> findItem = itemRepository.findById(id);

        Items item = findItem.get();

        // Dto로 반환하기 위해서 Build 하기
        ItemDto itemDto = ItemDto.builder()
                .name(item.getName())
                .price(item.getPrice())
                .color(item.getColor())
                .size(item.getSize())
                .gender(item.getGender())
                .likes(item.getLikes())
                .category(item.getCategory())
                .image(item.getImage())
                .incomeDate(LocalDateTime.now())
                .build();

        return itemDto;
    }

    // 단일로 받은 item 저장하기
    public Items saveItem(Items items) {
        Items savedItem = Items.builder()
                .name(items.getName())
                .price(items.getPrice())
                .color(items.getColor())
                .size(items.getSize())
                .gender(items.getGender())
                .likes(items.getLikes())
                .category(items.getCategory())
                .image(items.getImage())
                .incomeDate(LocalDateTime.now()).build();

        Items item = itemRepository.save(savedItem);

        return item;
    }

    // 리스트로 받은 모든 item 저장하기
    public List<Items> saveListItem(List<Items> itemsList) {

        List<Items> savedItemsList = new ArrayList<>();

        for (Items items : itemsList) {
            Items saveItem = Items.builder()
                    .name(items.getName())
                    .price(items.getPrice())
                    .color(items.getColor())
                    .size(items.getSize())
                    .gender(items.getGender())
                    .likes(items.getLikes())
                    .category(items.getCategory())
                    .image(items.getImage())
                    .incomeDate(LocalDateTime.now()).build();

            itemRepository.save(saveItem);
            savedItemsList.add(saveItem);
        }
        return savedItemsList;
    }

    // 모든 item 조회하기
    public List<ItemDto> getAllItems() {
        List<Items> itemsList = itemRepository.findAll();

        return itemsList.stream().map(
                item -> new ItemDto(item.getName(), item.getPrice(), item.getColor(), item.getSize(), item.getGender(),
                        item.getLikes(), item.getCategory(), item.getImage(), item.getIncomeDate())).toList();
    }

    // =============================== Category 화면 ===============================

    // 카테고리 화면에서 코트 아이템 조회하기
    public List<CategoryDto> searchCoat() {
        List<CategoryDto> returnItems = new ArrayList<>();
        List<Items> coatItems = itemRepository.findByCategory("coat");

        for (Items coatItem : coatItems) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .itemId(coatItem.getId())
                    .img(coatItem.getImage())
                    .itemName(coatItem.getName())
                    .price(coatItem.getPrice()).build();

            returnItems.add(categoryDto);
        }
        return returnItems;
    }

    // 카테고리 화면에서 바지 아이템 조회하기
    public List<CategoryDto> searchPants() {
        List<CategoryDto> returnItems = new ArrayList<>();
        List<Items> pantsItems = itemRepository.findByCategory("pants");

        for (Items pantsItem : pantsItems) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .itemId(pantsItem.getId())
                    .img(pantsItem.getImage())
                    .itemName(pantsItem.getName())
                    .price(pantsItem.getPrice()).build();

            returnItems.add(categoryDto);
        }
        return returnItems;
    }

    // 카테고리 화면에서 스웨터 아이템 조회하기
    public List<CategoryDto> searchSweat() {
        List<CategoryDto> returnItems = new ArrayList<>();
        List<Items> sweaterItems = itemRepository.findByCategory("sweater");

        for (Items sweaterItem : sweaterItems) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .itemId(sweaterItem.getId())
                    .img(sweaterItem.getImage())
                    .itemName(sweaterItem.getName())
                    .price(sweaterItem.getPrice()).build();

            returnItems.add(categoryDto);
        }
        return returnItems;
    }

    // =============================== Main 화면 ===============================

    // Home 조회하기 -> response 4개만 주기
    public List<MainDto> MainHome() {
        List<MainDto> returnItems = new ArrayList<>();
        List<Items> items = itemRepository.findAll();

        for (int i = 1; i < 5; i++) {
            Items item = items.get(i);
            MainDto mainDto = MainDto.builder()
                    .itemId(item.getId())
                    .img(item.getImage()).build();

            returnItems.add(mainDto);
        }
        return returnItems;
    }

    // New 조회하기 -> response 4개만 주기
    public List<MainDto> MainNew() {
        List<MainDto> returnItems = new ArrayList<>();

        List<Items> itemsList = itemRepository.findAll();

        List<Items> sortedItems = itemsList.stream().sorted(Comparator.comparing(Items::getIncomeDate).reversed()).toList();

        int cnt = 0;
        for (Items sortItem : sortedItems) {
            if (cnt > 5) {
                break;
            }

            MainDto mainDto = MainDto.builder()
                    .itemId(sortItem.getId())
                    .img(sortItem.getImage()).build();

            returnItems.add(mainDto);
            cnt++;
        }
        return returnItems;
    }

    // Coat 조회하기 -> response 4개만 주기
    public List<MainDto> MainCoat() {
        List<MainDto> returnItems = new ArrayList<>();
        List<Items> items = itemRepository.findByCategory("coat");

        for (int i = 1; i < 5; i++) {
            Items item = items.get(i);
            MainDto mainDto = MainDto.builder()
                    .itemId(item.getId())
                    .img(item.getImage()).build();

            returnItems.add(mainDto);
        }
        return returnItems;
    }

    // Pants 조회하기 -> response 4개만 주기
    public List<MainDto> MainPants() {
        List<MainDto> returnItems = new ArrayList<>();
        List<Items> items = itemRepository.findByCategory("pants");

        for (int i = 1; i < 5; i++) {
            Items item = items.get(i);
            MainDto mainDto = MainDto.builder()
                    .itemId(item.getId())
                    .img(item.getImage()).build();

            returnItems.add(mainDto);
        }
        return returnItems;
    }

    // Sweater 조회하기 -> response 4개만 주기
    public List<MainDto> MainSweater() {
        List<MainDto> returnItems = new ArrayList<>();
        List<Items> items = itemRepository.findByCategory("sweater");

        for (int i = 1; i < 5; i++) {
            Items item = items.get(i);
            MainDto mainDto = MainDto.builder()
                    .itemId(item.getId())
                    .img(item.getImage()).build();

            returnItems.add(mainDto);
        }
        return returnItems;
    }
}
