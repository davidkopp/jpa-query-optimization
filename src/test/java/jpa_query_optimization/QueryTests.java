package jpa_query_optimization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mickaelb.api.QueryAssertions;

import java.util.List;

public class QueryTests {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private static final int NUMBER_OF_DEPARTMENTS = 50;
    private static final int NUMBER_OF_EMPLOYEES_PER_DEPARTMENT = 3;

    @BeforeAll
    public static void fillDatabase() {
        emf = Persistence.createEntityManagerFactory("jpa_query_optimization");
        em = emf.createEntityManager();

        em.getTransaction().begin();

        for (int i = 0; i < NUMBER_OF_DEPARTMENTS; i++) {
            Department dept = new Department();
            dept.setName("Department " + i);

            for (int j = 0; j < NUMBER_OF_EMPLOYEES_PER_DEPARTMENT; j++) {
                Employee emp = new Employee();
                emp.setName("Employee " + i + "_" + j);
                emp.setDepartment(dept);
                dept.getEmployees().add(emp);
            }
            em.persist(dept);
        }

        em.getTransaction().commit();
    }

    @BeforeEach
    public void clearCache() {
        // Important to clear the persistence context so the associated cache with
        // cached objects is cleared
        em.clear();
    }

    @AfterAll
    public static void tearDown() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    /*
     * N+1 query problem
     */
    @Test
    public void numberOfQueries_queryProblem() {

        QueryAssertions.assertSelectCount(NUMBER_OF_DEPARTMENTS + 1, () -> {
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
        });
    }

    /*
     * Using JOIN FETCH only one select statement is expected
     */
    @Test
    public void numberOfQueries_queryWithFetchJoin() {

        QueryAssertions.assertSelectCount(1, () -> {

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
        });
    }
}
