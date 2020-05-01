package com.example.rlone.article.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ArticleInformationDto {

  @NotNull private String aname;

  @NotNull private String content;

  private String picture;
}
