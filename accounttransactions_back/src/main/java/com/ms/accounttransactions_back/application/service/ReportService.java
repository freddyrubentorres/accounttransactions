package com.ms.accounttransactions_back.application.service;

import com.ms.accounttransactions_back.domain.Report;

import java.util.List;
import java.util.Map;

/**
 * @author : Freddy Torres
 * file :  ReportService
 * @since : 5/4/2025, sáb
 **/

public interface ReportService {
    Map<Long, List<Report>> getTransactionsByIdentificationAndDate(String identification, String startDate, String endDate);
}
