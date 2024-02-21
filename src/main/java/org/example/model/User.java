package org.example.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class User {
    private int id;
    private String name;
    private Date birthday;
    private int amount;
    private String nickname;
    private String password;
}
