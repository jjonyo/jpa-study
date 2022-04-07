package com.example.jpa.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@DiscriminatorValue("book")
public class Book extends Item {

  @Column
  private String artist;

  @Column
  private String etc;
}
