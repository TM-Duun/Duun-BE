package dunn.dunnshop.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity @Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="items")
public class Item implements Comparable<Item>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;

    @Column(name="item_name",nullable = false,length = 50)
    private String name;

    @Column(name="item_Price",nullable = false)
    private Long price;

    @Column(name="item_description",length=100)
    @Embedded
    private ItemDescription description;

    @Column(name="item_category",nullable = false,length=20)
    private String category;

    // image 임시 null 가능
    @Column(name="item_image",length=100)
    private String img;

    @Column(nullable = true)
    private LocalDateTime createdAt;

    @Builder
    private Item(String name, Long price, ItemDescription description, String category, String img, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.img = img;
        this.createdAt = createdAt;
    }

    @Override
    public int compareTo(Item item) {
        //내림차순 반환
        return (this.getDescription().getLike() - item.getDescription().getLike())*(-1);
    }
}
