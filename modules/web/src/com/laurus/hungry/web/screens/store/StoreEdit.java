package com.laurus.hungry.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.laurus.hungry.entity.Store;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/4 12:04
 * Description : 
 */
@UiController("hungry_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
}