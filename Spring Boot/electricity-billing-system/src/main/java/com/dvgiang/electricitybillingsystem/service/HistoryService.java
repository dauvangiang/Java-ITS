package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.HistoryDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.model.History;
import com.dvgiang.electricitybillingsystem.repository.ConfigurationRepository;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import com.dvgiang.electricitybillingsystem.repository.HistoryRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {
    private final HistoryRepository historyRepository;
    private final ConfigurationRepository configurationRepository;
    private final CustomerRepository customerRepository;

    //Cóntructor DI
    public HistoryService(HistoryRepository historyRepository, ConfigurationRepository configurationRepository, CustomerRepository customerRepository) {
        this.historyRepository = historyRepository;
        this.configurationRepository = configurationRepository;
        this.customerRepository = customerRepository;
    }

    //Tinh tien va luu lai lich su
    public History getHistoryDetail(HistoryDTO historyDTO) {
        //Lấy thông tin khach hang theo id
        Optional<Customer> customer = customerRepository.findById(historyDTO.getCustomerId());

        if (customer.isEmpty()) {
            throw new NotFoundException("Customer ID does not exist!");
        }

        //Sắp xếp tăng dần theo giá
        Sort sort = Sort.by(Sort.Direction.ASC, "price");
        List<Configuration> configurations = configurationRepository.findAll(sort);

        //Số điện đã dùng
        int used = historyDTO.getUsed();

//        float totalCost = 0f;
//
//        for (Configuration config : configurations) {
//            if (used <= 0) break;
//
//            //Nếu số điện còn lại không vượt quá giới hạn
//            //Tính tiền => hoàn tất
//            if (config.getMaxUse() == null || used <= config.getMaxUse() - config.getMinUse() + 1) {
//                totalCost += used * config.getPrice();
//                break;
//            }
//            //Nếu không, tiếp tục tính
//            int temp = config.getMaxUse() - config.getMinUse() + 1;
//            totalCost += temp * config.getPrice();
//            used -= temp;
//        }

        History history = new History();
//        history.setCustomer(customer.get());
//        history.setBillingPeriod(historyDTO.getBillingPeriod());
//        history.setTotalCost(totalCost);

        return historyRepository.save(history);
    }

    //Danh sach lich su theo ma khach hang
    public List<History> getListHistoryByCustomerId(Long customerId) {

        return historyRepository.findAllByCustomerId(customerId);
    }
}
