package com.laurus.hungry.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.laurus.hungry.entity.Store;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/4 12:04
 * Description : 
 */
@UiController("hungry_Store.browse")
@UiDescriptor("store-browse.xml")
@LookupComponent("storesTable")
@LoadDataBeforeShow
public class StoreBrowse extends StandardLookup<Store> {
}