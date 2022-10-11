package org.example.controller;

import org.example.DAO.CustomerDAO;
import org.example.model.Customer;
import org.example.model.Utility;
import org.example.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    //need to inject the customer dao
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    public String listCustomers(Model theModel, @RequestParam(required=false) String sort) {

        // get customers from the service
        List<Customer> theCustomers = null;

        // check for sort field
        if (sort != null) {
            int theSortField = Integer.parseInt(sort);
            theCustomers = customerService.getCustomers(theSortField);
        }
        else {
            // no sort field provided ... default to sorting by last name
            theCustomers = customerService.getCustomers(Utility.LAST_NAME);
        }

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);

        return "list-customer";
    }


    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel){
        Customer theCustomer=new Customer();
        theModel.addAttribute("customer",theCustomer);

        return "customer-form";
    }

    @PostMapping("/saveCustomer")
    public String saveCustomer(@ModelAttribute("customer")Customer theCustomer)
    {
        customerService.saveCustomer(theCustomer);
        return "redirect:/customer/list";
    }

    @GetMapping("/delete")
    public String deleteCustomer(@RequestParam("customerId") int theId)
    {
        customerService.deleteCustomer(theId);
        return "redirect:/customer/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("customerId") int theId,
                                    Model theModel)
    {
        //get the customer from our service
        Customer theCustomer = customerService.getCustomer(theId);

        //set customer as a model attribute to pre-populate the form
        theModel.addAttribute("customer", theCustomer);

        //send over to our form
        return "customer-form";
    }

    @GetMapping("/search")
    public String searchCustomers(@RequestParam("theSearchName") String theSearchName,
                                  Model theModel) {
        // search customers from the service
        List<Customer> theCustomers = customerService.searchCustomers(theSearchName);

        // add the customers to the model
        theModel.addAttribute("customers", theCustomers);
        return "list-customer";
    }

}
