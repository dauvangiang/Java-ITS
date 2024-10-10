package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.model.Configuration;
import com.dvgiang.electricitybillingsystem.model.Customer;
import com.dvgiang.electricitybillingsystem.model.ElectricityBill;
import com.dvgiang.electricitybillingsystem.repository.ConfigurationRepository;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import com.dvgiang.electricitybillingsystem.repository.ElectricityBillRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ElectricityBillService {
  private final ElectricityBillRepository billRepository;
  private final CustomerRepository customerRepository;
  private final ConfigurationRepository configurationRepository;

  //Ghi so dien
  public ElectricityBill writeElectricityBilling(ElectricityBillRequestDTO requestDTO) {
    Optional<Customer> customer = customerRepository.findById(requestDTO.getCustomerId());
    if (customer.isEmpty()) {
      throw new NotFoundException("Customer ID not found!");
    }

    //Số điện đã dùng
    int used = requestDTO.getCurrentReading() - requestDTO.getPreviousReading();

    //Sắp xếp tăng dần theo giá
    Sort sort = Sort.by(Sort.Direction.ASC, "price");
    List<Configuration> configurations = configurationRepository.findAll(sort);

    float totalCost = 0f;

    for (Configuration config : configurations) {
      if (used <= 0) break;

      //Nếu số điện còn lại không vượt quá giới hạn
      //Tính tiền => hoàn tất
      if (config.getMaxUse() == null || used <= config.getMaxUse() - config.getMinUse() + 1) {
        totalCost += used * config.getPrice();
        break;
      }
      //Nếu không, tiếp tục tính
      int temp = config.getMaxUse() - config.getMinUse() + 1;
      totalCost += temp * config.getPrice();
      used -= temp;
    }

    ElectricityBill bill = ElectricityBill.builder()
        .customer(customer.get())
        .writingDate(new Date())
        .billingPeriod(requestDTO.getBillingPeriod())
        .currentReading(requestDTO.getCurrentReading())
        .previousReading(requestDTO.getPreviousReading())
        .used(used)
        .totalCost(totalCost)
        .build();

    return billRepository.save(bill);
  }

  //Tra cuu hoa don bang ma KH
  public ElectricityBill getBillByCustomerId(Long customerId) {
    ElectricityBill bill = billRepository.findByCustomerId(customerId);
    if (bill == null) {
      throw new NotFoundException("Customer ID not found!");
    }
    return bill;
  }
}
