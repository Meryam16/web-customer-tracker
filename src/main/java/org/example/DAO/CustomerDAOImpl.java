package org.example.DAO;

import org.example.model.Customer;
import org.example.model.Utility;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO{

    //need inject the session factory
    //so my dao can use it to talk to db
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Customer> getCustomers(int theSortField) {

        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // determine sort field
        String theFieldName = null;

        switch (theSortField) {
            case Utility.FIRST_NAME:
                theFieldName = "firstName";
                break;
            case Utility.LAST_NAME:
                theFieldName = "lastName";
                break;
            case Utility.EMAIL:
                theFieldName = "email";
                break;
            default:
                // if nothing matches the default to sort by lastName
                theFieldName = "lastName";
        }

        // create a query
        String queryString = "from Customer order by " + theFieldName;
        Query<Customer> theQuery =
                currentSession.createQuery(queryString, Customer.class);

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
        //get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //save the customer
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //now retrieve/read from database using the primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);

        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        //get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //delete object with primary key
        Query theQuery
                = currentSession.createQuery("delete from Customer where id=:customerId");
        theQuery.setParameter("customerId", theId);

        theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
        // get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {
            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName", Customer.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer", Customer.class);
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;

    }
}
