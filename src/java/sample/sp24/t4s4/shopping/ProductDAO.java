/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.sp24.t4s4.shopping;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.mail.internet.AddressException;
import sample.sp24.t4s4.user.UserGoogleDAO;
import sample.sp24.t4s4.utils.DBUtils;

/**
 *
 * @author admin
 */
public class ProductDAO {

    private static final String SEARCH = "SELECT productID, productName, price, quantity FROM tblProducts WHERE productName like ?";
    private static final String GET_QUANTITY = "SELECT quantity FROM tblProducts WHERE productID= ?";
    private static final String INSERT_TO_ORDER = "INSERT INTO tblOrders (orderID,userID,Date,total) VALUES(DBO.GET_NEX_ORDER_ID(?),?,?,?)";
    private static final String GET_ORDERID = "SELECT TOP 1 orderID FROM tblOrders ORDER BY orderID DESC";
    private static final String INSERT_INTO_ORDERDETAIL = "INSERT INTO tblOrderDetails (orderID, productID, quantity, price) VALUES (?,?,?,?)";
    private static final String UPDATE_QUANTITY = "UPDATE tblProducts SET quantity=? WHERE productID=?";
    private static final String DELETE_ORDER="DELETE tblOrders WHERE orderID=?";
    
    public List<Computer> getProducList(String search) throws SQLException {
        List<Computer> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    double price = (double) rs.getFloat("price");
                    int quantity = rs.getInt("quantity");
                    list.add(new Computer(productID, productName, price, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return list;
    }

    public int getQuatityOfProduct(String id) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        int quantity = 0;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(GET_QUANTITY);
            ptm.setString(1, id);
            rs = ptm.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt("quantity");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return quantity;
    }

    public boolean checkInsert(String userID, LocalDate currentDate, float total) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                conn.setAutoCommit(false);
                ptm = conn.prepareStatement(INSERT_TO_ORDER);
                ptm.setString(1, "OD_");
                ptm.setString(2, userID);
                ptm.setDate(3, (Date.valueOf(currentDate)));
                ptm.setFloat(4, total);
                int rowsAffected=(int) ptm.executeLargeUpdate();
                if(rowsAffected>0){
                    conn.commit();
                    check=true;
                }else{
                    conn.rollback();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (conn != null) {
                conn.setAutoCommit(true); // Reset auto-commit
                conn.close();
            }
            if(ptm!=null) ptm.close();
        }return check;
    }

    public String getOrderID() throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        ResultSet rs=null;
        String orderID="";
        try {
            conn=DBUtils.getConnection();
            ptm=conn.prepareStatement(GET_ORDERID);
            rs=ptm.executeQuery();
            if(rs.next()){
                orderID=rs.getString("orderID");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if (rs != null) {
                conn.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }return orderID;
    }


    public boolean checkSendMail(String email, String title, Cart cart) throws javax.mail.MessagingException, AddressException, IOException {
        UserGoogleDAO dao=new UserGoogleDAO();
        boolean check=false;
        try {
            dao.sendEmail(email, title,cart);
            System.out.println("Email sent successfully.");
            check=true;
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
        return check;
    }

    public boolean CheckUpdateAndInsert(Cart cart, String orderID) throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        PreparedStatement ptm2=null;
        ResultSet rs=null;
        boolean checkUpdate=false;
        boolean checkInsert=false;
        boolean check=false;
        try {
            conn=DBUtils.getConnection();
            conn.setAutoCommit(false);
            ptm=conn.prepareStatement(UPDATE_QUANTITY);
            ptm2=conn.prepareStatement(INSERT_INTO_ORDERDETAIL);
            for (Computer computer : cart.getCart().values()) {
                int quantityCurrent=getQuatityOfProduct(computer.getId());
                if(quantityCurrent==0){
                    conn.rollback();
                    check=false;
                    break;
                }else if(computer.getQuantity()>quantityCurrent){
                    conn.rollback();
                    check=false;
                    break;
                }else{
                    ptm2.setString(1, orderID);
                    ptm2.setString(2, computer.getId());
                    ptm2.setInt(3, computer.getQuantity());
                    ptm2.setFloat(4, (float) computer.getPrice());
                    checkInsert=ptm2.executeLargeUpdate()>0?true:false;
                    ptm.setInt(1, quantityCurrent-computer.getQuantity());
                    ptm.setString(2, computer.getId());
                    checkUpdate=ptm.executeLargeUpdate()>0?true:false;
                }
            }
            if(checkUpdate==true && checkInsert==true){
                check=true;
                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn!=null){
                conn.setAutoCommit(true);
                conn.close();
            }
            if(ptm!=null) ptm.close();
            if(rs!=null) rs.close();
        }return check;
    }

    public boolean deleteOrder(String orderID) throws SQLException {
        Connection conn=null;
        PreparedStatement ptm=null;
        boolean check=false;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                ptm=conn.prepareStatement(DELETE_ORDER);
                ptm.setString(1, orderID);
                check=ptm.executeLargeUpdate()>0?true:false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(conn!=null) conn.close();
            if(ptm!=null) ptm.close();
        }return check;
    }

}
