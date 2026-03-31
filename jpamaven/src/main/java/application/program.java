package application;

import domain.person;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class program {

    public static void main(String[] args){
        person p1 = new person(null, "Bob", "bob@gmail.com");
        person p2 = new person(null, "Roger", "rgr@gmail.com");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("exemple-jpa");

        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.persist(p1);
        em.persist(p2);
        em.getTransaction().commit();
        System.out.println("Ok!");

        em.close();
        emf.close();

        System.out.println(p1);
        System.out.println(p2);

    }

}  