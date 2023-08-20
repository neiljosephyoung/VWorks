package com.example.VWorks.DTO;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document("products")
public class Product {

    @Id
    private String id;
    @Field("description")
    private String description;
    @Field("productCode")
    private String productCode;
    @Field("barcode")
    private String barcode;
    @Field("sellingPrice")
    private Double sellingPrice;
    @Field("vat")
    private String vat;

}

