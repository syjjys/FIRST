package com.example.rlone.article.repository;

import com.example.rlone.article.entity.Article;
import com.example.rlone.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
  List<Article> findAll();

  //  @Query(nativeQuery = true,value = "SELECT * FROM ARTICLE WHERE USER_ID = :userId")
  //  List<Article> findByUserId(@Param("userId") Integer userId);

  List<Article> findAllByUser(User user);
}
