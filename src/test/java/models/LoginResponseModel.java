package models;

import lombok.Data;

@Data
public class LoginResponseModel {
    private String token;
    private String error;
}

