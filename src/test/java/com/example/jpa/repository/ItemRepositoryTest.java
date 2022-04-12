package com.example.jpa.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.jpa.domain.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;

  @Test
  public void save() {
    Item item = new Item();
    item.setName("item1");
    item.setPrice(3000);
    item.setPrice(0);

    Long savedId = itemRepository.save(item);

    assertThat(savedId).isEqualTo(item.getId());
  }
}
