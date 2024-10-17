package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO;
import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.exception.ConflictException;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import com.dvgiang.electricitybillingsystem.repository.ElectricityBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final ElectricityBillRepository electricityBillRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        //Nếu không có bản ghi phù hợp, ném ra ngoại lệ: Not Found
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer does not exist!");
        }

        return customer.get();
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        log.info("Created new customer");
        Customer customer = Customer
            .builder()
            .fullName(customerDTO.getName())
            .phone(customerDTO.getPhone())
            .address(customerDTO.getAddress())
            .createdAt(new Date(System.currentTimeMillis()))
            .build();
        return customerRepository.save(customer);
    }

    public String deleteCustomerByID(Long id) {
//        Optional<Customer> customer = customerRepository.findById(id);
//        if (customer.isEmpty()) {
//            throw new NotFoundException("Customer does not exist!");
//        }
//
//        //Nếu khách hàng còn nợ hóa đơn phải thanh toán trước
//        List<ElectricityBill> unpaidBills = electricityBillRepository.findUnpaidBillsByCustomerId(id);
//        if (!unpaidBills.isEmpty()) {
//            throw new ConflictException("Customer has " + unpaidBills.size() + " unpaid bills!");
//        }

        Optional<CustomerWithUnpaidBillsDTO> data = customerRepository.findCustomerWithUnpaidBillsById(id);
        if (data.isEmpty()) {
            throw new NotFoundException("Customer does not exist!");
        }

        CustomerWithUnpaidBillsDTO customer = data.get();
        if (customer.getUnpaidBillsCount() > 0) {
            throw new ConflictException("Customer has " + customer.getUnpaidBillsCount() + " unpaid bills!");
        }

        log.info("Deleted a customer");

        //Cập nhật trạng thái KH thành 0 (ngừng sử dụng dịch vụ)
        customerRepository.deleteCustomerById(id);
        return "Deleted successfully!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        Optional<Customer> customer = customerRepository.findById(customerDTO.getId());
        if (customer.isEmpty()) {
            throw new NotFoundException("Customer ID does not exist!");
        }

        Customer customerUpdate = customer.get();

        customerUpdate.setFullName(customerDTO.getName());
        customerUpdate.setPhone(customerDTO.getPhone());
        customerUpdate.setAddress(customerDTO.getAddress());
        customerUpdate.setUpdatedAt(new Date(System.currentTimeMillis()));

        log.info("Updated a customer");

        return customerRepository.save(customerUpdate);
    }
}
