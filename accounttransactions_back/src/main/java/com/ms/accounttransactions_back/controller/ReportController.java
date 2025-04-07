package com.ms.accounttransactions_back.controller;

import com.ms.accounttransactions_back.constants.ProcessConstants;
import com.ms.accounttransactions_back.dto.ReportDto;
import com.ms.accounttransactions_back.dto.response.ApiResponse;
import com.ms.accounttransactions_back.dto.response.ResponseUtil;
import com.ms.accounttransactions_back.service.ReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
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

@PropertySource("classpath:messages.properties")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(ProcessConstants.API_PATH_REPORTS)
public class ReportController {
    @Value("${controller.ok}")
    public String CONTROLLER_OK;

    private final ReportService reportService;

    @GetMapping()
    public ResponseEntity<ApiResponse<Map<Long, List<ReportDto>>>> getTransactionsByIdentificationAndDate(
            @RequestParam("identification") String identification,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate
    ) {
        var reportDto = reportService.getTransactionsByIdentificationAndDate(identification, startDate, endDate);
        return ResponseEntity.ok(ResponseUtil.createSuccessResponse(CONTROLLER_OK, reportDto));
    }
}
