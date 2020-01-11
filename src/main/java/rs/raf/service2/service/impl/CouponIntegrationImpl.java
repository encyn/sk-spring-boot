package rs.raf.service2.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rs.raf.service2.dao.CouponDao;
import rs.raf.service2.domain.Coupon;
import rs.raf.service2.domain.dto.CouponDto;
import rs.raf.service2.service.CouponApiIntegrationService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponIntegrationImpl implements CouponApiIntegrationService {

    private static final String URL = "https://kuponiko.p.rapidapi.com/recent/{country}";

    private final RestTemplate restTemplate;
    private final CouponDao couponDao;


    private static final String[] countries = {"US", "UK", "FR", "DE"};

    private List<CouponDto> toCouponDtos(List<Coupon> coupons){
        List<CouponDto> couponDtos = new ArrayList<>();
        for(Coupon coupon : coupons)
            couponDtos.add(new CouponDto(coupon));
        return couponDtos;
    }


    @Override
    public List<CouponDto> findCoupons(String country) {
        return toCouponDtos(couponDao.findByCountry(country));
    }

    private List<CouponDto> updateCoupons(String country) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-host", "kuponiko.p.rapidapi.com");
        headers.set("x-rapidapi-key", "4c69b74e3fmsh6c8922eb6130e97p185c58jsn360b5b8cb8bf");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<Coupon[]> response = restTemplate.exchange(URL, HttpMethod.GET, entity, Coupon[].class, country);
        List<Coupon> coupons = Arrays.asList(response.getBody());
        for(Coupon coupon : coupons)
            couponDao.save(coupon);
        return toCouponDtos(coupons);
    }

    @Override
    public List<CouponDto> findAllCoupons() {
        List<CouponDto> allCouponDtos = new ArrayList<>();
        for(String country : countries)
            allCouponDtos.addAll(findCoupons(country));
        return allCouponDtos;
    }

    @Override
    public List<CouponDto> updateAllCoupons() {
        List<CouponDto> allCouponDtos = new ArrayList<>();
        couponDao.deleteAll();
        for(String country : countries)
            allCouponDtos.addAll(updateCoupons(country));
        return allCouponDtos;
    }

    @Override
    public List<String> getAllLists() {
        return Arrays.asList(countries);
    }
}

