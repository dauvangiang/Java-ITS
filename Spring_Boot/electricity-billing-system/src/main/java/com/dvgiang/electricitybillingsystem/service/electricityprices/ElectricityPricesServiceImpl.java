package com.dvgiang.electricitybillingsystem.service.electricityprices;

import com.dvgiang.electricitybillingsystem.dto.request.ElectricityPricesDTO;
import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.enums.PermissionType;
import com.dvgiang.electricitybillingsystem.exception.NotFoundException;
import com.dvgiang.electricitybillingsystem.mapper.electricityprices.ElectricityPricesMapper;
import com.dvgiang.electricitybillingsystem.repository.electricityprices.ElectricityPricesRepo;
import com.dvgiang.electricitybillingsystem.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ElectricityPricesServiceImpl extends BaseService<ElectricityPricesRepo, ElectricityPricesMapper> implements ElectricityPricesService {
    public ElectricityPricesServiceImpl(ElectricityPricesRepo repository, ElectricityPricesMapper mapper) {
        super(repository, mapper);
    }

    private final String permission = "ELECTRICITY_PRICES_MANAGEMENT";

    @Override
    public List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type) {
        super.getUser(permission, PermissionType.VIEW);
        return repository.getAllElectricityPrices(isOrderByPrices, type);
    }

    @Override
    public ElectricityPrices getElectricityPriceById(Long id) {
        super.getUser(permission, PermissionType.VIEW);
        return repository.getElectricityPricesById(id)
                .orElseThrow(() -> new NotFoundException("Thông tin bậc giá điện không tồn tại!"));
    }

    @Override
    public ElectricityPrices createElectricityPrices(ElectricityPricesDTO dto) {
        super.getUser(permission, PermissionType.WRITE);
        ElectricityPrices price = mapper.toPrice(dto);
        return repository.save(price);
    }

    @Override
    public ElectricityPrices updateElectricityPrices(ElectricityPricesDTO dto) {
        super.getUser(permission, PermissionType.WRITE);
        ElectricityPrices price = repository.getElectricityPricesById(dto.getId())
                .orElseThrow(() -> new NotFoundException("Thông tin bậc giá điện không tồn tại!"));

        mapper.updatePrice(price, dto);

        return repository.save(price);
    }

    @Override
    public String deleteElectricityPricesById(Long id) {
        super.getUser(permission, PermissionType.DELETE);
        if (repository.existsPriceById(id)) {
            repository.deleteElectricityPricesById(id);
            return "Xóa thành công!";
        } else {
            throw new NotFoundException("Thông tin bậc giá điện không tồn tại!");
        }
    }
}
