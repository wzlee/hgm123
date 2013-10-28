package com.wzlee.hgm123.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


import com.googlecode.ehcache.annotations.Cacheable;
import com.wzlee.hgm123.domain.Advertise;

public interface AdvertiseRepository extends CrudRepository<Advertise, Serializable>{
	@Cacheable(cacheName="advertises") 
	public List<Advertise> findAdvertiseByOpenTime(String openTime);
//	@Cacheable(cacheName="advertises")
	public List<Advertise> findAdvertiseBySource(String source);
}
