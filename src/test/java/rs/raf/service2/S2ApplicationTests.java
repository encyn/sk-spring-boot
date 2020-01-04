package rs.raf.service2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rs.raf.service2.domain.dto.CouponDto;
import rs.raf.service2.service.CouponApiIntegrationService;

import java.util.List;

@SpringBootTest
class S2ApplicationTests {

	@Autowired
	private CouponApiIntegrationService couponApiIntegrationService;

	@Test
	void contextLoads() {

		List<CouponDto> coupons = couponApiIntegrationService.findCoupons("US");
		for(CouponDto couponDto : coupons) {
			System.out.println(couponDto.toString());
		}


	}

}
