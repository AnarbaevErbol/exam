package peaksoft.house.models;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.house.enums.OrderStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter@Setter
@NoArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateTime")
    private LocalDateTime date;

    @Column(name = "price")
    private int price;

    @Column(name = "status")
    private OrderStatus status;

    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    @ToString.Exclude
    private Customer customer;

    @OneToOne
    private Address pointA;

    @OneToOne
    private Address pointB;

    @ManyToOne
    private Supplier supplier;

    public Order(LocalDateTime date, int price, OrderStatus status) {
        this.date = date;
        this.price = price;
        this.status = status;
    }


}
