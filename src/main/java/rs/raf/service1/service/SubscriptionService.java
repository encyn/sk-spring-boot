package rs.raf.service1.service;

import org.json.simple.parser.ParseException;
import rs.raf.service1.domain.dto.SubscriptionReqDto;
import rs.raf.service1.domain.dto.SubscriptionResDto;
import rs.raf.service1.domain.dto.UserResDto;

import java.util.List;

public interface SubscriptionService {

    List<SubscriptionResDto> findAll();

    SubscriptionResDto addSubscription(SubscriptionReqDto subscriptionDto);

    SubscriptionResDto removeSubscription(String json) throws ParseException;

    List<String> getSubscriptionsForEmail(String userEmail) throws ParseException;
}
