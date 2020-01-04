package rs.raf.service1.ctrl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.raf.service1.domain.dto.SubscriptionReqDto;
import rs.raf.service1.domain.dto.SubscriptionResDto;
import rs.raf.service1.service.SubscriptionService;

@RestController
@RequestMapping(value = "/sub")
@RequiredArgsConstructor
public class SubCtrl {

    private final SubscriptionService subscriptionService;

    @PostMapping("/save")
    public SubscriptionResDto save(@RequestBody SubscriptionReqDto subscriptionReq) {
        return subscriptionService.addSubscription(subscriptionReq);
    }

}
