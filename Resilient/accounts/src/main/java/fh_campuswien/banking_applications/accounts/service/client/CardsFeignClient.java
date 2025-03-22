package fh_campuswien.banking_applications.accounts.service.client;

import fh_campuswien.banking_applications.accounts.dto.CardsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "/api/v1/cards/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("campuswien-banking-id") String correlationId, @RequestParam String mobileNumber);
}
