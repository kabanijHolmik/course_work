package com.coursework.models;

import java.sql.Blob;
import java.sql.Date;

public class Item {
    private int id;
    private int code;
    private Float price;
    private String name;
    private String category;
    private Blob photo;
    private String unit;
    private String location;
    private String status;
    private String note;
    private String country;
    private String supplier;
    private Date receiptDay;
    private Date saleDate;

    public Item() {
    }

    public Item(int id, int code, Float price, String name, String category, Blob photo, String unit, String location, String status, String note, String country,String supplier, Date receiptDay, Date saleDate) {
        this.id = id;
        this.code = code;
        this.price = price;
        this.name = name;
        this.category = category;
        this.photo = photo;
        this.unit = unit;
        this.location = location;
        this.status = status;
        this.note = note;
        this.country = country;
        this.supplier = supplier;
        this.receiptDay = receiptDay;
        this.saleDate = saleDate;
    }


    public int getId() {
        return this.id;
    }

    public int getCode() {
        return this.code;
    }

    public Float getPrice() {
        return this.price;
    }

    public String getName() {
        return this.name;
    }

    public String getCategory() {
        return this.category;
    }

    public Blob getPhoto() {
        return this.photo;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getLocation() {
        return this.location;
    }

    public String getStatus() {
        return this.status;
    }

    public String getNote() {
        return this.note;
    }

    public String getCountry() {
        return this.country;
    }

    public String getSupplier() {
        return supplier;
    }

    public Date getReceiptDay() {
        return this.receiptDay;
    }

    public Date getSaleDate() {
        return this.saleDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public void setReceiptDay(Date receiptDay) {
        this.receiptDay = receiptDay;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) return false;
        final Item other = (Item) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getId() != other.getId()) return false;
        if (this.getCode() != other.getCode()) return false;
        final Object this$price = this.getPrice();
        final Object other$price = other.getPrice();
        if (this$price == null ? other$price != null : !this$price.equals(other$price)) return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$category = this.getCategory();
        final Object other$category = other.getCategory();
        if (this$category == null ? other$category != null : !this$category.equals(other$category)) return false;
        final Object this$photo = this.getPhoto();
        final Object other$photo = other.getPhoto();
        if (this$photo == null ? other$photo != null : !this$photo.equals(other$photo)) return false;
        final Object this$unit = this.getUnit();
        final Object other$unit = other.getUnit();
        if (this$unit == null ? other$unit != null : !this$unit.equals(other$unit)) return false;
        final Object this$location = this.getLocation();
        final Object other$location = other.getLocation();
        if (this$location == null ? other$location != null : !this$location.equals(other$location)) return false;
        final Object this$status = this.getStatus();
        final Object other$status = other.getStatus();
        if (this$status == null ? other$status != null : !this$status.equals(other$status)) return false;
        final Object this$note = this.getNote();
        final Object other$note = other.getNote();
        if (this$note == null ? other$note != null : !this$note.equals(other$note)) return false;
        final Object this$country = this.getCountry();
        final Object other$country = other.getCountry();
        if (this$country == null ? other$country != null : !this$country.equals(other$country)) return false;
        final Object this$supplier = this.getSupplier();
        final Object other$supplier = other.getSupplier();
        if (this$supplier == null ? other$supplier != null : !this$supplier.equals(other$supplier)) return false;
        final Object this$receiptDay = this.getReceiptDay();
        final Object other$receiptDay = other.getReceiptDay();
        if (this$receiptDay == null ? other$receiptDay != null : !this$receiptDay.equals(other$receiptDay))
            return false;
        final Object this$saleDate = this.getSaleDate();
        final Object other$saleDate = other.getSaleDate();
        if (this$saleDate == null ? other$saleDate != null : !this$saleDate.equals(other$saleDate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Item;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getId();
        result = result * PRIME + this.getCode();
        final Object $price = this.getPrice();
        result = result * PRIME + ($price == null ? 43 : $price.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $category = this.getCategory();
        result = result * PRIME + ($category == null ? 43 : $category.hashCode());
        final Object $photo = this.getPhoto();
        result = result * PRIME + ($photo == null ? 43 : $photo.hashCode());
        final Object $unit = this.getUnit();
        result = result * PRIME + ($unit == null ? 43 : $unit.hashCode());
        final Object $location = this.getLocation();
        result = result * PRIME + ($location == null ? 43 : $location.hashCode());
        final Object $status = this.getStatus();
        result = result * PRIME + ($status == null ? 43 : $status.hashCode());
        final Object $note = this.getNote();
        result = result * PRIME + ($note == null ? 43 : $note.hashCode());
        final Object $country = this.getCountry();
        result = result * PRIME + ($country == null ? 43 : $country.hashCode());
        final Object $supplier = this.getSupplier();
        result = result * PRIME + ($supplier == null ? 43 : $supplier.hashCode());
        final Object $receiptDay = this.getReceiptDay();
        result = result * PRIME + ($receiptDay == null ? 43 : $receiptDay.hashCode());
        final Object $saleDate = this.getSaleDate();
        result = result * PRIME + ($saleDate == null ? 43 : $saleDate.hashCode());
        return result;
    }

    public String toString() {
        return "Item(id=" + this.getId() + ", code=" + this.getCode() + ", price=" + this.getPrice() + ", name=" + this.getName() + ", category=" + this.getCategory() + ", photo=" + this.getPhoto() + ", unit=" + this.getUnit() + ", location=" + this.getLocation() + ", status=" + this.getStatus() + ", note=" + this.getNote() + ", country=" + this.getCountry() + ", supplier=" + this.getSupplier() + ", receiptDay=" + this.getReceiptDay() + ", saleDate=" + this.getSaleDate() + ")";
    }
}
