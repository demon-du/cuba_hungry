package com.laurus.hungry.web.screens.dish;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.gui.UiComponents;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.components.FileDescriptorResource;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Image;
import com.haulmont.cuba.gui.screen.*;
import com.laurus.hungry.entity.Dish;

import javax.inject.Inject;


/**
 * @author : duyong
 * @version : 1.0
 * @date : 2020/2/4 12:05
 * Description : 
 */
@UiController("hungry_Dish.browse")
@UiDescriptor("dish-browse.xml")
@LookupComponent("dishesTable")
@LoadDataBeforeShow
public class DishBrowse extends StandardLookup<Dish> {

    @Inject
    private GroupTable<Dish> dishesTable;

    @Inject
    private UiComponents uiComponents;


    @Subscribe
    protected void onInit(InitEvent event) {
        dishesTable.addGeneratedColumn(
                "picture",
                this::renderAvatarImageComponent
        );
    }

    private Component renderAvatarImageComponent(Dish dish) {
        FileDescriptor imageFile = dish.getPicture();
        if (imageFile == null) {
            return null;
        }
        Image image = smallAvatarImage();
        image.setSource(FileDescriptorResource.class)
                .setFileDescriptor(imageFile);

        return image;
    }

    private Image smallAvatarImage() {
        Image image = uiComponents.create(Image.class);
        image.setScaleMode(Image.ScaleMode.CONTAIN);
        image.setHeight("40");
        image.setWidth("40");
        image.setStyleName("avatar-icon-small");
        return image;
    }


}