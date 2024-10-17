package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.repository.ElectricityPricesRepository;
import com.dvgiang.electricitybillingsystem.repository.CustomerRepository;
import com.dvgiang.electricitybillingsystem.repository.ElectricityBillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityBillService {
  private final ElectricityBillRepository billRepository;
  private final CustomerRepository customerRepository;
  private final ElectricityPricesRepository electricityPricesRepository;

  //Ghi so dien
  public ElectricityBill writeElectricityBilling(ElectricityBillRequestDTO requestDTO) {
    Optional<Customer> customer = customerRepository.findById(requestDTO.getCustomerId());
    if (customer.isEmpty()) {
      throw new NotFoundException("Customer ID not found!");
    }

    //Sắp xếp tăng dần theo giá
    Sort sort = Sort.by(Sort.Direction.ASC, "price");
    List<ElectricityPrices> listElectricityPrices = electricityPricesRepository.findAll(sort);

    //Số điện đã dùng
    int used = requestDTO.getCurrentReading() - requestDTO.getPreviousReading();

    float totalCost = calcTotalCost(used, listElectricityPrices);

    ElectricityBill bill = ElectricityBill.builder()
        .customer(customer.get())
        .writingDate(new Date())
        .billingPeriod(requestDTO.getBillingPeriod())
        .currentReading(requestDTO.getCurrentReading())
        .previousReading(requestDTO.getPreviousReading())
        .used(used)
        .totalCost(totalCost)
        .build();

    String logStr = String.format("Create new electricity bill for customer (customerId: %d)", requestDTO.getCustomerId());
    log.info(logStr);
    return billRepository.save(bill);
  }

  //Tra cuu hoa don bang ma KH
  public List<ElectricityBill> getAllBillUnpaidByCustomerId(Long id) {
    Optional<Customer> customer = customerRepository.findById(id);
    if (customer.isEmpty()) {
      throw new NotFoundException("Customer does not exist!");
    }
    return billRepository.findUnpaidBillsByCustomerId(id);
  }

  //Tinh tien dien
  private float calcTotalCost(int used, List<ElectricityPrices> listPrices) {
    float totalCost = 0f;
    int i = 0;
    while (used > 0 && i < listPrices.size() - 1) {
      //Khoang su dung dien trong muc gia
      int capacity = listPrices.get(i).getMaxUse() - listPrices.get(i).getMinUse() + 1;
      /*
       * neu so dien con lai <= khoang su dung trong gia dien hien tai
       * so dien nhan gia tien
       */
      if (used <= capacity) {
        totalCost += used * listPrices.get(i).getPrice();
        used = 0;
      }
      /*
       * Nguoc lai
       * tien tien dien theo capacity
       * cap nhat lai so dien
       */
      else {
        totalCost += capacity * listPrices.get(i).getPrice();
        used -= capacity;
      }
      //Bac gia tiep theo
      i++;
    }

    //Tinh tien neu so dien con lai vuot muc bac gia cao nhat
    if (used > 0) {
      totalCost += used * listPrices.get(i).getPrice();
    }

    return totalCost;
  }
}
