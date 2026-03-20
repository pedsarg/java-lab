package application;

import java.util.Date;
import java.util.List;

import model.DAO.DAOFactory;
import model.DAO.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

public class Program {

    public static void main(String[] args) {

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


        System.out.println("\n\n=== TEST 3: seller findAll ===");
        List<Seller> list2 = sellerDAO.findAll();
        for (Seller obj : list2){
            System.out.println(obj);
        }


        System.out.println("\n\n=== TEST 4: seller insert ===");
        Seller newSeller = new Seller(null, "Richard", "Rich@gmail.com", new Date(), 3500.0, department);
        sellerDAO.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());

    
        System.out.println("\n\n=== TEST 5: seller update ===");
        seller = sellerDAO.findById(4);
        seller.setName("Clark");
        sellerDAO.update(seller);
        System.out.println("Update completed");


        System.out.println("\n\n=== TEST 6: seller deleteById ===");
        sellerDAO.deleteById(4);
        System.out.println("Delete completed");
    }
}
