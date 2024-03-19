package dunn.dunnshop.service;

import dunn.dunnshop.dto.ItemDto;
import dunn.dunnshop.entity.Items;
import dunn.dunnshop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Items saveItem(Items items) {
        return itemRepository.save(items);
    }

    public List<ItemDto> getAllItems() {
        List<Items> itemsList = itemRepository.findAll();

        return itemsList.stream().map(
                item -> new ItemDto(item.getName(), item.getPrice(), item.getDescription(), item.getCategory())).toList();
    }
}
