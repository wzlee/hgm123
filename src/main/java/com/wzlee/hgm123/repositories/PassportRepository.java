package com.wzlee.hgm123.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.wzlee.hgm123.domain.Passport;

public interface PassportRepository extends CrudRepository<Passport, Serializable>{
	public List<Passport> findPassportByUpassid(String upassid);
	public List<Passport> findPassportByNickname(String nickname);
	public List<Passport> findPassportByEmail(String email);
	public List<Passport> findPassportByQqOpenid(String qqOpenid);
	public List<Passport> findPassportByTwOpenid(String twOpenid);
	public List<Passport> findPassportBySwOpenid(String swOpenid);
	public List<Passport> findPassportByYkOpenid(String ykOpenid);
	public Passport findPassportByLoginSequenceAndLoginTimestamp(String loginSequence,String loginTimestamp);
}
