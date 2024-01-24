package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int id;
    private String name;
    private String birthday;
    private int amount;
    private String nickname;
    private String password;


}
