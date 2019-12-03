package com.karthik.rest.webservices.restfullwebservices.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ApiModel(value = "User model")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min = 2, message = "Name must be at least two chars")
    @ApiModelProperty(notes = "User name should be al]t least 2 chars")
    private String name;
    @Past
    private Date dateOfBirth;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;
}
