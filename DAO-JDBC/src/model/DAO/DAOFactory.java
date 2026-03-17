package model.DAO;

import model.DAO.Impl.SellerDAOJDBC;

public class DAOFactory {

    public static SellerDAO createSellerDAO(){
        return new SellerDAOJDBC();
    }

}
