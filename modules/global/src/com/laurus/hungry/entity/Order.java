package com.laurus.hungry.entity;

import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Table(name = "HUNGRY_ORDER")
@Entity(name = "hungry_Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = 3063299988574054895L;

    @Column(name = "NR")
    protected String nr;

    @Column(name = "USER_KEY")
    protected String userKey;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    protected User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    protected Store store;

    @Column(name = "ORDER_AT")
    protected LocalDateTime orderAt;

    @Column(name = "QUANTITY")
    protected Integer quantity;

    @Column(name = "TOTAL_PRICE")
    protected Double totalPrice;

    @OneToMany(mappedBy = "order")
    protected List<OrderDetail> orderDetails;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

}