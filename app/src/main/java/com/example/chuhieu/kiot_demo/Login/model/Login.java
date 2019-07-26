package com.example.chuhieu.kiot_demo.Login.model;

public class Login {
    String UserName;
    String Password;
    String TenantCode;
    String TenantId;
    String Email;
    String Language;

    public Login(String userName, String password, String tenantCode, String tenantId, String email, String language) {
        UserName = userName;
        Password = password;
        TenantCode = tenantCode;
        TenantId = tenantId;
        Email = email;
        Language = language;
    }

    public Login() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getTenantCode() {
        return TenantCode;
    }

    public void setTenantCode(String tenantCode) {
        TenantCode = tenantCode;
    }

    public String getTenantId() {
        return TenantId;
    }

    public void setTenantId(String tenantId) {
        TenantId = tenantId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }
}
