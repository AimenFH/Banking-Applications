package fh_campuswien.banking_applications.message.dto;

import org.springframework.context.annotation.Configuration;

public record AccountsMsgDto(Long accountNumber, String name, String mobileNumber) {

}
