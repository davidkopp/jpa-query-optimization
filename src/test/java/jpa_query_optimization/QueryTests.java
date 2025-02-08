package jpa_query_optimization;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mickaelb.api.QueryAssertions;

import java.util.List;

public class QueryTests {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_query_optimization");
    private EntityManager em = emf.createEntityManager();

    private static final int NUMBER_OF_DEPARTMENTS = 50;
    private static final int NUMBER_OF_EMPLOYEES_PER_DEPARTMENT = 3;

    @BeforeEach
    public void setUp() {
        
        em.getTransaction().begin();

        for (int i = 0; i < NUMBER_OF_DEPARTMENTS; i++) {
            Department dept = new Department();
            dept.setName("Department " + i);
            
            em.persist(dept);
            em.flush();
            em.refresh(dept);

            for (int j = 0; j < NUMBER_OF_EMPLOYEES_PER_DEPARTMENT; j++) {
                Employee emp = new Employee();
                emp.setName("Employee " + i + j);
                emp.setDepartment(dept);
                em.persist(emp);
            }
        }

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
