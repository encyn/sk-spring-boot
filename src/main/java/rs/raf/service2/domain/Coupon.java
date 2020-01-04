package rs.raf.service2.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String store_name;

    private String store_country;

    private String code;

    private String description;

    private String store_id;
}
