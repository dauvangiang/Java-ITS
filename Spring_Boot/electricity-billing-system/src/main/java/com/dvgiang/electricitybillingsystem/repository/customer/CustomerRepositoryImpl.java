package com.dvgiang.electricitybillingsystem.repository.customer;

import com.dvgiang.electricitybillingsystem.dto.query.UnpaidBillCountsDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.entity.QCustomer;
import com.dvgiang.electricitybillingsystem.entity.QElectricityBill;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class CustomerRepositoryImpl extends BaseRepository implements CustomerRepositoryCustom {
    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        QCustomer qCustomer = QCustomer.customer;

        BooleanBuilder builder = new BooleanBuilder()
            .and(qCustomer.id.eq(id))
            .and(qCustomer.status.eq(1));

        Customer customer = query.from(qCustomer)
            .select(qCustomer)
            .where(builder)
            .fetchOne();

        return Optional.ofNullable(customer);
    }

    @Override
    public List<Customer> getCustomers() {
        QCustomer qCustomer = QCustomer.customer;
        return query.from(qCustomer)
                //create CustomerDTO obj
//                .select(Projections.fields(CustomerDTO.class, qCustomer.fullName, qCustomer.phone))
                .select(qCustomer)
                .orderBy(qCustomer.id.asc())
                .fetch();
    }

    public List<Customer> getCustomersByPage(int page, long limit) {
        QCustomer qCustomer = QCustomer.customer;
        return query
            .from(qCustomer)
            .select(qCustomer)
            .offset(limit * (page - 1)) //Số bản ghi bị bỏ qua
            .limit(limit) //Số bản ghi tối đa mỗi lần truy vấn
            .fetch();
    }

    @Override
    public Optional<UnpaidBillCountsDTO> getUnpaidBillCountsByCustomerId(Long id) {
        QCustomer qCustomer = QCustomer.customer;
        QElectricityBill qBill = QElectricityBill.electricityBill;

        BooleanBuilder leftJoinBuilder = new BooleanBuilder()
                .and(qCustomer.id.eq(qBill.customerId))

            .and(qBill.paymentStatus.eq(0)); //unpaid

        BooleanBuilder whereBuilder = new BooleanBuilder()
            .and(qCustomer.id.eq(id))
            .and(qCustomer.status.eq(1));

        //Subquery: count unpaid bill by customer id
        UnpaidBillCountsDTO billCounts = query
            .select(Projections.constructor(
                UnpaidBillCountsDTO.class,
                qCustomer.id,
                JPAExpressions.select(qBill.id.count())
                    .from(qBill)
                    .where(qBill.customerId.eq(id).and(qBill.paymentStatus.eq(0)))
                ))
            .from(qCustomer)
            .where(qCustomer.id.eq(id))
            .fetchOne();

        return Optional.ofNullable(billCounts);
    }

    @Override
    @Transactional
    @Modifying
    public void deleteCustomerById(Long id) {
        QCustomer qCustomer = QCustomer.customer;

        query.update(qCustomer).where(qCustomer.id.eq(id))
            .set(qCustomer.status, 0)
            .execute();
    }
}
