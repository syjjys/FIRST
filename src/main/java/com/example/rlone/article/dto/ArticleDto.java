package com.example.rlone.article.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class ArticleDto {

  @NotNull private String aname;

  @NotNull private String content;

  private String picture;

  private Date createAt = new Date();
}
