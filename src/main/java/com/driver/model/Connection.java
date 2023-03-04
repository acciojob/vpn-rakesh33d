package com.driver.model;

import javax.persistence.*;

@Entity
@Table(name = "connections")
public class Connection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // it is child w.r.t user entity
    @ManyToOne
    @JoinColumn
    private User user;

    //it is child w.r.t service provider entity
    @ManyToOne
    @JoinColumn
    private ServiceProvider serviceProvider;

    public Connection() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}
