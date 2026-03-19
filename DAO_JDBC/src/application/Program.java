package application;

import java.util.Date;
import java.util.List;

import model.DAO.DAOFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {
        // Department obj = new Department(1, "Books");
        // System.out.println(obj);

        // Seller seller = new Seller(21, "Bob", "bob@gmail.com", new Date(), 3000.0, obj);
        // System.out.println(seller);

        SellerDAO sellerDAO = DAOFactory.createSellerDAO();

        System.out.println("=== TEST 1: seller findById ===");
        Seller seller = sellerDAO.findById(3);
        System.out.println(seller);


        System.out.println("\n\n=== TEST 2: seller findByDepartment ===");
        Department department = new Department(3, null);
        List<Seller> list = sellerDAO.findByDepartment(department);

        for (Seller obj : list){
            System.out.println(obj);
        }
    }

}
