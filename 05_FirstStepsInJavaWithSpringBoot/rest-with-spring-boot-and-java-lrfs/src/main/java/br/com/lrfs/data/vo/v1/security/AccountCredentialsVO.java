package br.com.lrfs.data.vo.v1.security;

import java.io.Serializable;

public class AccountCredentialsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    public AccountCredentialsVO() {
    }

    public AccountCredentialsVO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    //generate setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password.toUpperCase();
    }

    //generate hashcode and equals

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime*result+((password == null)?0:password.hashCode());
        result = prime*result+((userName == null)?0:userName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass()!=obj.getClass()) return false;
        AccountCredentialsVO other = (AccountCredentialsVO) obj;
        if(password == null) {
            if(other.password!=null) return false;
        } else if(!password.equals(other.password)) return false;
        if(userName == null) {
            if(other.userName!=null) return false;
        } else if(!userName.equals(other.userName)) return false;
        return true;
    }

    //generate toString
    @Override
    public String toString() {
        return "AccountCredentialsVO [password=" + password + ", userName=" + userName + "]";
    }
    
}
