package com.laurus.hungry.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@Table(name = "HUNGRY_ORDER_DETAIL")
@Entity(name = "hungry_OrderDetail")
public class OrderDetail extends StandardEntity {
    private static final long serialVersionUID = 2406827851552199347L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    @Column(name = "USER_KEY")
    protected String userKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    protected Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DISH_ID")
    protected Dish dish;

    @Column(name = "QUANTITY")
    protected Integer quantity;

    @Column(name = "TOTAL_PRICE")
    protected Double totalPrice;

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}