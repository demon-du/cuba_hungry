-- begin HUNGRY_DISH
create table HUNGRY_DISH (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    PRICE double precision,
    STORE_ID varchar(32),
    --
    primary key (ID)
)^
-- end HUNGRY_DISH
-- begin HUNGRY_STORE
create table HUNGRY_STORE (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    --
    primary key (ID)
)^
-- end HUNGRY_STORE
-- begin HUNGRY_ORDER_DETAIL
create table HUNGRY_ORDER_DETAIL (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    ORDER_ID varchar(32),
    USER_KEY varchar(255),
    STORE_ID varchar(32),
    DISH_ID varchar(32),
    QUANTITY integer,
    TOTAL_PRICE double precision,
    --
    primary key (ID)
)^
-- end HUNGRY_ORDER_DETAIL
-- begin HUNGRY_ORDER
create table HUNGRY_ORDER (
    ID varchar(32),
    VERSION integer not null,
    CREATE_TS datetime(3),
    CREATED_BY varchar(50),
    UPDATE_TS datetime(3),
    UPDATED_BY varchar(50),
    DELETE_TS datetime(3),
    DELETED_BY varchar(50),
    --
    NR varchar(255),
    USER_ID varchar(32),
    STORE_ID varchar(32),
    ORDER_AT datetime(3),
    QUANTITY integer,
    TOTAL_PRICE double precision,
    --
    primary key (ID)
)^
-- end HUNGRY_ORDER
