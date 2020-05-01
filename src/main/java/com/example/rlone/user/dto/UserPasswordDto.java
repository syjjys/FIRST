package com.example.rlone.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class UserPasswordDto {
  @NotNull public String usernameOrEmail;

  @NotNull public String oldPassword;

  @NotNull public String newPassword;
}
