package dunn.dunnshop.service;

import dunn.dunnshop.domain.Item;
import dunn.dunnshop.dto.category.CategoryDto;
import dunn.dunnshop.dto.category.CategoryResponseDto;
import dunn.dunnshop.dto.main.MainDto;
import dunn.dunnshop.dto.main.*;
import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemsRepository itemsRepository;

    //아이템 추가
    public void save(Item item){
        itemsRepository.save(item);
    }

    //아이템 제거
    public void remove(Long id){
        final Item item = itemsRepository.findById(id).orElseThrow();
        itemsRepository.delete(item);
    }

    //모든 아이템 출력
    public List<ItemDto> findAllItems(){
        final List<Item> allItems = itemsRepository.findAll();
        return allItems.stream().map(ItemDto::from).toList();
    }

    //특정 아이템 출력
    public ItemDto findItem(Long id){
        final Item item = itemsRepository.findById(id).orElseThrow();
        return ItemDto.from(item);
    }

    /*
    // 아이테 갱신 추가
    */

    /*
    =============================== Main ===============================
     */
    // 좋아요 상위 4개 item 반환
    public MainResponseDto gethome(){
        List<Item>allItem = itemsRepository.findAll();

        Collections.sort(allItem);

        // 상위 4개 아이템
        List<Item> Items = allItem.subList(0,4);

        final List<MainDto> mainDtos = new ArrayList<>();
        for (Item item : Items) {
            MainDto mainDto = MainDto.builder()
                                .itemId(item.getId())
                                .img(item.getImg())
                                .build();
            mainDtos.add(mainDto);
        }

        MainResponseDto response = MainResponseDto.builder().mainDtos(mainDtos).build();
        return response;
    }

    // 신상 조회
    public MainResponseDto getNew(){
        List<Item> allItem = itemsRepository.findAll();

        allItem.sort(Comparator.comparing(Item::getCreatedAt).reversed());

        List<Item> Items = allItem.subList(0, 4);

        List<MainDto> mainDtos = new ArrayList<>();
        for (Item item : Items) {
            MainDto mainDto = MainDto.builder().itemId(item.getId()).img(item.getImg()).build();
            mainDtos.add(mainDto);
        }

        MainResponseDto response = MainResponseDto.builder().mainDtos(mainDtos).build();
        return response;
    }

    // 카테고리 마다 상위 좋아요 4개
    public MainResponseDto getCategoryTopItem(String category){
//        List<Item> allCategoryItem = itemsRepository.findAll().stream()
//                .filter(item -> category.equals(item.getCategory())).collect(Collectors.toList());
//        Collections.sort(allCategoryItem);
//
//        List<Item> Items = allCategoryItem.subList(0,4);

//        final List<Item> items = itemsRepository.findByCategoryTop4(category);

//        List<MainDto> mainDtos = new ArrayList<>();
//        for (Item item : items) {
//            MainDto mainDto = MainDto.builder()
//                                        .itemId(item.getId())
//                                        .img(item.getImg())
//                                        .build();
//            mainDtos.add(mainDto);
//        }
        return new MainResponseDto(itemsRepository.findByCategoryTop4(category));
    }

    /*
    =============================== Category ===============================
     */
    public CategoryResponseDto getCategoryItem(String category){
        List<Item> allCategoryItem = itemsRepository.findAll().stream()
                .filter(item -> category.equals(item.getCategory())).collect(Collectors.toList());

        List<CategoryDto> categoryDtos = new ArrayList<>();
        for (Item item : allCategoryItem) {
            CategoryDto categoryDto = CategoryDto.builder()
                    .itemId(item.getId())
                    .img(item.getImg())
                    .name(item.getName())
                    .price(item.getPrice())
                    .build();

            categoryDtos.add(categoryDto);
        }
        CategoryResponseDto response = CategoryResponseDto.builder()
                                                        .categoryDtos(categoryDtos)
                                                        .build();

        return response;
    }

}
