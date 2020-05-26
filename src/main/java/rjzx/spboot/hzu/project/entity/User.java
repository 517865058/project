package rjzx.spboot.hzu.project.entity;

import java.io.Serializable;

/**
 * 用户表(User)实体类
 *
 * @author makejava
 * @since 2020-05-24 11:44:52
 */

public class User implements Serializable {
    private static final long serialVersionUID = -44384175058896229L;
    
    private String userid;
    
    private String userpwd;
    /**
    * 用户姓名
    */
    private String username;
    /**
    * 是否验证
    */
    private Object permission;
    /**
    * 角色：0学生；1指导教师；2二级学院负责人；3教务处负责人；4评审专家
    */
    private Integer role;
    /**
    * 团队ID
    */
    private String team;
    
    private String phone;
    
    private String email;
    /**
    * 二级学院
    */
    private String college;


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Object getPermission() {
        return permission;
    }

    public void setPermission(Object permission) {
        this.permission = permission;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

}