package com.example.rlone.util;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Getter
@Setter
public class ServiceResult<T> {

  @NotNull Boolean success;

  T data;

  int messageCode = -1;

  public ServiceResult(boolean success) {
    this.success = success;
  }
}
