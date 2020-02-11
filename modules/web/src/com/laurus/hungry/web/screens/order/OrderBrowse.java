package com.laurus.hungry.web.screens.order;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.laurus.hungry.entity.Order;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/6 11:24
 * Description :
 */
@UiController("hungry_Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Inject
    private UserSession userSession;

    @Inject
    private Notifications notifications;

    @Inject
    private ScreenBuilders screenBuilders;

    @Subscribe("ordersTable.create")
    public void onOrdersTableCreate(Action.ActionPerformedEvent event) {
        if(isOrdered()){
            notifications.create(Notifications.NotificationType.HUMANIZED).withCaption("今日已点餐").show();
        }else{
            OrderEdit orderEdit = screenBuilders.screen(this)
                    .withScreenClass(OrderEdit.class)
                    .build();
            orderEdit.setEntityToEdit(metadata.create(Order.class));
            orderEdit.show();
        }
    }


    private boolean isOrdered() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
        String userKey = userSession.getUser().getLogin() + dataFormat.format(now);

        Long count = dataManager.loadValue(
                "select count(o) from hungry_Order o where o.userKey = :userKey and o.deleteTs is null ", Long.class)
                .parameter("userKey", userKey)
                .one();
        return count > 0;
    }
}