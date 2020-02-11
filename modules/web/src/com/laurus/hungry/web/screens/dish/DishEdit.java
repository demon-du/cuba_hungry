package com.laurus.hungry.web.screens.dish;

import com.haulmont.cuba.gui.screen.*;
import com.laurus.hungry.entity.Dish;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/4 12:05
 * Description : 
 */
@UiController("hungry_Dish.edit")
@UiDescriptor("dish-edit.xml")
@EditedEntityContainer("dishDc")
@LoadDataBeforeShow
public class DishEdit extends StandardEditor<Dish> {
}