package com.example.jpa.repository;

import com.example.jpa.domain.Order;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderRepository {

  private final EntityManager em;

  @Transactional
  public Long save(Order order) {
    em.persist(order);

    return order.getId();
  }

  public Order findOne(Long id) {
    return em.find(Order.class, id);
  }
}
