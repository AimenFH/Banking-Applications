package fh_campuswien.banking_applications.accounts.service.impl;

import fh_campuswien.banking_applications.accounts.dto.AccountsDto;
import fh_campuswien.banking_applications.accounts.dto.CardsDto;
import fh_campuswien.banking_applications.accounts.dto.CustomerDetailsDto;
import fh_campuswien.banking_applications.accounts.dto.LoansDto;
import fh_campuswien.banking_applications.accounts.entity.Accounts;
import fh_campuswien.banking_applications.accounts.entity.Customer;
import fh_campuswien.banking_applications.accounts.exception.ResourceNotFoundException;
import fh_campuswien.banking_applications.accounts.mapper.AccountsMapper;
import fh_campuswien.banking_applications.accounts.mapper.CustomerMapper;
import fh_campuswien.banking_applications.accounts.repository.AccountsRepository;
import fh_campuswien.banking_applications.accounts.repository.CustomerRepository;
import fh_campuswien.banking_applications.accounts.service.ICustomersService;
import fh_campuswien.banking_applications.accounts.service.client.CardsFeignClient;
import fh_campuswien.banking_applications.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CustomerServiceImpl implements ICustomersService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;


    @Override
    public CustomerDetailsDto fetchCustomerDetails(String correlationId, String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (null != loansDtoResponseEntity) {
            customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());

        }
            ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (null != cardsDtoResponseEntity) {
            customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
        }
        return customerDetailsDto;
    }
}
