package com.example.jpa.repository;

import com.example.jpa.domain.Item;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

  private final EntityManager em;

  public Long save(Item item) {
    if (item.getId() == null)
      em.persist(item);
    else {
      em.merge(item);
    }

    return item.getId();
  }

  public Item findById(Long id) {
    return em.find(Item.class, id);
  }

  public List<Item> findAll() {
    return em.createQuery("SELECT i FROM Item i", Item.class)
        .getResultList();
  }

}
