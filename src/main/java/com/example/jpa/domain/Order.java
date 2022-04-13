package com.example.jpa.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Lombok;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

  @Id @GeneratedValue
  @Column(name = "order_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @OneToMany(mappedBy = "order")
  private List<OrderItem> orderItems = new ArrayList<>();

  @OneToOne
  @JoinColumn(name="delivery_id")
  private Delivery delivery;

  private LocalDateTime orderDate;

  private OrderStatus orderStatus;

  /* 연관 관계 메소드 */
  public void setMember(Member member) {
    this.member = member;
    member.getOrders().add(this);
  }

  public void addOrderItem(OrderItem item) {
    this.orderItems.add(item);
    item.setOrder(this);
  }

  public void setDelivery(Delivery delivery) {
    this.delivery = delivery;
    delivery.setOrder(this);
  }

  /* 생성 메소드 */
  public static Order createOrder(Member member, Delivery delivery, OrderItem ...orderItems) {
    Order order = new Order();
    order.setMember(member);
    order.setDelivery(delivery);

    for (OrderItem orderItem : orderItems) {
      order.addOrderItem(orderItem);
    }

    order.orderDate = LocalDateTime.now();
    order.orderStatus = OrderStatus.ORDER;

    return order;
  }

  /* 비즈니스 로직 */
  public void cancel() {
    this.orderStatus = OrderStatus.CANCEL;


    for (OrderItem orderItem : getOrderItems()) {
      orderItem.cancel();
    }
  }
}
