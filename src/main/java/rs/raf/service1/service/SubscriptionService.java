package rs.raf.service1.service;

import rs.raf.service1.domain.dto.SubscriptionReqDto;
import rs.raf.service1.domain.dto.SubscriptionResDto;

import java.util.List;

public interface SubscriptionService {

    SubscriptionResDto addSubscription(SubscriptionReqDto subscriptionDto);

    List<SubscriptionResDto> findAll();
}
