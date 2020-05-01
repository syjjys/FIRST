package com.example.rlone.article.entity;

import com.example.rlone.user.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "ARTICLE")
public class Article {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private int id;

  @Column(name = "ANAME")
  private String aname;

  @Column(name = "CONTENT")
  private String content;

  @Column(name = "PICTURE")
  private String picture;

  @Column(name = "CREATE_AT")
  private Date createAt;

  @Column(name = "UPDATE_AT")
  @UpdateTimestamp
  private Date updateAt;

  @Column(name = "USER_ID", insertable = false, updatable = false)
  private int userId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "USER_ID")
  @JsonIgnoreProperties(value = {"hibernateLazyInitializer"})
  private User user;
}
