create table PRICES (
       ID int(11) IDENTITY,
       PRODUCT_ID int(11) not null,
       PRIORITY int(11) not null,
       BRAND_ID int(11) not null,
       PRICE_LIST int(11) not null,
       START_DATE datetime not null,
       END_DATE datetime not null,
       PRICE double not null,
       CURR varchar(3) not null,
       PRIMARY KEY (ID)
);