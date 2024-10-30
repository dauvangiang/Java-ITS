package com.dvgiang.electricitybillingsystem.repository.customer;

import com.dvgiang.electricitybillingsystem.dto.query.CustomerWithUnpaidBillsDTO;
import com.dvgiang.electricitybillingsystem.entity.Customer;
import com.dvgiang.electricitybillingsystem.entity.QCustomer;
import com.dvgiang.electricitybillingsystem.entity.QElectricityBill;
import com.dvgiang.electricitybillingsystem.repository.BaseRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//Triển khai các hàm đã khai báo trong CustomerRepositoryCustom
@Repository
class CustomerRepositoryImpl extends BaseRepository<Customer, Long> implements CustomerRepositoryCustom {
    public CustomerRepositoryImpl(EntityManager entityManager) {
        super(Customer.class, entityManager);
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

    @Override
    public Optional<CustomerWithUnpaidBillsDTO> getCustomerWithUnpaidBillsById(Long id) {
        QCustomer qCustomer = QCustomer.customer;
        QElectricityBill qBill = QElectricityBill.electricityBill;

        BooleanBuilder leftJoinBuilder = new BooleanBuilder()
            .and(qCustomer.id.eq(qBill.customer.id))
            .and(qBill.paymentStatus.eq(0)); //unpaid

        BooleanBuilder whereBuilder = new BooleanBuilder()
            .and(qCustomer.id.eq(id))
            .and(qCustomer.status.eq(1));

        CustomerWithUnpaidBillsDTO billCounts = query
            .select(Projections.constructor(CustomerWithUnpaidBillsDTO.class, qCustomer.id, qBill.count()))
            .from(qCustomer)
            .leftJoin(qBill).on(leftJoinBuilder)
            .where(whereBuilder)
            .groupBy(qCustomer.id)
            .fetchOne();

        System.out.println(billCounts);

//        CustomerWithUnpaidBillsDTO customer = query
//            .from(qCustomer)
//            .leftJoin(qBill)
//                .on(leftJoinBuilder)
//            .select(Projections.fields(CustomerWithUnpaidBillsDTO.class, qCustomer.id, qBill.id.count().as("unpaidBillCount")))
//            .where(whereBuilder)
//            .groupBy(qCustomer.id)
//            .fetchOne();

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
