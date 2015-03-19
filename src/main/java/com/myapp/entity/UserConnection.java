package com.myapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "UserConnection")
public class UserConnection {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "userID")
    private String userId;

    @Column(name = "providerID", nullable = false)
    private String providerId;

    @Column(name = "providerUserID")
    private String providerUserId;

    @Column(name = "rank", nullable = false)
    private int rank;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "profileUrl")
    private String profileUrl;

    @Column(name = "imageUrl")
    private String imageUrl;

    @Column(name = "accessToken", nullable = false)
    private String accessToken;

    @Column(name = "secret")
    private String secret;

    @Column(name = "refreshToken")
    private String refreshToken;

    @Column(name = "expireTime")
    private Long expireTime;

    public UserConnection(String userId, String providerId, String providerUserId, int rank, String displayName, String profileUrl, String imageUrl, String accessToken, String secret, String refreshToken, Long expireTime) {
        this.userId = userId;
        this.providerId = providerId;
        this.providerUserId = providerUserId;
        this.rank = rank;
        this.displayName = displayName;
        this.profileUrl = profileUrl;
        this.imageUrl = imageUrl;
        this.accessToken = accessToken;
        this.secret = secret;
        this.refreshToken = refreshToken;
        this.expireTime = expireTime;
    }

    public UserConnection() {
    }

    public String getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public String getProviderUserId() {
        return providerUserId;
    }

    public void setProviderUserId(String providerUserId) {
        this.providerUserId = providerUserId;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }
}
