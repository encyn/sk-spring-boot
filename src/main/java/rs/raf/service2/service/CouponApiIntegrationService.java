package rs.raf.service2.service;

import rs.raf.service2.domain.dto.CouponDto;

import java.util.List;

public interface CouponApiIntegrationService {

    List<CouponDto> findCoupons(String country);

    List<CouponDto> findAllCoupons();

    List<CouponDto> updateAllCoupons();

    List<String> getAllLists();
}
