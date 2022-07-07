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
import java.util.Date;
import java.util.List;
import phu.DTO.BookDTO;
import phu.Utils.DBUtils;

/**
 *
 * @author PC
 */
public class BookDAO {

    private static final String SEARCH = "SELECT BookID, Title, Price, Image, Describe,Author, CategoryID,Quantity,ImportDate,TotalReviews,Rating, Status "
            + "FROM tblBooks WHERE Title like ? AND status like ?";
    private static final String GETBOOKFROMID = "SELECT Title, Price, Image, Describe,Author, CategoryID,Quantity,TotalReviews,Rating,ImportDate, Status FROM tblBooks WHERE BookID=? AND status=?";
    private static final String UPDATE = "UPDATE tblBooks SET Title=?, Price=?, Image=?,Describe=?, Author=?, CategoryID=?, Quantity=?, Status=? WHERE BookID=?";
    private static final String INSERT = "INSERT INTO tblBooks(BookID, Title, Price, Image, Describe, Author, CategoryID, Quantity,TotalReviews,Rating, Status,ImportDate) "
            + "VALUES(?,?,?,?,?,?,?,?,0,0,?,?)";

    public boolean AddBook(BookDTO book) throws SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(INSERT);
                ptm.setString(1, getNewBookID());
                ptm.setString(2, book.getTitle());
                ptm.setFloat(3, book.getPrice());
                ptm.setString(4, book.getImage());
                ptm.setString(5, book.getDescribe());
                ptm.setString(6, book.getAuthor());
                ptm.setString(7, getCategoryID(book.getCategory()));
                ptm.setInt(8, book.getQuantity());
                ptm.setBoolean(9, book.isStatus());
                ptm.setDate(10, new java.sql.Date(book.getImportDate().getTime()));
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    private String getNewBookID() throws SQLException {
        String result = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT MAX(BookID) FROM tblBooks");
            rs = ptm.executeQuery();
            if (rs.next()) {
                result = rs.getString("");
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
        int a = Integer.parseInt(result);
        a++;
        return String.valueOf(a);
    }

    public boolean updateBook(BookDTO book) throws SQLException {
        boolean result = false;
        Connection con = null;
        PreparedStatement ptm = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(UPDATE);
                ptm.setString(1, book.getTitle());
                ptm.setFloat(2, book.getPrice());
                ptm.setString(3, book.getImage());
                ptm.setString(4, book.getDescribe());
                ptm.setString(5, book.getAuthor());
                ptm.setString(6, getCategoryID(book.getCategory()));
                ptm.setInt(7, book.getQuantity());
                ptm.setBoolean(8, book.isStatus());
                ptm.setString(9, book.getBookID());
                result = ptm.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ptm != null) {
                ptm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

    public List<String> getListCategory() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT * FROM tblCategory");
            rs = ptm.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("CategoryName"));
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
        return list;
    }

    private String getCategoryID(String categoryName) throws SQLException {
        String result = null;
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            ptm = con.prepareStatement("SELECT CategoryID FROM tblCategory Where CategoryName=?");
            ptm.setString(1, categoryName);
            rs = ptm.executeQuery();
            if (rs.next()) {
                result = rs.getString("CategoryID");
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

    public BookDTO getBookFromID(String bookID) throws SQLException {
        BookDTO book = new BookDTO();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GETBOOKFROMID);
                ptm.setString(1, bookID);
                ptm.setBoolean(2, true);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String title = rs.getString("Title");
                    String image = rs.getString("Image");
                    float price = rs.getFloat("Price");
                    String describe = rs.getString("Describe");
                    String author = rs.getString("Author");
                    String categoryName = getCategoryName(rs.getString("CategoryID"));
                    int quantity = rs.getInt("Quantity");
                    int totalReview = rs.getInt("TotalReviews");
                    float rating = rs.getFloat("Rating");
                    boolean status = rs.getBoolean("Status");
                    Date importDate = rs.getDate("ImportDate");

                    book = new BookDTO(bookID, title, price, image, describe, author, categoryName, quantity, importDate, totalReview, rating, status);
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
        return book;
    }

    public List<BookDTO> searchBooks(String search) throws SQLException {
        List<BookDTO> listBooks = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                ptm.setBoolean(2, true);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String title = rs.getString("Title");
                    String image = rs.getString("Image");
                    float price = rs.getFloat("Price");
                    String describe = rs.getString("Describe");
                    String author = rs.getString("Author");
                    String categoryName = getCategoryName(rs.getString("CategoryID"));
                    int quantity = rs.getInt("Quantity");
                    int totalReview = rs.getInt("TotalReviews");
                    float rating = rs.getFloat("Rating");
                    boolean status = rs.getBoolean("Status");
                    Date importDate = rs.getDate("ImportDate");
                    listBooks.add(new BookDTO(bookID, title, price, image, describe, author, categoryName, quantity, importDate, totalReview, rating, status));
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
        return listBooks;
    }

    public List<BookDTO> searchAllBooks(String search) throws SQLException {
        List<BookDTO> listBooks = new ArrayList<>();
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement("SELECT BookID, Title, Price, Image, Describe,Author, "
                        + "CategoryID,Quantity,TotalReviews,Rating,ImportDate, Status FROM tblBooks WHERE Title like ?");
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String bookID = rs.getString("BookID");
                    String title = rs.getString("Title");
                    String image = rs.getString("Image");
                    float price = rs.getFloat("Price");
                    String describe = rs.getString("Describe");
                    String author = rs.getString("Author");
                    String categoryName = getCategoryName(rs.getString("CategoryID"));
                    int quantity = rs.getInt("Quantity");
                    int totalReview = rs.getInt("TotalReviews");
                    float rating = rs.getFloat("Rating");
                    boolean status = rs.getBoolean("Status");
                    Date importDate = rs.getDate("ImportDate");
                    listBooks.add(new BookDTO(bookID, title, price, image, describe, author, categoryName, quantity, importDate, totalReview, rating, status));
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
        return listBooks;
    }

    private String getCategoryName(String categoryID) throws SQLException {
        String GET = "SELECT CategoryName FROM tblCategory WHERE CategoryID=?";
        Connection con = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        String CategoryName = null;
        try {
            con = DBUtils.getConnection();
            if (con != null) {
                ptm = con.prepareStatement(GET);
                ptm.setString(1, categoryID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    CategoryName = rs.getString("CategoryName");
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
        return CategoryName;
    }
}
