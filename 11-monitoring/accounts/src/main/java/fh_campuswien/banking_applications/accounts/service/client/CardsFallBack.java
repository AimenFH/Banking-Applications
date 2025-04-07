package fh_campuswien.banking_applications.accounts.service.client;

import fh_campuswien.banking_applications.accounts.dto.CardsDto;
import fh_campuswien.banking_applications.accounts.dto.LoansDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CardsFallBack implements CardsFeignClient {

    @Override
    public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
        return null;
    }
}
