package com.example.jpa.service;

import com.example.jpa.domain.Member;
import com.example.jpa.repository.MemberRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

  private final MemberRepository memberRepository;

  @Transactional
  public Long join(Member member) {
    validateDuplicateMember(member);

    return memberRepository.save(member);
  }

  public void validateDuplicateMember(Member member) {
    String name = member.getName();
    List<Member> members = memberRepository.findByName(name);

    if (!members.isEmpty()) {
      throw new IllegalStateException("중복된 닉네임 입니다.");
    }
  }

  public List<Member> findAll() {
    return memberRepository.findAll();
  }

  public Member findById(Long id) {
    return memberRepository.findById(id);
  }
}
