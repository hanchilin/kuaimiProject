package com.kuaimi.domain.entity;

import com.kuaimi.domain.BasePersistable;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : chenwei
 * @create : 2017-10-04 23:22
 */
@Data
@Entity
@Table(name = "users")
public class User extends BasePersistable {

    @NotNull
    private String name;

    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[^4,\\D])|(17[0-9])|(18[0,5-9]))\\d{8}$")
    private String phone;

    @NotNull
    @Column(name = "hashedPassword")
    private String password;

    @NotNull
    private UserStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    private Date passwordUpdatedAt;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSuccessfulLogin;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFailedLogin;

    @ManyToMany()
    @JoinTable(name = "users_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();
}
