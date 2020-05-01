package com.example.rlone.article.service;

import com.example.rlone.article.constant.ArticleCrudError;
import com.example.rlone.article.dto.ArticleDto;
import com.example.rlone.article.dto.ArticleInformationDto;
import com.example.rlone.article.entity.Article;
import com.example.rlone.article.repository.ArticleRepository;
import com.example.rlone.user.constant.UserCrudError;
import com.example.rlone.user.entity.User;
import com.example.rlone.user.repository.UserRepository;
import com.example.rlone.util.ServiceResult;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class ArticleService {

  @Autowired private ArticleRepository articleRepository;

  private ModelMapper modelMapper = new ModelMapper();

  @Autowired private UserRepository userRepository;

  public ServiceResult<List<Article>> listAll() {
    ServiceResult<List<Article>> result = new ServiceResult<>(false);

    result.setData(articleRepository.findAll());
    result.setSuccess(true);

    return result;
  }

  public ServiceResult addArticle(Integer id, ArticleDto articleDto) {
    ServiceResult result = new ServiceResult(false);
    User user = userRepository.findById(id).get();

    if (StringUtils.isEmpty(user)) {
      result.setMessageCode(UserCrudError.USER_NOT_FOUND);
      return result;
    }

    Article article = modelMapper.map(articleDto, Article.class);
    article.setUser(user);
    articleRepository.save(article);
    result.setSuccess(true);
    return result;
  }

  public ServiceResult deleteArticle(Integer id, Integer userId) {
    ServiceResult result = new ServiceResult(false);
    User user = userRepository.findById(userId).get();

    if (StringUtils.isEmpty(user)) {
      result.setMessageCode(UserCrudError.USER_NOT_FOUND);
      return result;
    }

    List<Article> articles = articleRepository.findAllByUser(user);
    Article article = articleRepository.findById(id).get();

    if (articles.isEmpty() || StringUtils.isEmpty(article)) {
      result.setMessageCode(ArticleCrudError.ARTICLE_NOT_FOUND);
      return result;
    }

    if (!articles.contains(article)) {
      result.setMessageCode(ArticleCrudError.AUTHOR_IS_NOT_THIS_USER);
      return result;
    } else if (articles.contains(article)) {
      articleRepository.delete(article);
      result.setSuccess(true);
    }

    return result;
  }

  public ServiceResult updateArticle(
      Integer id, Integer userId, ArticleInformationDto articleInformationDto) {
    ServiceResult result = new ServiceResult(false);
    Article article = articleRepository.findById(id).get();
    User user = userRepository.findById(userId).get();

    if (StringUtils.isEmpty(article)) {
      result.setMessageCode(ArticleCrudError.ARTICLE_NOT_FOUND);
      return result;
    }

    if (StringUtils.isEmpty(user)) {
      result.setMessageCode(UserCrudError.USER_NOT_FOUND);
      return result;
    }

    if (article.getUser().equals(user)) {
      modelMapper.map(articleInformationDto, article);
      articleRepository.save(article);
    } else if (!article.getUser().equals(user)) {
      result.setMessageCode(ArticleCrudError.AUTHOR_IS_NOT_THIS_USER);
      return result;
    }

    result.setSuccess(true);
    return result;
  }
}
