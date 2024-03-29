package dunn.dunnshop.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import dunn.dunnshop.dto.OrderDetailDto;
import jakarta.persistence.*;
import lombok.*;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Orders {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;

    @Column
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetails> orderItems = new ArrayList<>();

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<OrderDetails> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderDetails> orderItems) {
        this.orderItems = orderItems;
    }

}
