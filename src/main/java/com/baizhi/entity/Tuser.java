package com.baizhi.entity;

import lombok.Data;

@Data
public class Tuser {
    private String id;
    private String username;
    private String password;
    private String salt;
    private Integer age;
    private String phone;
}
