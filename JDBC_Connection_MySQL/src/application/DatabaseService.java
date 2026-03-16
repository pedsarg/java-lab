package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;
import db.DBIntegrityException;

public class DatabaseService {

    public void ReadData(){
        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;

        try{
            conn = DB.getConnection();
            st = conn.createStatement();

            rs = st.executeQuery("SELECT * from User");
        
            while(rs.next()){
                System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }        
    }

    public void InsertData(){
        Connection conn = null;
        PreparedStatement st = null;
        
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                "INSERT INTO Department "
                + "(Id, Name) Values (?, ?)");
            
            st.setInt(1, 5);
            st.setString(2, "Game Development");

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }
    }

        public void InsertDataWithGeneratedKeys(){
        Connection conn = null;
        PreparedStatement st = null;
        
        try{
            conn = DB.getConnection();
            st = conn.prepareStatement(
                "INSERT INTO Department "
                + "(Id, Name) Values (?, ?)", Statement.RETURN_GENERATED_KEYS);
            
            st.setInt(1, 5);
            st.setString(2, "Game Development");

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                System.out.println("Done! Rows affected: " + rowsAffected);
            
                ResultSet rs = st.getGeneratedKeys();
                while(rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("Generated Id: " + id);
                }
            }else{
                System.out.println("No rows affected!");
            }
            

        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }
    }

    public void UpdateData(){

        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();
            
            st = conn.prepareStatement(
                "UPDATE Department "
                + "SET Name = ?"
                + "WHERE Id = ?"
            );

            st.setString(1, "Car Development");
            st.setInt(2, 4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);


        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }
    }

    public void DeleteData(){
        Connection conn = null;
        PreparedStatement st = null;

        try{
            conn = DB.getConnection();

            st = conn.prepareStatement(
                "DELETE FROM Department "
                + "WHERE Id = ? "
            );

            st.setInt(1,4);

            int rowsAffected = st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }catch(SQLException e){
            throw new DBIntegrityException(e.getMessage());
        }finally{
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }
    }

    public void TransactionalUpdate(){
        Connection conn = null;
        Statement st = null;

        try{
            conn = DB.getConnection();

            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE Department SET Name = 'Fashion' WHERE Id = 3");

            // if(rows1 != 0)
            //     throw new SQLException("Fake error");

            int rows2 = st.executeUpdate("UPDATE Department SET Name = 'Car development' WHERE Id = 2");

            conn.commit();

            System.out.println("Rows1: " + rows1);
            System.out.println("Rows2: " + rows2);

        }catch(SQLException e){
            try{
                conn.rollback();
                throw new DBIntegrityException("Transaction rolled back! Caused by: " + e.getMessage());
            }catch(SQLException e1){
                throw new DBIntegrityException("Error trying to rollback! Caused by: " + e1.getMessage());
            }            
        }finally{
            DB.closeStatement(st);
            DB.closeConnection(conn);
        }

    }
}
