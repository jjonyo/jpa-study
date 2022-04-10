package com.example.jpa.repository;

import com.example.jpa.domain.Member;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

  @PersistenceContext
  EntityManager em;

  public Long save(Member member){
    em.persist(member);
    return member.getId();
  }

  public List<Member> findAll() {
    return em.createQuery("SELECT m FROM Member m", Member.class)
        .getResultList();
  }

  public Member findById(Long id){
    return em.find(Member.class, id);
  }

  public List<Member> findByName(String name) {
    return em.createQuery("SELECT m FROM Member m WHERE m.name=:name", Member.class)
        .setParameter("name", name)
        .getResultList();
  }


}
