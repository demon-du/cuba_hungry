package com.laurus.hungry.web.screens.orderdetail;

import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.laurus.hungry.entity.Dish;
import com.laurus.hungry.entity.OrderDetail;
import com.laurus.hungry.web.screens.order.OrderEdit;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/6 11:34
 * Description :
 */
@UiController("hungry_OrderDetail.edit")
@UiDescriptor("order-detail-edit.xml")
@EditedEntityContainer("orderDetailDc")
@LoadDataBeforeShow
public class OrderDetailEdit extends StandardEditor<OrderDetail> {

    @Inject
    private Screens screens;

    @Inject
    private CollectionLoader<Dish> dishesDl;

    @Inject
    private CollectionContainer<Dish> dishesDc;

    @Inject
    private UserSession userSession;

    private List<OrderDetail> orderDetails;

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        List<UUID> dishIds = orderDetails.stream().map(od -> od.getDish().getId()).collect(Collectors.toList());
        dishIds.add(UUID.randomUUID());
        dishesDl.setParameter("store", getEditedEntity().getStore());
        dishesDl.setParameter("dishIds", dishIds);
        getScreenData().loadAll();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        OrderDetail orderDetail = getEditedEntity();
        orderDetail.setTotalPrice(new BigDecimal(orderDetail.getDish().getPrice()).multiply(new BigDecimal(orderDetail.getQuantity())).doubleValue());

        if (orderDetail.getUserKey() == null) {
            orderDetail.setUserKey(userSession.getUser().getLogin() + formatter.format(now));
        }
    }

    @Subscribe
    public void onAfterClose(AfterCloseEvent event) {
        for (Screen screen : screens.getOpenedScreens().getActiveScreens()) {
            if (screen.getClass().equals(OrderEdit.class)) {
                OrderEdit orderEdit = (OrderEdit) screen;
                orderEdit.detailLoad();
                break;
            }
        }
    }


}