package com.wzlee.hgm123.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wzlee.hgm123.domain.Youkuer;

public interface YoukuerRepository extends CrudRepository<Youkuer, Serializable>{
	public List<Youkuer> findYoukuerByName(String name);
}
