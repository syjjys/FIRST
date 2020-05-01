package com.example.rlone.user.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user" , uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "USERNAME"
        }),
        @UniqueConstraint(columnNames = {
                "EMAIL"
        })
})
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "EMAIL")
  private String email;

  @JsonIgnore
  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "GENDER")
  private boolean gender;

  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "CREATE_AT")
  private Date createAt;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "role_has_user",
          joinColumns = @JoinColumn(name = "user_ID"),
          inverseJoinColumns = @JoinColumn(name = "role_ID"))
  private Set<Role> roles = new HashSet<>();

  public boolean getGender() {
    return this.gender;
  }
}
