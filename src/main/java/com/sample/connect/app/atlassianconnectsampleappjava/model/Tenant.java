package com.sample.connect.app.atlassianconnectsampleappjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tenants")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    private String host;

    @Column
    private String sharedSecret;

    @Column
    private String clientKey;

    public Tenant(String host, String sharedSecret, String clientKey) {
        this.host = host;
        this.sharedSecret = sharedSecret;
        this.clientKey = clientKey;
    }

    public Tenant() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getSharedSecret() {
        return sharedSecret;
    }

    public void setSharedSecret(String sharedSecret) {
        this.sharedSecret = sharedSecret;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }
}
