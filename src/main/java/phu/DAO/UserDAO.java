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
import java.util.Date;
import phu.DTO.UserDTO;
import phu.Utils.DBUtils;

/**
 *
 * @author PC
 */
public class UserDAO {

    private static final String LOGIN = "SELECT UserID, RoleID, Address, Birthday, Phone, Email FROM tblUsers WHERE UserName=? AND Password=? AND status=?";

    public UserDTO CheckLogin(String userName, String password) throws SQLException {
        UserDTO user = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(LOGIN);
                ptm.setString(1, userName);
                ptm.setString(2, password);
                ptm.setString(3, "True");
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String userID = rs.getString("UserID");
                    String roleID = rs.getString("RoleID");
                    String roleName = GetRoleName(roleID);
                    Date birthday = rs.getDate("Birthday");
                    String address = rs.getString("Address");
                    String phone = rs.getString("Phone");
                    String email = rs.getString("Email");
                    user = new UserDTO(userID, roleName, userName, "***", birthday, address, phone, email, true);
                }
            }
        } catch (Exception e) {
            String er = e.toString();
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
        return user;
    }

    private String GetRoleName(String roleID) throws SQLException {
        String GET = "SELECT RoleName FROM tblRoles WHERE RoleID=?";
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String roleName = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET);
                ptm.setString(1, roleID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    roleName = rs.getString("RoleName");
                }
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
        return roleName;
    }
}
