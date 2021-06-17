package service;

import model.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class CustomerServiceORM implements ICustomerService{

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public List<Customer> findAll() {
        String query = "SELECT c FROM Customer AS c";
        TypedQuery<Customer> customerTypedQuery = entityManager.createQuery(query, Customer.class);
        return customerTypedQuery.getResultList();
    }

    @Override
    public void save(Customer customer) {

    }

    @Override
    public Customer findById(int id) {
        return null;
    }

    @Override
    public void update(int id, Customer customer) {

    }

    @Override
    public void remove(int id) {

    }
}
