package rs.raf.service1.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.service1.domain.Subscription;

public interface SubscriptionDao extends JpaRepository<Subscription, Long> {
}
