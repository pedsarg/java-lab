package application;

import java.util.Date;

import model.DAO.DAOFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
        Department obj = new Department(1, "Books");
        System.out.println(obj);

        SellerDAO sellerDao = DAOFactory.createSellerDAO();

        Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
        System.out.println(seller);
    }

}
