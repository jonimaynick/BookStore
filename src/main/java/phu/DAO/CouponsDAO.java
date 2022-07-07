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
import phu.Utils.DBUtils;

public class CouponsDAO {
        public static int GetPercent(String couCode) throws SQLException {
        int result = 101;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT PercentageDiscount FROM tblPercentageDiscount INNER JOIN tblCoupons ON "
                    + "tblPercentageDiscount.PercentageID = tblCoupons.PercentageID WHERE CouponsCode=?");
            ptm.setString(1, couCode);
            rs = ptm.executeQuery();
            if(rs.next()) {
                result = rs.getInt("PercentageDiscount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }
}
