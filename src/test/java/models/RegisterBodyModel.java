package models;

import lombok.Data;

@Data
public class RegisterBodyModel {
    private String email;
    private String password;
}
