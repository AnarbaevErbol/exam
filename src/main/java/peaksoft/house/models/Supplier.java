package peaksoft.house.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import peaksoft.house.enums.SupplierStatus;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter@Setter
@NoArgsConstructor
@ToString
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone_number")
    private int phoneNumber;
    
    @Column(name = "status")
    private SupplierStatus status;

    @OneToMany(mappedBy = "supplier")
    private List<Order> orders;

    public Supplier(String fullName, int phoneNumber, SupplierStatus status) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.status = status;
    }

    public void giveOrder( Order freeOrder) {
        orders.add(freeOrder);
    }
}
