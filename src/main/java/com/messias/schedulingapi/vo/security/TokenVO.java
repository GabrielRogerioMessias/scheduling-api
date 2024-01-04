package com.messias.schedulingapi.vo.security;

import java.util.Date;
import java.util.Objects;

public class TokenVO {
    private String username;
    private Boolean authenticated;
    private Date created;
    private Date expiration;
    private String acessToken;
    private String reflashToken;

    public TokenVO() {
    }

    public TokenVO(String username, Boolean authenticated, Date created, Date expiration, String acessToken, String reflashToken) {
        this.username = username;
        this.authenticated = authenticated;
        this.created = created;
        this.expiration = expiration;
        this.acessToken = acessToken;
        this.reflashToken = reflashToken;
    }

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

    public String getAcessToken() {
        return acessToken;
    }

    public void setAcessToken(String acessToken) {
        this.acessToken = acessToken;
    }

    public String getReflashToken() {
        return reflashToken;
    }

    public void setReflashToken(String reflashToken) {
        this.reflashToken = reflashToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenVO tokenVO = (TokenVO) o;
        return Objects.equals(username, tokenVO.username) && Objects.equals(authenticated, tokenVO.authenticated) && Objects.equals(created, tokenVO.created) && Objects.equals(expiration, tokenVO.expiration) && Objects.equals(acessToken, tokenVO.acessToken) && Objects.equals(reflashToken, tokenVO.reflashToken);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, authenticated, created, expiration, acessToken, reflashToken);
    }
}
