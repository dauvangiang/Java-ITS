package com.dvgiang.electricitybillingsystem.repository.electricityprices;

import com.dvgiang.electricitybillingsystem.entity.ElectricityPrices;
import com.dvgiang.electricitybillingsystem.entity.QElectricityPrices;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ElectricityPricesRepoImpl extends BaseRepository implements ElectricityPricesRepoCustom {
    public ElectricityPricesRepoImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public List<ElectricityPrices> getAllElectricityPrices(boolean isOrderByPrices, String type) {
        QElectricityPrices qPrice = QElectricityPrices.electricityPrices;

         JPAQuery<ElectricityPrices> queryResult = query
                 .from(qPrice)
                .select(qPrice)
                .where(qPrice.status.eq(1));

         //default
         if (!isOrderByPrices || type == null || type.isEmpty()) {
             return queryResult.fetch();
         }
         //order by asc prices
         else if (type.equals("asc")) {
             return queryResult.orderBy(qPrice.price.asc()).fetch();
         }
         // order by desc prices
         else if (type.equals("desc")) {
             return queryResult.orderBy(qPrice.price.desc()).fetch();
         }
         return null;
    }

    @Override
    public Optional<ElectricityPrices> getElectricityPricesById(Long id) {
        QElectricityPrices qPrice = QElectricityPrices.electricityPrices;

        BooleanBuilder builder = new BooleanBuilder()
                .and(qPrice.id.eq(id))
                .and(qPrice.status.eq(1));

        return Optional.ofNullable(
                query.from(qPrice)
                        .select(qPrice)
                        .where(builder)
                        .fetchOne()
        );
    }

    @Override
    public boolean existsPriceById(Long id) {
        QElectricityPrices qPrice = QElectricityPrices.electricityPrices;

        BooleanBuilder builder = new BooleanBuilder()
                .and(qPrice.id.eq(id))
                .and(qPrice.status.eq(1));

        return query.from(qPrice)
                .where(builder)
                .select(qPrice.id)
                .fetchFirst() != null;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteElectricityPricesById(Long id) {
        QElectricityPrices qPrice = QElectricityPrices.electricityPrices;

        query.update(qPrice)
                .where(qPrice.id.eq(id))
                .set(qPrice.status, 0)
                .execute();
    }
}
