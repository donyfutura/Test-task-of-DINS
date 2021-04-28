package com.dins.test.api.request;

import com.dins.test.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

public class UserRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    public UserRequest(String name) {
        this.name = name;

    }

    public UserRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

