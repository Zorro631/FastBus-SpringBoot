package hwg.bachelor.fastbusspringboot.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "busroutes")
public class Routes {
    @Id
    private String routeId;

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String destination;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal duration;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;
}

