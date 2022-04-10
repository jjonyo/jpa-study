package com.example.jpa.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @Mock
  static MemberRepository memberRepository;

  @InjectMocks
  static MemberService memberService;

  private Member member = new Member("user");



//
//  @Test
//  @DisplayName("회원가입에 성공해야 한다.")
//  void join() {
//    given(memberRepository.findByName(member.getName())).willReturn(new ArrayList<>());
//    given(memberRepository.save(member)).willReturn(1L);
//
//    Long savedId = memberService.join(member);
//
//    assertThat(savedId).isEqualTo(1L);
//  }

  @Test
  @DisplayName("중복된 이름이 있으면 예외가 발생해야 한다.")
  void duplicate() {
    List<Member> findByNameReturnList = new ArrayList<>();
    findByNameReturnList.add(new Member("user"));

    
    given(memberRepository.findByName(member.getName())).willThrow(IllegalStateException.class);


    assertThrows(IllegalStateException.class, () -> memberService.join(member));
  }

}
