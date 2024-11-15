package com.dvgiang.electricitybillingsystem.service.electricitybill;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityBillDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.mapper.electricitybill.ElectricityBillMapper;
import com.dvgiang.electricitybillingsystem.repository.electricitybill.ElectricityBillRepo;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import com.dvgiang.electricitybillingsystem.service.customer.CustomerService;
import com.dvgiang.electricitybillingsystem.service.electricityprices.ElectricityPricesService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ElectricityBillServiceImpl extends BaseService<ElectricityBillRepo, ElectricityBillMapper> implements ElectricityBillService{
    private final CustomerService customerService;
    private final ElectricityPricesService electricityPricesService;

    public ElectricityBillServiceImpl(
            ElectricityBillRepo repository,
            ElectricityBillMapper mapper,
            CustomerService customerService,
            ElectricityPricesService electricityPricesService
    ) {
        super(repository, mapper);
        this.customerService = customerService;
        this.electricityPricesService = electricityPricesService;
    }

    @Override
    public ElectricityBill writeElectricityBilling(ElectricityBillDTO dto) {
        super.getUser("ELECTRICITY_BILL_MANAGEMENT", PermissionType.WRITE);
        Customer customer = customerService.getCustomerById(dto.getCustomerId(), true);

        List<ElectricityPrices> listElectricityPrices = electricityPricesService.getAllElectricityPrices(true, "asc");

        //Số điện đã dùng
        int used = dto.getCurrentReading() - dto.getPreviousReading();

        float totalCost = calcTotalCost(used, listElectricityPrices);

        ElectricityBill bill = mapper.toBill(dto);
        bill.setCreatedAt(new Date());
        bill.setCustomerId(customer.getId());
        bill.setTotalCost(totalCost);

        return repository.save(bill);
    }

    @Override
    public List<ElectricityBill> getUnpaidBillsByCustomerId(Long id) {
        customerService.getCustomerById(id, false);
        return repository.getUnpaidBillsByCustomerId(id);
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
