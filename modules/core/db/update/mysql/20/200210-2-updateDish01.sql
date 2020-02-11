alter table HUNGRY_DISH add constraint FK_HUNGRY_DISH_ON_PICTURE foreign key (PICTURE_ID) references SYS_FILE(ID);
create index IDX_HUNGRY_DISH_ON_PICTURE on HUNGRY_DISH (PICTURE_ID);
