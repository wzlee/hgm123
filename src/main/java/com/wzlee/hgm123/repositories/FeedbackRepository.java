package com.wzlee.hgm123.repositories;

import java.io.Serializable;

import org.springframework.data.repository.CrudRepository;


import com.wzlee.hgm123.domain.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Serializable>{
}
