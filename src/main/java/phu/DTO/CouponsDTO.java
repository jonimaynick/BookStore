/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.DTO;

import java.util.Date;

/**
 *
 * @author PC
 */
public class CouponsDTO {

    private String couponsID;
    private int percentageDiscount;
    private Date startDay, endDate;
    private String couponsCode;

    public CouponsDTO() {
        Date date = new Date(1, 1, 1);

        this.couponsID = "null";
        this.percentageDiscount = 0;
        this.startDay = date;
        this.endDate = date;
        this.couponsCode = "00000000";
    }

    public CouponsDTO(String couponsID, int percentageDiscount, Date startDay, Date endDate, String couponsCode) {
        this.couponsID = couponsID;
        this.percentageDiscount = percentageDiscount;
        this.startDay = startDay;
        this.endDate = endDate;
        this.couponsCode = couponsCode;
    }

    public String getCouponsID() {
        return couponsID;
    }

    public void setCouponsID(String couponsID) {
        this.couponsID = couponsID;
    }

    public int getPercentageDiscount() {
        return percentageDiscount;
    }

    public void setPercentageDiscount(int percentageDiscount) {
        this.percentageDiscount = percentageDiscount;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCouponsCode() {
        return couponsCode;
    }

    public void setCouponsCode(String couponsCode) {
        this.couponsCode = couponsCode;
    }
}
