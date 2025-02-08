package jpa_query_optimization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_query_optimization");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        List<Department> departments = em.createQuery("SELECT d FROM Department d", Department.class).getResultList();  
        // List<Department> departments = em.createQuery("SELECT d FROM Department d JOIN FETCH d.employees", Department.class).getResultList();  

        for (Department department : departments) {  
            System.out.println("Department: " + department.getName());  
            for (Employee employee : department.getEmployees()) {  
                System.out.println("  Employee: " + employee.getName());  
            }
        }

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
