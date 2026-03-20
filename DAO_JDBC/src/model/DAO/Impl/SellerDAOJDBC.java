package model.DAO.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class SellerDAOJDBC implements SellerDAO{

    private Connection conn;

    public SellerDAOJDBC(Connection conn){
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "INSERT INTO Seller "
                + "(name, email, birthdate, baseSalary, departmentID) "
                + "VALUES "
                + "(?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();

                if (rs.next()){
                    int id = rs.getInt(1);
                    obj.setId(id);
                }

                DB.closeResultSet(rs);
            }else{
                throw new DbException("Unexpected error! No rows affected!");
            }

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }        
    }

    @Override
    public void update(Seller obj) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "UPDATE Seller "
                + "SET name = ?, email = ?, birthdate = ?, baseSalary = ?, departmentID = ? "
                + "WHERE id = ?");

            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setDate(3, new java.sql.Date(obj.getBirthDate().getTime()));
            st.setDouble(4, obj.getBaseSalary());
            st.setInt(5, obj.getDepartment().getId());
            st.setInt(6, obj.getId());

           st.executeUpdate();

        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }             
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;

        try{
            st = conn.prepareStatement(
                "DELETE FROM Seller "
                + "WHERE id = ?"
            );
            st.setInt(1, id);
            st.executeUpdate();
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeStatement(st);
        }
    }

    @Override
    public Seller findById(Integer id) {
    
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT Seller.*, Department.Name as DepName "
                + "FROM Seller INNER JOIN Department "
                + "ON Seller.DepartmentId = Department.id "
                + "WHERE Seller.id = ?"
            );

            st.setInt(1, id);

            rs = st.executeQuery();

            if(rs.next()){
                Department dep = instantiateDepartment(rs);

                Seller obj = instantiateSeller(rs, dep);  
                
                return obj;
            }
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

        return null;
    }

    private Seller instantiateSeller(ResultSet rs, Department dep) throws SQLException {
        Seller obj = new Seller();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("Name"));
        obj.setEmail(rs.getString("Email"));
        obj.setBirthDate(rs.getDate("BirthDate"));
        obj.setBaseSalary(rs.getDouble("BaseSalary"));
        obj.setDepartment(dep);  
        return obj;
    }

    private Department instantiateDepartment(ResultSet rs) throws SQLException {
        Department dep = new Department();
        dep.setId(rs.getInt("DepartmentId"));
        dep.setName(rs.getString("DepName"));
        return dep;    
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT Seller.*, Department.Name as DepName "
                + "FROM Seller INNER JOIN Department "
                + "ON Seller.departmentID = Department.id "
                + "ORDER BY Seller.name"
            );

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){

                //Validate if the department already exists in the map, if not, instantiate it and add to the map.
                Department dep = map.get(rs.getInt("DepartmentID"));

                if (dep == null){
                    dep = instantiateDepartment(rs);

                    //If the department not exists in the map, add it to the map.
                    map.put(rs.getInt("DepartmentID"), dep);
                }
                
                Seller obj = instantiateSeller(rs, dep);  
                
                list.add(obj);
            }

            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Seller> findByDepartment(Department department) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try{
            st = conn.prepareStatement(
                "SELECT Seller.*, Department.Name as DepName "
                + "FROM Seller INNER JOIN Department "
                + "ON Seller.departmentID = Department.id "
                + "WHERE Seller.departmentID = ? "
                + "ORDER BY Seller.name"
            );

            st.setInt(1, department.getId());

            rs = st.executeQuery();

            List<Seller> list = new ArrayList<>();
            Map<Integer, Department> map = new HashMap<>();

            while(rs.next()){

                //Validate if the department already exists in the map, if not, instantiate it and add to the map.
                Department dep = map.get(rs.getInt("DepartmentID"));

                if (dep == null){
                    dep = instantiateDepartment(rs);

                    //If the department not exists in the map, add it to the map.
                    map.put(rs.getInt("DepartmentID"), dep);
                }
                
                Seller obj = instantiateSeller(rs, dep);  
                
                list.add(obj);
            }

            return list;
        }catch(SQLException e){
            throw new DbException(e.getMessage());
        }finally{
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }        
    }
}
