package com.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class Item {

  @Id @GeneratedValue
  @Column(name="item_id")
  private Long id;

  @Column
  private String name;

  @Column
  private int price;

  @Column
  private int stockQuantity;

  public void addStock(int quantity) {
    this.stockQuantity += quantity;
  }

  public void removeStock(int quantity) {
    if (this.stockQuantity - quantity < 0) {
      throw new IllegalStateException("수량은 0 이상이여야 합니다.");
    }

    this.stockQuantity -= quantity;
  }
}
