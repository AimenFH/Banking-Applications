package fh_campuswien.banking_applications.accounts.controller;

import fh_campuswien.banking_applications.accounts.dto.AccountsContactInfoDto;
import fh_campuswien.banking_applications.accounts.dto.CustomerDetailsDto;
import fh_campuswien.banking_applications.accounts.dto.ErrorResponseDto;
import fh_campuswien.banking_applications.accounts.service.ICustomersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
@RequiredArgsConstructor
@Tag(name = "Rest API for Customer Details")
@EnableConfigurationProperties(value = AccountsContactInfoDto.class)
public class CustomerController {

    private final ICustomersService iCustomersService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Operation(summary = "Fetch Customer Details REST API")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("campuswien-banking-id") String correlationId,
                                                                   @RequestParam
                                                                   @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                                   String mobileNumber) {
        logger.debug("campuswien-banking-id found:{}", correlationId);
        logger.debug("fetchCustomerDetails method start");
        CustomerDetailsDto customerDetailsDto = iCustomersService.fetchCustomerDetails(correlationId, mobileNumber);
        logger.debug("fetchCustomerDetails method end");
        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }
}
