package com.example.jpa.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
public class Member {

  @Id
  @GeneratedValue
  @Column(name = "member_id")
  private Long id;

  @Column
  private String name;

  @Embedded
  private Address address;

  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<>();

  public Member(String name) {
    this.name = name;
  }
}
