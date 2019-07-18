package com.example.chuhieu.kiot_demo.BanHang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryResponse {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("Picture")
    @Expose
    private Object picture;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("UnitPrice")
    @Expose
    private Double unitPrice;
    @SerializedName("Price")
    @Expose
    private Double price;
    @SerializedName("InStock")
    @Expose
    private Double inStock;
    @SerializedName("MinInStock")
    @Expose
    private Double minInStock;
    @SerializedName("MaxInStock")
    @Expose
    private Double maxInStock;
    @SerializedName("BranchName")
    @Expose
    private String branchName;
    @SerializedName("GroupName")
    @Expose
    private String groupName;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("ImageUrls")
    @Expose
    private List<Object> imageUrls = null;

    public CategoryResponse(String id, String name, String productCode, Object picture, String unit, Double unitPrice, Double price, Double inStock, Double minInStock, Double maxInStock, String branchName, String groupName, String description, List<Object> imageUrls) {
        this.id = id;
        this.name = name;
        this.productCode = productCode;
        this.picture = picture;
        this.unit = unit;
        this.unitPrice = unitPrice;
        this.price = price;
        this.inStock = inStock;
        this.minInStock = minInStock;
        this.maxInStock = maxInStock;
        this.branchName = branchName;
        this.groupName = groupName;
        this.description = description;
        this.imageUrls = imageUrls;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Object getPicture() {
        return picture;
    }

    public void setPicture(Object picture) {
        this.picture = picture;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getInStock() {
        return inStock;
    }

    public void setInStock(Double inStock) {
        this.inStock = inStock;
    }

    public Double getMinInStock() {
        return minInStock;
    }

    public void setMinInStock(Double minInStock) {
        this.minInStock = minInStock;
    }

    public Double getMaxInStock() {
        return maxInStock;
    }

    public void setMaxInStock(Double maxInStock) {
        this.maxInStock = maxInStock;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Object> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<Object> imageUrls) {
        this.imageUrls = imageUrls;
    }

}
