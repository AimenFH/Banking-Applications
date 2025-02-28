package fh_campuswien.banking_applications.accounts.service.impl;

import fh_campuswien.banking_applications.accounts.constants.AccountsConstants;
import fh_campuswien.banking_applications.accounts.dto.AccountsDto;
import fh_campuswien.banking_applications.accounts.dto.CustomerDto;
import fh_campuswien.banking_applications.accounts.entity.Accounts;
import fh_campuswien.banking_applications.accounts.entity.Customer;
import fh_campuswien.banking_applications.accounts.exception.CustomerAlreadyExistsException;
import fh_campuswien.banking_applications.accounts.exception.ResourceNotFoundException;
import fh_campuswien.banking_applications.accounts.mapper.AccountsMapper;
import fh_campuswien.banking_applications.accounts.mapper.CustomerMapper;
import fh_campuswien.banking_applications.accounts.repository.AccountsRepository;
import fh_campuswien.banking_applications.accounts.repository.CustomerRepository;
import fh_campuswien.banking_applications.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        customerRepository.findByEmailOrMobileNumber(customer.getEmail(), customer.getMobileNumber())
                .ifPresent(existingCustomer -> {
                    String message = existingCustomer.getEmail().equals(customer.getEmail())
                            ? "Customer already exists with this email." : "Customer already exists with this mobile number.";
                    throw new CustomerAlreadyExistsException(message);
                });
        customer.setCreatedAt(LocalDateTime.now());
        customer.setCreatedBy("Aymen");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId (customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        return customerDto;
    }

    //// private
    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        newAccount.setCreatedAt(LocalDateTime.now());
        newAccount.setCreatedBy("Aymen");
        return newAccount;
    }
}
