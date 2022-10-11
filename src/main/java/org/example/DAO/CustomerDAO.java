package org.example.DAO;

import org.example.model.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers(int theSortField);

    void saveCustomer(Customer theCustomer);

    Customer getCustomer(int theId);

    void deleteCustomer(int theId);

    List<Customer> searchCustomers(String theSearchName);
}
