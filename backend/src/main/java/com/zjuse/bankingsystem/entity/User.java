package com.zjuse.bankingsystem.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id; 
    private String username; 
    private String password;
    @JsonProperty("id_number")
    private String idNumber;
    private String email; 
    @JsonProperty("phone_number")
    private String phoneNumber;
}
