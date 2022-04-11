package com.example.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.jpa.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Test
  @DisplayName("회원가입에 성공해야 한다.")
  void join() {
    Member member = new Member("user");

    Long savedId = memberService.join(member);
    Member savedMember = memberService.findById(savedId);

    assertThat(savedId).isEqualTo(member.getId());
    assertThat(savedMember.getName()).isEqualTo(member.getName());
    assertThat(savedMember).isEqualTo(member);
  }

  @Test
  @DisplayName("중복된 이름이 있으면 예외가 발생해야 한다.")
  void duplicate() {
    Member member = new Member("user");
    memberService.join(member);

    Member member2 = new Member("user");
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));
  }

}
