package user_common.application.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_common.domain.dto.DepositHistoryDTO;
import user_common.domain.dto.DepositRequestDTO;
import user_common.domain.dto.TransferHistoryDTO;
import user_common.domain.dto.TransferRequestDTO;
import user_common.domain.record.DepositResponse;
import user_common.domain.record.TransferResponse;
import user_common.domain.service.OperationsService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationsController {

    private final OperationsService operationsService;

    OperationsController(OperationsService operationsService){
        this.operationsService = operationsService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(@RequestBody DepositRequestDTO requestDeposit) {
        DepositResponse depositHistoryCreated = operationsService.deposit(requestDeposit);
        return ResponseEntity.status(HttpStatus.CREATED).body(depositHistoryCreated);
    }

    @PostMapping("/transfer")
    ResponseEntity<TransferResponse> transfer(@RequestBody TransferRequestDTO requestTransfer){
        TransferResponse transferHistoryCreated = operationsService.transfer(requestTransfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(transferHistoryCreated);
    }
}
