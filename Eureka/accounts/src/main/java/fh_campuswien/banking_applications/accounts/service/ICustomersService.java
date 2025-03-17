package fh_campuswien.banking_applications.accounts.service;

import fh_campuswien.banking_applications.accounts.dto.CustomerDetailsDto;

public interface ICustomersService {

    CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
