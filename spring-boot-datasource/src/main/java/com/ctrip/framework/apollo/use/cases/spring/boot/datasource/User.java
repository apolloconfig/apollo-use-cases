package com.ctrip.framework.apollo.use.cases.spring.boot.datasource;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by kl on 2018/6/25.
 * Content :
 */
@Entity
public class User {

    @Id
    private int id;
    private String name;

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
}
