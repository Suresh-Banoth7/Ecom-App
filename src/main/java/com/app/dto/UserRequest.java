package com.app.dto;


import com.app.model.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequest {

    private String name;
    private String email;
    private String phone;
    private AddressDTO address;

}
