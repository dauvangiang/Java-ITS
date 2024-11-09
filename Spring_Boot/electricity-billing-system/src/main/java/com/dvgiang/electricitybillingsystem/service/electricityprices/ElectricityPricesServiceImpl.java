package com.dvgiang.electricitybillingsystem.service.electricityprices;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.electricityprices.ElectricityPricesMapper;
import com.dvgiang.electricitybillingsystem.repository.electricityprices.ElectricityPricesRepo;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricityPricesServiceImpl extends BaseService<ElectricityPricesRepo, ElectricityPricesMapper> implements ElectricityPricesService{
    public ElectricityPricesServiceImpl(ElectricityPricesRepo repository, ElectricityPricesMapper mapper) {
        super(repository, mapper);
    }

    @Override
    public List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type) {
        return repository.getAllElectricityPrices(isOrderByPrices, type);
    }

    @Override
    public ElectricityPrices getElectricityPriceById(Long id) {
        return repository.getElectricityPricesById(id)
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));
    }

    @Override
    public ElectricityPrices createElectricityPrices(ElectricityPricesDTO dto) {
        ElectricityPrices price = mapper.toPrice(dto);
        return repository.save(price);
    }

    @Override
    public ElectricityPrices updateElectricityPrices(ElectricityPricesDTO dto) {
        ElectricityPrices price = repository.getElectricityPricesById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Electricity prices does not exist!"));

        mapper.updatePrice(price, dto);

        return repository.save(price);
    }

    @Override
    public String deleteElectricityPricesById(Long id) {
        if (repository.existsPriceById(id)) {
            repository.deleteElectricityPricesById(id);
            return "Deleted successfully!";
        } else {
            throw new NotFoundException("Electricity prices does not exist!");
        }
    }
}
