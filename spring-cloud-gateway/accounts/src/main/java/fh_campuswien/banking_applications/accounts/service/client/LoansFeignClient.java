package fh_campuswien.banking_applications.accounts.service.client;

import fh_campuswien.banking_applications.accounts.dto.CardsDto;
import fh_campuswien.banking_applications.accounts.dto.LoansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoansFeignClient {

    @GetMapping(value = "/api/v1/loans/fetch", consumes = "application/json")
    public ResponseEntity<LoansDto> fetchLoanDetails(@RequestHeader("campuswien-banking-id") String correlationId, @RequestParam String mobileNumber);
}
