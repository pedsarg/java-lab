package model.DAO;

import java.sql.Connection;

import db.DB;
import model.DAO.Impl.DepartmentDAOJDBC;
import model.DAO.Impl.SellerDAOJDBC;

public class DAOFactory {

    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC(DB.getConnection());
    }

    public static DepartmentDAO createDepartmentDAO(){
        return new DepartmentDAOJDBC(DB.getConnection());
    };

}
