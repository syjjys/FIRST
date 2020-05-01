package com.example.rlone.user.controller;

import com.example.rlone.security.JwtAuthenticationResponse;
import com.example.rlone.security.JwtTokenProvider;
import com.example.rlone.security.UserPrincipal;
import com.example.rlone.user.dto.*;
import com.example.rlone.user.entity.Role;
import com.example.rlone.user.entity.RoleName;
import com.example.rlone.user.entity.User;
import com.example.rlone.user.repository.RoleRepository;
import com.example.rlone.user.repository.UserRepository;
import com.example.rlone.user.service.MailServiceImpl;
import com.example.rlone.user.service.UserService;
import com.example.rlone.util.ServiceResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@RestController
public class UserController  extends Thread  {

  @Autowired MailServiceImpl mailService;

  @Autowired AuthenticationManager authenticationManager;

  @Autowired UserRepository userRepository;

  @Autowired JwtTokenProvider tokenProvider;

  @Autowired private UserService userService;

  private final String baseUrl = "/api/auth";

  @PostMapping(baseUrl + "/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginDto userLoginDto) {
    System.out.println("11122223333333");
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                userLoginDto.getUsernameOrEmail(), userLoginDto.getPassword()));
    System.out.println("1112222333355555555555555");
    SecurityContextHolder.getContext().setAuthentication(authentication);
    System.out.println("11122223333555555555555588888888888885");
    String jwt = tokenProvider.generateToken(authentication);
    System.out.println("1112222333355555555555558888888888888500000000");
    System.out.println(ResponseEntity.ok(new JwtAuthenticationResponse(jwt)));
    return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
  }

  @PostMapping(baseUrl + "/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody UserDto userDto) {
    ServiceResult result = userService.addUser(userDto);

    if (result.getSuccess()) {
      return ResponseEntity.ok().body("成功");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }

  @PutMapping(baseUrl)
  @ResponseBody
  public ResponseEntity update(@Valid @RequestBody UserInformationDto userInformationDto) {
    ServiceResult result = userService.updateUser(userInformationDto);

    if (result.getSuccess()) {
      return ResponseEntity.ok("成功");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }

  @PatchMapping(baseUrl + "/password")
  @ResponseBody
  public ResponseEntity changePassword(@Valid @RequestBody UserPasswordDto userPasswordDto) {
    //    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //    UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
    // System.out.println(userPrincipal.getUsername());
    ServiceResult result =
        userService.changeUserPassword(userPasswordDto.getUsernameOrEmail(), userPasswordDto);

    if (result.getSuccess()) {
      return ResponseEntity.ok("success");
    }

    return ResponseEntity.badRequest().body(result.getMessageCode());
  }

  @PostMapping(baseUrl + "/email")
  @ResponseBody
  public ResponseEntity emailSender() throws Exception {
    ServiceResult result = mailService.sendMessage();
    return ResponseEntity.ok(result.getData());
  }



//  @PostMapping(baseUrl + "/email")
//  @ResponseBody
//  public ResponseEntity game(){
//    for (int i = 0; i < 9999; i++) {
//      User user = new User();
//      user.setPassword("112121212");
//      user.setDescription("efsjfej");
//      user.setGender(false);
//      user.setUsername("saoygonjio" + i);
//      userRepository.save(user);
//    }
//    return ResponseEntity.ok().build();
//  }
}
