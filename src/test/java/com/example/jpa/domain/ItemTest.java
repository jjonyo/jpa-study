package com.example.jpa.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ItemTest {

  @Test
  public void stock() {
    Item item = new Item();
    item.setStockQuantity(0);

    item.addStock(10);

    assertThat(item.getStockQuantity()).isEqualTo(10);
  }

  @Test
  public void stockException() {
    Item item = new Item();
    item.setStockQuantity(0);

    assertThrows(IllegalStateException.class, () -> item.removeStock(1));
  }
}
