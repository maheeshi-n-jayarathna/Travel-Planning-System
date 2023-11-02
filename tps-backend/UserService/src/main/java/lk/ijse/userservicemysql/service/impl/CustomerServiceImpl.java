package lk.ijse.userservicemysql.service.impl;

import lk.ijse.userservicemysql.dto.CustomerDTO;
import lk.ijse.userservicemysql.entity.Customer;
import lk.ijse.userservicemysql.exception.DuplicateException;
import lk.ijse.userservicemysql.exception.NotFoundException;
import lk.ijse.userservicemysql.persistance.CustomerDao;
import lk.ijse.userservicemysql.service.CustomerService;
import lk.ijse.userservicemysql.util.DataTypeConvertor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDao customerDao;
    private final DataTypeConvertor convertor;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {
        if (customerDao.findByNic(customerDTO.getNic()).isPresent())
            throw new DuplicateException("Customer nic duplicated");
        if (customerDao.findByUserName(customerDTO.getUserName()).isPresent())
            throw new DuplicateException("Customer userName duplicated");
        String customerId;
        do {
            customerId = String.format("C%S", UUID.randomUUID());
        } while (customerDao.findById(customerId).isPresent());
        customerDTO.setCustomerId(customerId);
        return convertor.getCustomerDTO(customerDao.save(convertor.getCustomer(customerDTO)));
    }

    @Override
    public CustomerDTO getSelectedCustomer(String customerId) {
        return convertor.getCustomerDTO(customerDao.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found")));
    }

    @Override
    public CustomerDTO getSelectedCustomerByUserName(String userName) {
        return convertor.getCustomerDTO(customerDao.findByUserName(userName).orElseThrow(() -> new NotFoundException("Customer not found")));
    }

    @Override
    public void updateCustomer(CustomerDTO customerDTO) {
        customerDao.findById(customerDTO.getCustomerId()).orElseThrow(() -> new NotFoundException("Customer not found"));
        Optional<Customer> optionalCustomerForNic = customerDao.findByNic(customerDTO.getNic());
        if (optionalCustomerForNic.isPresent() && !optionalCustomerForNic.get().getCustomerId().equals(customerDTO.getCustomerId()))
            throw new DuplicateException("Duplicate customer nic");
        Optional<Customer> optionalCustomerForUserName = customerDao.findByUserName(customerDTO.getUserName());
        if (optionalCustomerForUserName.isPresent() && !optionalCustomerForUserName.get().getCustomerId().equals(customerDTO.getCustomerId()))
            throw new DuplicateException("Duplicate customer userName");
        customerDao.save(convertor.getCustomer(customerDTO));
    }

    @Override
    public void deleteCustomer(String customerId) {
        customerDao.findById(customerId).orElseThrow(() -> new NotFoundException("Customer not found"));
        // in use
        customerDao.deleteById(customerId);
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        return customerDao.findAll().stream().map(convertor::getCustomerDTO).collect(Collectors.toList());
    }
}
