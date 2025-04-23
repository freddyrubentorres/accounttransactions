package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.domain.Report;
import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.adapter.in.web.dto.ResponseUtil;
import com.ms.accounttransactions_back.application.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  ReportController
 * @since : 5/4/2025, sáb
 **/

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("${endpoint.reports.base}")
public class ReportController {
    @Value("${controller.ok}")
    public String mensaje;

    private final ReportService reportService;

    @GetMapping()
    public ResponseEntity<ApiResponse<Map<Long, List<Report>>>> getTransactionsByIdentificationAndDate(
            @RequestParam("identification") String identification,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        var reportDto = reportService.getTransactionsByIdentificationAndDate(identification, startDate, endDate);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(mensaje, reportDto));
    }
}
