package com.example.rlone.user.dto;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Getter
@Setter
public class UserEmailDto {
    private String id;//邮件接收人

    private String from;

    private String subject;//邮件主题

    private String text;//邮件内容

    private Date sentDate;//发送时间

    private String cc;//抄送

    private String bcc;//密送

    private String status;//状态

    private String error;//报错信息

    @JsonIgnore
    private MultipartFile[] multipartFiles;//邮件附件
}