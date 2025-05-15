package fh_campuswien.banking_applications.accounts.service;

import fh_campuswien.banking_applications.accounts.dto.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);

    boolean updateAccountStatus(Long accountNumber);
}
