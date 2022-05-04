package peaksoft.house.models;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.house.enums.OrderStatus;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter@Setter
@NoArgsConstructor
@ToString
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private long phoneNumber;

    @OneToOne(cascade =
            {CascadeType.PERSIST,CascadeType.MERGE},
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private Address address;

    @OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true,  mappedBy = "customer", fetch = FetchType.EAGER )
    private List<Order> orders;

    public Customer(String fullName, long phoneNumber) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }



    public void removeOrderById(Long orderid){
        orders.removeIf(order -> order.getId().equals(orderid));
    }

    public void ChangeStatusById(Long id){
        for (Order o : orders){
            if (o.getId().equals(id)){
                o.setStatus(OrderStatus.CANCELED);
            }
        }
    }
    public void addOrder(Order newOrder){
        this.orders.add(newOrder);
    }
    public List<Order> findByStatus(OrderStatus orderStatus){
        List<Order> orderss = new ArrayList<>();
        for (Order o: orders){
            if(o.getStatus().equals(orderStatus)){
                orderss.add(o);
            }
        }
        return orderss;
    }
}
