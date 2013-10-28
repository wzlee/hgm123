package com.wzlee.hgm123.repositories;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

import com.wzlee.hgm123.domain.IpData;

public interface IpDataRepository extends CrudRepository<IpData, Serializable> ,MongoRepository<IpData, Serializable>{
}
