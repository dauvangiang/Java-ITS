package com.dvgiang.electricitybillingsystem.service.customer;

import com.dvgiang.electricitybillingsystem.dto.query.UnpaidBillCountsDTO;
import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.exception.ConflictException;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.customer.CustomerMapper;
import com.dvgiang.electricitybillingsystem.repository.customer.CustomerRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends BaseService<CustomerRepository, CustomerMapper> implements CustomerService {
    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        super(repository, mapper);
    }

    public List<Customer> getCustomers() {
        return repository.getCustomers();
    }

    public Customer getCustomerById(Long id) {
        return repository.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapper.toCustomer(customerDTO);
        return repository.save(customer);
    }

    public String deleteCustomerByID(Long id) {
        UnpaidBillCountsDTO billCounts = repository.getUnpaidBillCountsByCustomerId(id)
                .orElseThrow(() -> new NotFoundException("Customer does not exist!"));

        if (billCounts != null && billCounts.getUnpaidBillsCount() > 0) {
            throw new ConflictException("Customer has " + billCounts.getUnpaidBillsCount() + " unpaid bills!");
        }

        repository.deleteCustomerById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Customer customer = repository.findById(customerDTO.getId())
                .orElseThrow(() -> new NotFoundException("Customer ID does not exist!"));

        mapper.updateCustomer(customer, customerDTO);
        return repository.save(customer);
    }

    public List<Customer> getCustomersByPage(int page, long limit) {
        return repository.getCustomersByPage(page, limit);
    }
}
