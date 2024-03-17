package org.nhathm.app.customer;

import com.alibaba.cola.catchlog.CatchAndLog;
import com.alibaba.cola.dto.MultiResponse;
import com.alibaba.cola.dto.Response;
import lombok.RequiredArgsConstructor;
import org.nhathm.api.CustomerServiceI;
import org.nhathm.app.customer.executor.CustomerAddCmdExe;
import org.nhathm.app.customer.executor.query.CustomerListByNameQryExe;
import org.nhathm.dto.CustomerAddCmd;
import org.nhathm.dto.CustomerListByNameQry;
import org.nhathm.dto.data.CustomerDTO;
import org.springframework.stereotype.Service;


@Service
@CatchAndLog
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerServiceI {

    private final CustomerAddCmdExe customerAddCmdExe;

    private final CustomerListByNameQryExe customerListByNameQryExe;

    public Response addCustomer(CustomerAddCmd customerAddCmd) {
        return customerAddCmdExe.execute(customerAddCmd);
    }

    @Override
    public MultiResponse<CustomerDTO> listByName(CustomerListByNameQry customerListByNameQry) {
        return customerListByNameQryExe.execute(customerListByNameQry);
    }

}