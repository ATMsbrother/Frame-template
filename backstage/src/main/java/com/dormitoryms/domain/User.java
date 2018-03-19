package com.dormitoryms.domain;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户模型
 *
 * @author hackyo
 * Created on 2018/3/18 11:53.
 */
@Document
@Data
public class User implements Serializable {

    @Id
    private String id;

    @Length(min = 1, max = 16, message = "用户名长度必须在1-16位之间")
    @Indexed(unique = true, direction = IndexDirection.DESCENDING, dropDups = true)
    private String username;

    @Pattern(regexp = "^(?![^a-zA-Z]+$)(?!\\D+$).{8,32}$", message = "密码长度必须在8-32位之间，且必须包含数字和字母")
    private String password;

    private String lastLoginIp;

    private Date lastPasswordResetTime;

    private List<String> roles;

}
