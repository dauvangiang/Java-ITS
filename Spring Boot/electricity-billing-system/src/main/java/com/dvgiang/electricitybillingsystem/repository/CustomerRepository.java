package com.dvgiang.electricitybillingsystem.repository;

import com.dvgiang.electricitybillingsystem.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Repository
public class CustomerRepository {
    private static final String FILE_PATH = "data.json";
    private List<Customer> customers;

    public CustomerRepository() {
        try {
            //Get file data.json
            File fileJson = new ClassPathResource(FILE_PATH).getFile();

            ObjectMapper mapper = new ObjectMapper();
            //read file then transform into list customer
            customers = mapper.readValue(fileJson, new TypeReference<List<Customer>>() {});
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //Get all customers
    public List<Customer> fillAll() {
        return customers;
    }

    //Find customer by id
    public Customer findById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) return customer;
        }
        return null;
    }

    //Add new customer
    public boolean addCustomer(Customer customer) {
        return customers.add(customer);
    }

    //Delete customer by id
    public boolean deleteCustomer(int id) {
        int idx = 0, n = customers.size();
        while (idx < n && customers.get(idx).getId() != id) {
            idx++;
        }
        if (idx == n) {
            return false;
        }
        customers.remove(idx);
        return true;
    }

    //Update customer infor
    public boolean updateCustomer(Customer customer) {
        int idx = 0, n = customers.size();
        while (idx < n && customers.get(idx).getId() != customer.getId()) {
            idx++;
        }
        if (idx == n) {
            return false;
        }
        customers.get(idx).setName(customer.getName());
        customers.get(idx).setAddress("Street", "suite", "city");
        customers.get(idx).setPhone(customer.getPhone());
        return true;
    }
}
