package fh_campuswien.banking_applications.accounts.service.impl;

import fh_campuswien.banking_applications.accounts.dto.CustomerDto;
import fh_campuswien.banking_applications.accounts.repository.AccountsRepository;
import fh_campuswien.banking_applications.accounts.repository.CustomerRepository;
import fh_campuswien.banking_applications.accounts.service.IAccountsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountsServiceImpl implements IAccountsService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;
    @Override
    public void createAccount(CustomerDto customerDto) {

    }
}
