package application;

import domain.Person;

public class program {

    public static void main(String[] args){
        Person p1 = new Person(1, "Bob", "bob@gmail.com");
        Person p2 = new Person(2, "Roger", "rgr@gmail.com");

        System.out.println(p1);
        System.out.println(p2);

    }

}
