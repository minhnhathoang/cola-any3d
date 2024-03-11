package org.nhathm.domain.customer.gateway;

import org.nhathm.domain.customer.Customer;

public interface CustomerGateway {
    Customer getByById(String customerId);
}
