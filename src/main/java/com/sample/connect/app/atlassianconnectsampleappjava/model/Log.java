package com.sample.connect.app.atlassianconnectsampleappjava.model;

import jakarta.persistence.*;

@Entity
@Table(name = "logs")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column
    public int tenantId;

    @Column
    public String message;

    @Column
    public String data;
}
