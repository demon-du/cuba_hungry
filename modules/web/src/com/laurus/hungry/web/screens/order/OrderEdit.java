package com.laurus.hungry.web.screens.order;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.laurus.hungry.entity.Order;
import com.laurus.hungry.entity.OrderDetail;
import com.laurus.hungry.entity.Store;
import com.laurus.hungry.web.screens.orderdetail.OrderDetailEdit;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/4 12:21
 * Description :
 */
@UiController("hungry_Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {

    @Inject
    private ScreenBuilders screenBuilders;

    @Inject
    private CollectionLoader<OrderDetail> orderDetailsDl;

    @Inject
    private CollectionContainer<OrderDetail> orderDetailsDc;


    @Inject
    private UserSession userSession;

    @Inject
    private Metadata metadata;

    @Inject
    private DataManager dataManager;

    @Inject
    private GroupTable<OrderDetail> orderDetailTable;



    @Subscribe("storeField")
    public void onStoreFieldValueChange(HasValue.ValueChangeEvent<Store> event) {
        detailLoad();
    }

    @Subscribe
    public void onBeforeShow(BeforeShowEvent event) {
        detailLoad();
        getScreenData().loadAll();
    }

    public void detailLoad() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        orderDetailsDl.setParameter("order", getEditedEntity());
        orderDetailsDl.setParameter("store", getEditedEntity().getStore());
        orderDetailsDl.setParameter("userKey", userSession.getUser().getLogin() + formatter.format(now));
        orderDetailsDl.load();
    }

    @Subscribe("orderDetailTable.create")
    public void onOrderDetailTableCreate(Action.ActionPerformedEvent event) {
        OrderDetail orderDetail = metadata.create(OrderDetail.class);
        orderDetail.setStore(getEditedEntity().getStore());
        detailShow(orderDetail);
    }

    @Subscribe("orderDetailTable.edit")
    public void onOrderDetailTableEdit(Action.ActionPerformedEvent event) {
        detailShow(orderDetailTable.getSingleSelected());
    }

    private void detailShow(OrderDetail orderDetail) {
        OrderDetailEdit orderDetailEdit = screenBuilders.screen(this)
                .withScreenClass(OrderDetailEdit.class)
                .build();
        orderDetailEdit.setEntityToEdit(orderDetail);
        orderDetailEdit.setOrderDetails(orderDetailsDc.getItems());
        orderDetailEdit.show();
    }

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        List<OrderDetail> orderDetails = orderDetailsDc.getItems();

        Order order = getEditedEntity();
        if (order.getUser() == null) {
            order.setNr("O" + formatter.format(now));
            order.setOrderAt(now);
            order.setUser(userSession.getUser());
            order.setUserKey(userSession.getUser().getLogin() + dataFormat.format(now));
        }

        int quantity = 0;
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (OrderDetail orderDetail : orderDetails) {
            quantity += orderDetail.getQuantity();
            totalPrice = totalPrice.add(new BigDecimal(orderDetail.getTotalPrice()));
            orderDetail.setOrder(order);
        }
        order.setQuantity(quantity);
        order.setTotalPrice(totalPrice.doubleValue());
        order.setOrderDetails(orderDetails);

    }


}