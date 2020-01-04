package rs.raf.service2.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import rs.raf.service2.domain.dto.CouponDto;
import rs.raf.service2.service.CouponApiIntegrationService;

import java.util.List;

@RestController
@RequestMapping(value = "/coupon")
@RequiredArgsConstructor
@EnableScheduling
public class CouponCtrl {

    @Autowired
    private CouponApiIntegrationService couponApiIntegrationService;

    @GetMapping("/findAll")
    public List<CouponDto> findAll(){
        return couponApiIntegrationService.findAllCoupons();
    }

    @GetMapping("/findByCountry")
    public List<CouponDto> findByCountry(@RequestParam (name = "country") String country){
        return couponApiIntegrationService.findCoupons(country);
    }

    @GetMapping("/updateAll")
    //@Scheduled(fixedRate = 10000)
    public List<CouponDto> updateAll(){
        return couponApiIntegrationService.updateAllCoupons();
    }

}

