package com.laurus.hungry.entity;

import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@NamePattern("%s|name")
@Table(name = "HUNGRY_DISH")
@Entity(name = "hungry_Dish")
public class Dish extends StandardEntity {
    private static final long serialVersionUID = 4641624174812428616L;

    @Column(name = "NAME")
    protected String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PICTURE_ID")
    protected FileDescriptor picture;

    @Column(name = "PRICE")
    protected Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    protected Store store;

    @Transient
    @MetaProperty
    protected String namePrice;

    public FileDescriptor getPicture() {
        return picture;
    }

    public void setPicture(FileDescriptor picture) {
        this.picture = picture;
    }

    public String getNamePrice() {
        return name + "/" + price;
    }

    public void setNamePrice(String namePrice) {
        this.namePrice = namePrice;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}