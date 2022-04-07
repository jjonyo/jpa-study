package com.example.jpa.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.jpa.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberRepositoryTest {

  @Autowired
  MemberRepository memberRepository;

  @Test
  void save() {
    Member member = new Member();
    member.setName("user2");

    Long id = memberRepository.save(member);

    Member savedMember = memberRepository.findById(id);

    assertThat(savedMember.getName()).isEqualTo(member.getName());
    assertThat(savedMember.getId()).isNotNull();
  }
}
