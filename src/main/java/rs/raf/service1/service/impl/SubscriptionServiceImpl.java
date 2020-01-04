package rs.raf.service1.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.raf.service1.dao.SubscriptionDao;
import rs.raf.service1.dao.UserDao;
import rs.raf.service1.domain.Frequency;
import rs.raf.service1.domain.Subscription;
import rs.raf.service1.domain.dto.SubscriptionReqDto;
import rs.raf.service1.domain.dto.SubscriptionResDto;
import rs.raf.service1.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionDao subscriptionDao;
    private final UserDao userDao;

    @Override
    public SubscriptionResDto addSubscription(SubscriptionReqDto subscriptionDto) {
        Subscription subscription = Subscription.builder().list(subscriptionDto.getList())
                .frequency(Frequency.valueOf(subscriptionDto.getFrequency())).user(userDao.findById(subscriptionDto.getUserId()).get()).build();
        subscriptionDao.save(subscription);
        return new SubscriptionResDto(subscription);
    }

    @Override
    public List<SubscriptionResDto> findAll() {
        List<SubscriptionResDto> subscriptionDtos = new ArrayList<>();
        for(Subscription s : subscriptionDao.findAll())
            subscriptionDtos.add(new SubscriptionResDto(s));
        return subscriptionDtos;
    }
}
