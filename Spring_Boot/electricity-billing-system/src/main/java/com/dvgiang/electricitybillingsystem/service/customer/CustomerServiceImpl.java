package com.dvgiang.electricitybillingsystem.service.customer;

import com.dvgiang.electricitybillingsystem.dto.query.UnpaidBillCountsDTO;
import com.dvgiang.electricitybillingsystem.dto.request.CustomerDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.exception.ConflictException;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.customer.CustomerMapper;
import com.dvgiang.electricitybillingsystem.repository.customer.CustomerRepository;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl extends BaseService<CustomerRepository, CustomerMapper> implements CustomerService {
    private final String permission = "CUSTOMER_MANAGEMENT";

    public CustomerServiceImpl(CustomerRepository repository, CustomerMapper mapper) {
        super(repository, mapper);
    }

    public List<Customer> getCustomers() {
        super.getUser(permission, PermissionType.VIEW);
        return repository.getCustomers();
    }

    public Customer getCustomerById(Long id, boolean requiredLogin) {
        if (requiredLogin) {
            super.getUser(permission, PermissionType.VIEW);
        }
        return repository.getCustomerById(id)
                .orElseThrow(() -> new NotFoundException("Thông tin khách hàng không tồn tại!"));
    }

    public Customer createCustomer(CustomerDTO customerDTO) {
        super.getUser(permission, PermissionType.WRITE);
        Customer customer = mapper.toCustomer(customerDTO);
        return repository.save(customer);
    }

    public String deleteCustomerByID(Long id) {
        super.getUser(permission, PermissionType.DELETE);
        UnpaidBillCountsDTO billCounts = repository.getUnpaidBillCountsByCustomerId(id)
                .orElseThrow(() -> new NotFoundException("Thông tin khách hàng không tồn tại!"));

        if (billCounts != null && billCounts.getUnpaidBillsCount() > 0) {
            throw new ConflictException("Khách hàng có " + billCounts.getUnpaidBillsCount() + " hóa đơn chưa thanh toán!");
        }

        repository.deleteCustomerById(id);
        return "Xóa thành công!";
    }

    public Customer updateCustomer(CustomerDTO customerDTO) {
        super.getUser(permission, PermissionType.WRITE);
        Customer customer = repository.findById(customerDTO.getId())
                .orElseThrow(() -> new NotFoundException("Thông tin khách hàng không tồn tại!"));

        mapper.updateCustomer(customer, customerDTO);
        return repository.save(customer);
    }

    public List<Customer> getCustomersByPage(int page, long limit) {
        return repository.getCustomersByPage(page, limit);
    }
}
