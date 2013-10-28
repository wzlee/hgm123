package com.wzlee.hgm123.repositories;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.wzlee.hgm123.domain.Website;


public interface WebsiteRepository extends CrudRepository<Website, Serializable>{
	List<Website> findWebsiteBySld(String sld);
	List<Website> findWebsiteByCreator(String creator);
}
