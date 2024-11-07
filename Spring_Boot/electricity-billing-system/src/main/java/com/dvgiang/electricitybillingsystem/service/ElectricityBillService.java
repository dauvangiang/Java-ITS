package com.dvgiang.electricitybillingsystem.service;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillRequestDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.mapper.ElectricityBillMapper;
import com.dvgiang.electricitybillingsystem.repository.electricitybill.ElectricityBillRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ElectricityBillService {
    private final CustomerService customerService;
    private final ElectricityPricesService electricityPricesService;
    private final ElectricityBillRepo billRepo;
    private final ElectricityBillMapper mapper;

    public ElectricityBill writeElectricityBilling(ElectricityBillRequestDTO requestDTO) {
        Customer customer = customerService.getCustomerById(requestDTO.getCustomerId());

        List<ElectricityPrices> listElectricityPrices = electricityPricesService.getAllElectricityPrices(true, "asc");

        //Số điện đã dùng
        int used = requestDTO.getCurrentReading() - requestDTO.getPreviousReading();

        float totalCost = calcTotalCost(used, listElectricityPrices);

        ElectricityBill bill = mapper.toBill(requestDTO);
        bill.setCreatedAt(new Date());
        bill.setCustomerId(customer.getId());
        bill.setTotalCost(totalCost);

        log.info("Create new electricity bill for customer (customerId: {})", requestDTO.getCustomerId());

        return billRepo.save(bill);
    }

    public List<ElectricityBill> getUnpaidBillsByCustomerId(Long id) {
        customerService.getCustomerById(id);
        return billRepo.getUnpaidBillsByCustomerId(id);
    }

    private float calcTotalCost(int used, List<ElectricityPrices> listPrices) {
        float totalCost = 0f;
        int i = 0;
        while (used > 0 && i < listPrices.size() - 1) {
            //Khoảng dử dụng điện trong mức giá
            int capacity = listPrices.get(i).getMaxUse() - listPrices.get(i).getMinUse() + 1;
            /*
             * Số điện còn lại <= capacity
             * Tiền điện (totalCost) += số điện x giá
             */
            if (used <= capacity) {
                totalCost += used * listPrices.get(i).getPrice();
                used = 0;
            }
            //Ngược lại, totalCost += capacity x giá
            else {
                totalCost += capacity * listPrices.get(i).getPrice();
                used -= capacity;
            }

            i++;
        }

        //Trường hợp số điện còn lại vượt mức giá cao nhất
        if (used > 0) {
            totalCost += used * listPrices.get(i).getPrice();
        }

        return totalCost;
    }
}
