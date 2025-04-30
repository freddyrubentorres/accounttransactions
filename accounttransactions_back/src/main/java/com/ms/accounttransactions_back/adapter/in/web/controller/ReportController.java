package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.util.ResponseUtil;
import com.ms.accounttransactions_back.application.port.in.TransactionPort;
import com.ms.accounttransactions_back.domain.Report;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file : ReportController
 * @since : 25/4/2025, vie
 **/

@RestController
@RequiredArgsConstructor
@RequestMapping("/reports")
public class ReportController {
    private final TransactionPort transactionPort;
    public static final String SUCCESS_MESSAGE = "Operacion ejecutada correctamente";

    @GetMapping
    public ResponseEntity<ApiResponse<Map<Long, List<Report>>>> report(@RequestParam("identification") String identification, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        var report = transactionPort.findTransactionsByIdentificationAndDateRange(identification, startDate, endDate);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(SUCCESS_MESSAGE, report));
    }
}
