package com.example.chuhieu.kiot_demo.BanHang.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class Category {
    @SerializedName("ProductName")
    @Expose
    private String productName;
    @SerializedName("ProductCode")
    @Expose
    private String productCode;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("Visible")
    @Expose
    private Boolean visible;
    @SerializedName("Instock")
    @Expose
    private Integer instock;
    @SerializedName("ProductGroup")
    @Expose
    private String productGroup;
    @SerializedName("PageSize")
    @Expose
    private Integer pageSize;
    @SerializedName("PageIndex")
    @Expose
    private Integer pageIndex;

    public Category(String productName, String productCode, String description, Boolean visible, Integer instock, String productGroup, Integer pageSize, Integer pageIndex) {
        this.productName = productName;
        this.productCode = productCode;
        this.description = description;
        this.visible = visible;
        this.instock = instock;
        this.productGroup = productGroup;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Integer getInstock() {
        return instock;
    }

    public void setInstock(Integer instock) {
        this.instock = instock;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

}
