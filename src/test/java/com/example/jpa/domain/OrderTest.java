package com.example.jpa.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class OrderTest {

  @Test
  public void create() {
    Item item = Item.createItem("Book", 1000, 100);
    Member member = new Member("member");
    OrderItem orderItem = OrderItem.createOrderItem(item, 1000, 30);

    Order order = Order.createOrder(member, new Delivery(), orderItem);

    assertThat(order.getMember()).isEqualTo(member);
    assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
    assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(100 - 30);
  }

  @Test
  public void orderCancel() {
    Item item = Item.createItem("Book", 1000, 100);
    Member member = new Member("member");
    OrderItem orderItem = OrderItem.createOrderItem(item, 1000, 30);

    Order order = Order.createOrder(member, new Delivery(), orderItem);
    order.cancel();

    assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(100);
    assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
  }

  @Test
  public void orderFail() {
    Item item = Item.createItem("Book", 1000, 10);
    Member member = new Member("member");

    assertThrows(IllegalStateException.class,
        () -> OrderItem.createOrderItem(item, 1000, 30));
  }
}
