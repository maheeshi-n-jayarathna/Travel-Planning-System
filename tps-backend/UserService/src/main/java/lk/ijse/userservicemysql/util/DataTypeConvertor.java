package lk.ijse.userservicemysql.util;

import lk.ijse.userservicemysql.dto.CustomerDTO;
import lk.ijse.userservicemysql.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
@RequiredArgsConstructor
public class DataTypeConvertor {
    private final ModelMapper modelMapper;

    public CustomerDTO getCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = modelMapper.map(customer, CustomerDTO.class);
        customerDTO.setProfile(Base64.getDecoder().decode(customer.getProfile()));
        return customerDTO;
    }

    public Customer getCustomer(CustomerDTO customerDTO) {
        Customer customer = modelMapper.map(customerDTO, Customer.class);
        customer.setProfile(Base64.getEncoder().encodeToString(customerDTO.getProfile()));
        customer.setPassword(BCrypt.hashpw(customerDTO.getPassword(), BCrypt.gensalt()));
        return customer;
    }
}
