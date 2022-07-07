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
import phu.DTO.BookHistoryDTO;
import phu.DTO.DetailDTO;
import phu.Utils.DBUtils;

public class DetailDAO {

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

    public String GetDetailID() throws SQLException {
        String result = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT MAX(DetailID) FROM tblOrderDetail");
            rs = ptm.executeQuery();
            if (rs.next()) {
                result = rs.getString("");
                if (result == null) {
                    result = "000";
                }
                int newInt = Integer.parseInt(result) + 1;
                result = String.valueOf(newInt);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return result;
    }
    private static final String ADDDETAIL = "INSERT INTO tblOrderDetail(DetailID, Price, Quantity, OrderID, BookID) VALUES(?,?,?,?,?)";

    public boolean AddDetail(DetailDTO detail) throws SQLException {
        String newID = this.GetDetailID();
        boolean rs = false;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement(ADDDETAIL);
            ptm.setString(1, newID);
            ptm.setFloat(2, detail.getPrice());
            ptm.setInt(3, detail.getQuantity());
            ptm.setString(4, detail.getOrderID());
            ptm.setString(5, detail.getBookID());
            rs = ptm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return rs;
    }

    private static final String GETLISTDETAIL = "SELECT DetailID,tblOrderDetail.Quantity,tblOrderDetail.Price, Title, Image "
            + "FROM tblOrderDetail INNER JOIN tblBooks ON tblBooks.BookID = tblOrderDetail.BookID WHERE OrderID=?";

    public List<BookHistoryDTO> GetListDetail(String orderID) throws SQLException {
        List<BookHistoryDTO> list = new ArrayList<>();
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement(GETLISTDETAIL);
            ptm.setString(1, orderID);
            rs = ptm.executeQuery();
            while (rs.next()) {
                list.add(new BookHistoryDTO(rs.getString("DetailID"), rs.getInt("Quantity"),
                        rs.getFloat("Price"), rs.getString("Title"), rs.getString("Image")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.CloseAll();
        }
        return list;
    }
}
