package com.example.hive.dto.request;

//import com.example.hive.entity.Address;

import com.example.hive.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown=true)
public class UserRegistrationRequestDto {

    @NotBlank(message = "name cannot be empty")
//    @Pattern(regexp = "[a-zA-Z]*", message = "name can only have letters")
    @Size(message = "fullName character length cannot be less than 2 and more than 100", min = 2, max = 200)
    private String fullName;


    @NotBlank(message = "email cannot be empty")
    @Email(message = "Must be a valid email!")
    private String email;


    private String password;

    //Interface Annotation and Validator to create custom Annotation

//    private String confirmPassword;


    @NotBlank(message = "phoneNumber cannot be empty")
    @Size(message = "Phone number character length cannot be less than 10 and more than 16", min = 10, max = 16)
    private String phoneNumber;

    //    @NotBlank(message = "role field cannot be empty")
    private Role role;

//    @NotBlank(message = "validId field cannot be empty")
//    @JsonIgnore
//    private MultipartFile validId;
//    private String validIdUrl;

    //    @NotBlank(message = "address field cannot be null")
    private String address;

}
