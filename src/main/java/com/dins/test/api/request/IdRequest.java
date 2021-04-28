package com.dins.test.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class IdRequest {

    private long id;

    public IdRequest(long id) {
        this.id = id;
    }

    public IdRequest() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
