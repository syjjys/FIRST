package com.example.rlone.user.service;

import com.example.rlone.user.constant.UserCrudError;
import com.example.rlone.user.dto.UserDto;
import com.example.rlone.user.dto.UserEmailDto;
import com.example.rlone.user.dto.UserInformationDto;
import com.example.rlone.user.dto.UserPasswordDto;
import com.example.rlone.user.entity.Role;
import com.example.rlone.user.entity.RoleName;
import com.example.rlone.user.entity.User;
import com.example.rlone.user.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import com.example.rlone.user.repository.UserRepository;
import com.example.rlone.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  RoleRepository roleRepository;

  @Autowired private UserRepository userRepository;

  private ModelMapper modelMapper = new ModelMapper();

  public ServiceResult addUser(UserDto userDto) {
    ServiceResult result = new ServiceResult(false);

    if (userDto.getUsername() == null || userDto.getEmail() == null) {
      result.setMessageCode(UserCrudError.USERNAME_OR_EMAIL_NOT_FOUND);
      return result;
    }

    if (userDto.getPassword() == null) {
      result.setMessageCode(UserCrudError.PASSWORD_NOT_FOUND);
      return result;
    }

    Optional<User> isExisted = userRepository.findByUsernameOrEmail(userDto.getUsername(),userDto.getEmail());

    if (!isExisted.isEmpty()) {
      result.setMessageCode(UserCrudError.USER_HAS_EXIST);
      return result;
    }

    User user = modelMapper.map(userDto, User.class);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    Role userRole = roleRepository.findByName(RoleName.ROLE_USER).get();
    user.setRoles(Collections.singleton(userRole));
    user.setCreateAt(new Date());
    userRepository.save(user);
    result.setSuccess(true);
    return result;
  }

  public ServiceResult updateUser(UserInformationDto userInformationDto) {
    ServiceResult result = new ServiceResult(false);

    User user = userRepository.findByUsername(userInformationDto.getUsername()).get();

    if (StringUtils.isEmpty(user)) {
      result.setMessageCode(UserCrudError.USER_NOT_FOUND);
    }

    modelMapper.map(userInformationDto, user);
    userRepository.save(user);
    result.setSuccess(true);
    return result;
  }

  public ServiceResult changeUserPassword(String username, UserPasswordDto userPasswordDto) {
    ServiceResult result = new ServiceResult(false);

    User user = userRepository.findByUsername(username).get();

    if (passwordEncoder.matches(userPasswordDto.getOldPassword(),user.getPassword())) {
      user.setPassword(passwordEncoder.encode(userPasswordDto.newPassword));
      userRepository.save(user);
      result.setSuccess(true);
      return result;
    }

    result.setMessageCode(UserCrudError.UNKNOWN_ERROR);
    return result;
  }
}
