package rs.raf.service2.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import rs.raf.service2.domain.Coupon;

import java.util.List;

public interface CouponDao extends JpaRepository<Coupon, Integer> {

    @Query("select c from Coupon c where c.store_country = ?1")
    List<Coupon> findByCountry(String country);

    @Query("select c from Coupon c where c.store_id = ?1 and c.code = ?2")
    Coupon findByStoreIdAndCode(String store_id, String code);
}
