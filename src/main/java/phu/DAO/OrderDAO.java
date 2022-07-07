/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phu.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import phu.DTO.OrderDTO;
import phu.Utils.DBUtils;

public class OrderDAO {

    private Connection con = null;
    private PreparedStatement ptm = null;
    private ResultSet rs = null;

    private void CloseAll() throws SQLException {
        if (con != null) {
            con.close();
        }
        if (ptm != null) {
            ptm.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    public String GetOrderID() throws SQLException {
        String result = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT MAX(OrderID) FROM tblOrder");
            rs = ptm.executeQuery();
            if (rs.next()) {
                result = rs.getString("");
                int newInt = Integer.parseInt(result) + 1;
                result = String.valueOf(newInt);
            } else {
                result = "001";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return result;
    }

    private static final String CREATEORDER = "INSERT INTO tblOrder(OrderID, OrderDate, Total, UserID, Status, CouponsCode) VALUES(?,?,?,?,?,?)";

    public OrderDTO CreateOrder(OrderDTO order) throws SQLException {
        String newID = this.GetOrderID();
        order.setOrderID(newID);
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement(CREATEORDER);
            ptm.setString(1, newID);
            ptm.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            ptm.setFloat(3, order.getTotal());
            ptm.setString(4, order.getUserID());
            ptm.setBoolean(5, order.isStatus());
            ptm.setString(6, order.getCouponsCode());
            boolean result = ptm.executeUpdate() > 0;
            if (result) {
                return order;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return null;
    }

    //UPDATE tblOrder SET Total=? WHERE OrderID=?
    public boolean UpdateTotal(float total, String orderID) throws SQLException {
        boolean rs = false;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("UPDATE tblOrder SET Total=? WHERE OrderID=?");
            ptm.setFloat(1, total);
            ptm.setString(2, orderID);
            rs = ptm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return rs;
    }

    private static final String GETLISTORDER = "SELECT OrderID, OrderDate, Total, Status, CouponsCode FROM tblOrder WHERE UserID=?";
    
    public List<OrderDTO> GetListOrder(String userID) throws SQLException {
        List<OrderDTO> list = new ArrayList<OrderDTO>();
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement(GETLISTORDER);
            ptm.setString(1, userID);
            rs = ptm.executeQuery();
            while(rs.next()){
                String coupons = rs.getString("CouponsCode");
                if(coupons == null) coupons="";
                list.add(new OrderDTO(rs.getString("OrderID"), rs.getDate("OrderDate"), rs.getFloat("Total"), userID, true, coupons));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return list;
    }
}
