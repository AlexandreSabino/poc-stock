CREATE TABLE stock.inventory (
    sku text,
    warehousecode int,
    sellerid text,
    productorigin int,
    distributioncenter text,
    lastupdate timestamp,
    quantity double,
    reservedquantity double,
    virtual boolean,
    warehousename text,
    saveDate bigint,
    primary KEY (sku, warehousecode, sellerid, productorigin)
)

CREATE MATERIALIZED VIEW inventoryByWarehousecode AS
SELECT * FROM inventory
where warehousecode is not null
and savedate is not null
and sku is not null
and sellerid is not null
and productorigin is not null
PRIMARY KEY (warehousecode, saveDate, sku, sellerid, productorigin);

CREATE TABLE stock.kardex (
    dateofmovement timestamp,
    sku text,
    warehousecode int,
    sellerid text,
    productorigin int,
    distributioncenter text,
    origin text,
    quantityactual double,
    quantitymovement double,
    quantityold double,
    typemovement text,
    PRIMARY KEY (dateofmovement, sku, warehousecode, sellerid, productorigin)
);
