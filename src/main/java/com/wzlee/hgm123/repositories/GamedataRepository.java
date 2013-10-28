package com.wzlee.hgm123.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wzlee.hgm123.domain.Gamedata;

public interface GamedataRepository extends CrudRepository<Gamedata, Serializable>{
	public List<Gamedata> findGamedataByPid(String pid);
	public List<Gamedata> findGamedataByAid(String aid);
}
