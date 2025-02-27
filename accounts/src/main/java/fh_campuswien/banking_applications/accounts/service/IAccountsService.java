package fh_campuswien.banking_applications.accounts.service;

import fh_campuswien.banking_applications.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);
}
