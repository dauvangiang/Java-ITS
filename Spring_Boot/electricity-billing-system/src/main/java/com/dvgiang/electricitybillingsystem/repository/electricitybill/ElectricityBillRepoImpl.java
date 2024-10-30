package com.dvgiang.electricitybillingsystem.repository.electricitybill;

import com.dvgiang.electricitybillingsystem.entity.ElectricityBill;
import com.dvgiang.electricitybillingsystem.entity.QElectricityBill;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ElectricityBillRepoImpl extends BaseRepository implements ElectricityBillRepoCustom {
    public ElectricityBillRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<ElectricityBill> getUnpaidBillsByCustomerId(Long id) {
        QElectricityBill qBill = QElectricityBill.electricityBill;

        BooleanBuilder builder = new BooleanBuilder()
                .and(qBill.customer.id.eq(id))
                .and(qBill.paymentStatus.eq(0));

        return query.from(qBill)
                .select(qBill)
                .where(builder)
                .fetch();
    }
}
