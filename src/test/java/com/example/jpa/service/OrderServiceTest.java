package com.example.jpa.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.jpa.domain.Item;
import com.example.jpa.domain.Member;
import com.example.jpa.domain.Order;
import com.example.jpa.domain.OrderStatus;
import com.example.jpa.repository.ItemRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class OrderServiceTest {

  @Autowired
  OrderService orderService;

  @Autowired
  MemberService memberService;

  @Autowired
  ItemService itemService;

  @Test
  void order() {
    Member member = new Member("user");
    memberService.join(member);

    Item item = Item.createItem("Book", 1000, 10);
    itemService.saveItem(item);

    Long orderId = orderService.order(member.getId(), item.getId(), 3);

    Order order = orderService.findOnd(orderId);
    assertThat(order.getMember()).isEqualTo(member);
    assertThat(order.getOrderItems().get(0).getItem()).isEqualTo(item);
    assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.ORDER);
  }

  @Test
  void order_fail() {
    Member member = new Member("user");
    memberService.join(member);

    Item item = Item.createItem("Book", 1000, 10);
    itemService.saveItem(item);

    assertThrows(IllegalStateException.class,
        () -> orderService.order(member.getId(), item.getId(), 11));
  }

  @Test
  void order_cancel() {
    Member member = new Member("user");
    memberService.join(member);

    Item item = Item.createItem("Book", 1000, 10);
    itemService.saveItem(item);

    Long orderId = orderService.order(member.getId(), item.getId(), 3);
    orderService.cancelOrder(orderId);

    Order order = orderService.findOnd(orderId);

    assertThat(order.getOrderStatus()).isEqualTo(OrderStatus.CANCEL);
    assertThat(order.getOrderItems().get(0).getItem().getStockQuantity()).isEqualTo(10);
  }
}
