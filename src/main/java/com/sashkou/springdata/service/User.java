package com.sashkou.springdata.service;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Document(collection = "users")
public class User {

    @Id
    @JsonAlias("_id")
    private long id;

    @NotBlank(message = "name can't be null or empty")
    private String name;

    @Min(value = 0, message = "age can't be lt 0")
    private int age;

    @CreatedDate
    private Date expireAt;
}
