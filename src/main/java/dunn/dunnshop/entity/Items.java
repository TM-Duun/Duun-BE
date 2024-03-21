package dunn.dunnshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 파라미터 없는 생성자 만듬
public class Items {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20)
    private String name;

    @Column
    private Long price;

    @Column(length = 10)
    private String color;

    @Column(length = 5)
    private String size;

    @Column(length = 10)
    private String gender;

    @Column
    private Long likes;

    @Column(length = 20)
    private String category;

    @Column(length = 100)
    private String image;

    @Column
    private LocalDateTime incomeDate;

    @Builder
    private Items(String name, Long price, String color, String size, String gender,
                  Long likes, String category, String image, LocalDateTime incomeDate) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.gender = gender;
        this.likes = likes;
        this.category = category;
        this.image = image;
        this.incomeDate = incomeDate;
    }
}
