package org.example.emarketmall.entity;

import org.example.emarketmall.common.BaseEntity;

/**
 * @Description: 用户信息
 * @author: april
 * @date: 2022年05月30日 17:31
 */
public class UserInfo extends BaseEntity {
    private String name;
    private String loginName;
    private String avatar;
    private String email;
    private String phone;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String name, String loginName, String phone, String password) {
        this.name = name;
        this.loginName = loginName;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + getId() + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", avatar='" + avatar + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", delFlag=" + getDelFlag() + '\'' +
                ", createBy=" + getCreateBy() + '\'' +
                ", createTime=" + getCreateTime() + '\'' +
                ", updateBy=" + getUpdateBy() + '\'' +
                ", updateTime=" + getUpdateTime() + '\'' +
                ", REMARK=" + getRemark() + '\'' +
                '}';
    }
}
