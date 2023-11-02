package lk.ijse.userservicemysql.service;

import lk.ijse.userservicemysql.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomer(CustomerDTO customerDTO);
    CustomerDTO getSelectedCustomer(String customerId);
    CustomerDTO getSelectedCustomerByUserName(String userName);
    void updateCustomer(CustomerDTO customerDTO);
    void deleteCustomer(String customerId);
    List<CustomerDTO> getAllCustomer();
}
