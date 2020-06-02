package com.anxinpha.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.util.Date;


/**
 * @author 尹硕硕
 * @description
 **/
@Table(name = "tb_user")
public class User {
    @Id
    @Null
    private Long id;
    @Length(min = 4,max = 30,message = "用户名需要4-30位！")
    private String username;
    @JsonIgnore
    @Length(min = 6,max = 13,message = "密码必须设置6-13位!")
    private String password;
    @Pattern(regexp = "^1[356789]\\d{9}$",message = "手机号不合法！")
    private String phone;
    @Email(message = "邮箱不合法")
    private String email;
    private Integer roleId;

    private Date created;

    private Date updated;

    private String sex;

    private Integer state;

    private String file;

    private String description;
    @JsonIgnore
    private String salt;

    private Boolean isShop;
    @Transient
    private Integer points;
    @Transient
    private Long balance;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Boolean getIsShop() {
        return isShop;
    }

    public void setIsShop(Boolean shop) {
        isShop = shop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
}
