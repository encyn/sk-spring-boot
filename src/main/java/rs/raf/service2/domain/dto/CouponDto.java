package rs.raf.service2.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import rs.raf.service2.domain.Coupon;

@Data @NoArgsConstructor
public class CouponDto {

    private String store_name;

    private String store_country;

    private String code;

    private String description;

    public CouponDto(Coupon coupon){
        this.store_name = coupon.getStore_name();
        this.store_country = coupon.getStore_country();
        this.code = coupon.getCode();
        this.description = coupon.getDescription();
    }
}
