package com.example.jpa.service;

import com.example.jpa.domain.Delivery;
import com.example.jpa.domain.Item;
import com.example.jpa.domain.Member;
import com.example.jpa.domain.Order;
import com.example.jpa.domain.OrderItem;
import com.example.jpa.repository.ItemRepository;
import com.example.jpa.repository.MemberRepository;
import com.example.jpa.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;
  private final ItemRepository itemRepository;
  private final MemberRepository memberRepository;

  @Transactional
  public Long order(Long memberId, Long itemId, int count) {
    Member member = memberRepository.findById(memberId);
    Item item = itemRepository.findById(itemId);

    OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

    Order order = Order.createOrder(member, new Delivery(), orderItem);

    orderRepository.save(order);
    return order.getId();
  }

  @Transactional
  public void cancelOrder(Long orderId) {
    Order order = orderRepository.findOne(orderId);

    order.cancel();
  }

  public Order findOnd(Long orderId) {
    return orderRepository.findOne(orderId);
  }
 }
