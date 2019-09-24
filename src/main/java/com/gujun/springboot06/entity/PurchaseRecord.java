package com.gujun.springboot06.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Alias("purchaseRecord")
public class PurchaseRecord implements Serializable {

    private static final long serialVersionUID = 5236078327846483175L;

    private Integer prId;

    private Integer uId;

    private Integer pId;

    private double price;

    private int quantity;

    private double sum;

    private Date date;

    private String note;

    public Integer getPrId() {
        return prId;
    }

    public void setPrId(Integer prId) {
        this.prId = prId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PurchaseRecord() {
    }

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "prId=" + prId +
                ", uId=" + uId +
                ", pId=" + pId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", sum=" + sum +
                ", date=" + date +
                ", note='" + note + '\'' +
                '}';
    }

}
