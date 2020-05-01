package com.example.rlone.article.controller;

import com.example.rlone.article.dto.ArticleDto;
import com.example.rlone.article.dto.ArticleInformationDto;
import com.example.rlone.article.entity.Article;
import com.example.rlone.article.repository.ArticleRepository;
import com.example.rlone.article.service.ArticleService;
import com.example.rlone.user.entity.User;
import com.example.rlone.user.repository.UserRepository;
import com.example.rlone.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Provider;
import java.util.Date;
import java.util.List;

@RestController
public class ArticleController {

  @Autowired private UserRepository userRepository;

  @Autowired private ArticleService articleService;

  @Autowired private ArticleRepository articleRepository;

  private final String baseUrl = "/api/article";

  @GetMapping(baseUrl)
  @ResponseBody
  public ResponseEntity list() {
    ServiceResult<List<Article>> result = articleService.listAll();
    return ResponseEntity.ok(result.getData());
  }

  @PostMapping(baseUrl + "/{userId}")
  @ResponseBody
  public ResponseEntity insert(
      @PathVariable(name = "userId") Integer id, @Valid @RequestBody ArticleDto articleDto) {
    ServiceResult result = articleService.addArticle(id, articleDto);

    if (result.getSuccess()) {
      return ResponseEntity.ok("插入完成");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }

  @DeleteMapping(baseUrl + "/{id}/user/{userId}")
  @ResponseBody
  public ResponseEntity delete(
      @PathVariable(name = "id") Integer id, @PathVariable(name = "userId") Integer userId) {
    ServiceResult result = articleService.deleteArticle(id, userId);

    if (result.getSuccess()) {
      return ResponseEntity.ok("删除完成");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }

  @PutMapping(baseUrl + "/{id}")
  @ResponseBody
  public ResponseEntity update(
      @PathVariable(name = "id") Integer id,
      @RequestParam(name = "userId") Integer userId,
      @Valid @RequestBody ArticleInformationDto articleInformationDto) {
    ServiceResult result = articleService.updateArticle(id, userId, articleInformationDto);

    if (result.getSuccess()) {
      return ResponseEntity.ok("修改成功");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }
}
