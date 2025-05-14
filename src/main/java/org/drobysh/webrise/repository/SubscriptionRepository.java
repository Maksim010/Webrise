package org.drobysh.webrise.repository;

import org.drobysh.webrise.model.Subscription;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findSubscriptionByUserId(Long userId);

    @Query("SELECT s FROM Subscription s WHERE s.user.id = :userId AND s.id = :subId")
    Optional<Subscription> findByUserIdAndId(
            @Param("userId") Long userId,
            @Param("subId") Long subscriptionId);

    @Query("""
        SELECT s.name, COUNT(s) as count
        FROM Subscription s
        GROUP BY s.name
        ORDER BY count DESC
    """)
    Page<Object[]> findTop3PopularSubscriptions(Pageable pageable);
}
