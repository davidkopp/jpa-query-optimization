package jpa_query_optimization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mickaelb.api.AssertHibernateSQLCount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.logging.Handler;
import java.util.ArrayList;

public class QueryTests {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_query_optimization");
    private EntityManager em = emf.createEntityManager();

    @BeforeEach
    public void setUp() {

        // Insert test data
        em.getTransaction().begin();

        Department dept1 = new Department();
        dept1.setName("IT");

        Department dept2 = new Department();
        dept2.setName("Finance");

        Employee emp1 = new Employee();
        emp1.setName("John");
        emp1.setDepartment(dept1);

        Employee emp2 = new Employee();
        emp2.setName("Jane");
        emp2.setDepartment(dept1);

        Employee emp3 = new Employee();
        emp3.setName("Bernd");
        emp3.setDepartment(dept2);

        Employee emp4 = new Employee();
        emp4.setName("Julia");
        emp4.setDepartment(dept2);

        em.persist(dept1);
        em.persist(dept2);
        em.persist(emp1);
        em.persist(emp2);
        em.persist(emp3);
        em.persist(emp4);

        em.getTransaction().commit();
    }

    @AfterEach
    public void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    /*
     * N+1 query problem
     * 3 select statements expected
     */
    @Test
    @AssertHibernateSQLCount(selects = 3)
    public void numberOfQueries_queryProblem() {
        em.getTransaction().begin();

        List<Department> departments = em
                .createQuery("SELECT d FROM Department d", Department.class)
                .getResultList();

        for (Department department : departments) {
            System.out.println("Department: " + department.getName());
            for (Employee employee : department.getEmployees()) {
                System.out.println("  Employee: " + employee.getName());
            }
        }

        em.getTransaction().commit();
    }

    /*
     * Using JOIN FETCH only one select statement is expected
     */
    @Test
    @AssertHibernateSQLCount(selects = 1)
    public void numberOfQueries_queryWithFetchJoin() {
        em.getTransaction().begin();

        List<Department> departments = em
                .createQuery("SELECT d FROM Department d JOIN FETCH d.employees", Department.class)
                .getResultList();

        for (Department department : departments) {
            System.out.println("Department: " + department.getName());
            for (Employee employee : department.getEmployees()) {
                System.out.println("  Employee: " + employee.getName());
            }
        }

        em.getTransaction().commit();
    }
}
