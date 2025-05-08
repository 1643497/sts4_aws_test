package com.test.pro04.member.dao;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.test.pro04.member.dto.MemberDTO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Repository
public class MemberDAOImpl implements MemberDAO{

@PersistenceContext
private EntityManager em;
	@Override
	public List<MemberDTO> memberList() {
		
		return em.createQuery("select m from Member m order by joinDate desc",MemberDTO.class).getResultList();
		
	}
	@Override
	public void joinMember(MemberDTO dto) {
		
		em.persist(dto);
	}
	@Override
	public MemberDTO selectById(String id) {
		// TODO Auto-generated method stub
		return em.find(MemberDTO.class, id);
	}
	@Override
	public void updateMember(MemberDTO dto) {
		em.merge(dto);
		
	}
	@Override
	public void deleteMember(String id) {
		 em.createQuery("DELETE FROM Member m WHERE m.id = :id")
	      .setParameter("id", id)
	      .executeUpdate();
	}
	@Override
	public boolean login(String id, String pwd) {
	    List<String> results = em.createQuery("select m.pwd from Member m where m.id = :id", String.class)
	            .setParameter("id", id)
	            .getResultList();

	    if (results.isEmpty()) {
	        return false; // ID 없음 → 로그인 실패
	    }

	    String encryptedPwd = results.get(0);
	    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	    return encoder.matches(pwd, encryptedPwd);
	}
		

		
	

}
