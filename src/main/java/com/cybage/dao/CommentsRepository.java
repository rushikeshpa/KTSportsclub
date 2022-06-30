package com.cybage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cybage.pojo.Comments;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Integer> {

}
