package hwg.bachelor.fastbusspringboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer"))
    private User customer; // Assuming Account is another entity

    @ManyToOne
    @JoinColumn(name = "bus_info_id", nullable = false, foreignKey = @ForeignKey(name = "fk_bus_info"))
    private BusInfo busInfo; // Assuming BusInfo is another entity

    @ManyToOne
    @JoinColumn(name = "route_id", nullable = false, foreignKey = @ForeignKey(name = "fk_route"))
    private Routes route; // Assuming Route is another entity

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @Column(name = "iban", nullable = false, length = 34)
    private String iban;

    @Column(name = "bic", nullable = false, length = 11)
    private String bic;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "account_name", nullable = false)
    private String accountName;

    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

}
