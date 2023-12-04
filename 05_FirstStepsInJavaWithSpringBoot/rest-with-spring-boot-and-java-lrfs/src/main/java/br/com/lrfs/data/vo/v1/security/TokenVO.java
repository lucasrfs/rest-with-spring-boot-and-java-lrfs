package br.com.lrfs.data.vo.v1.security;


import java.util.Date;
import java.util.Objects;

public class TokenVO {

    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String accessToken;
    private String refreshToken;

    //add constructor with all fields
    public TokenVO(String username, Boolean authenticated, Date now, Date validity, String accessToken, String refreshToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = now;
        this.expiration = validity;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    //add default constructor
    public TokenVO() {
    }

    //generate getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    //generate hash and equals
    @Override
    public int hashCode() {
        return Objects.hash(username, authenticated, created, expiration, accessToken, refreshToken);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TokenVO tokenVO = (TokenVO) obj;
        return Objects.equals(username, tokenVO.username) &&
                Objects.equals(authenticated, tokenVO.authenticated) &&
                Objects.equals(created, tokenVO.created) &&
                Objects.equals(expiration, tokenVO.expiration) &&
                Objects.equals(accessToken, tokenVO.accessToken) &&
                Objects.equals(refreshToken, tokenVO.refreshToken);
    }
}
