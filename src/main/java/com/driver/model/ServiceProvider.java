package com.driver.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "service_providers")
public class ServiceProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    //it is child w.r.t admin entity
    @ManyToOne
    @JoinColumn
    private Admin admin;

    //it is parent w.r.t. userEntity
    @ManyToMany(mappedBy = "serviceProviderList",cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    //it parent w.r.t country entity
    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Country>countryList = new ArrayList<>();

    // it is parent w.r.t. connection entity
    @OneToMany(mappedBy = "serviceProvider",cascade = CascadeType.ALL)
    private List<Connection>connectionList = new ArrayList<>();

    public ServiceProvider() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public List<Connection> getConnectionList() {
        return connectionList;
    }

    public void setConnectionList(List<Connection> connectionList) {
        this.connectionList = connectionList;
    }
}
