package com.ms.accounttransactions_back.adapter.in.web.controller;

import com.ms.accounttransactions_back.adapter.in.web.dto.ApiResponse;
import com.ms.accounttransactions_back.application.port.in.TransactionPort;
import com.ms.accounttransactions_back.domain.Report;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = EntityManager.class)
class ReportControllerTest {
    private static final String IDENTIFICATION = "17458712489";
    private static final String START_DATE = "2023-01-01";
    private static final String END_DATE = "2023-12-31";
    @InjectMocks
    private ReportController reportController;
    @Mock
    private TransactionPort transactionPort;
    private Map<Long, List<Report>> mockReport;

    @BeforeEach
    void setUp() {
        mockReport = Map.of(
                1L, List.of(new Report()),
                2L, List.of(new Report())
        );
    }

    @Test
    void testReportSuccess() {
        // Given
        when(transactionPort.findTransactionsByIdentificationAndDateRange(IDENTIFICATION, START_DATE, END_DATE))
                .thenReturn(mockReport);
        // When
        ResponseEntity<ApiResponse<Map<Long, List<Report>>>> responseEntity = reportController.report(IDENTIFICATION, START_DATE, END_DATE);
        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        ApiResponse<Map<Long, List<Report>>> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);
        assertNotNull(responseBody.getData());
        assertEquals("Operacion ejecutada correctamente", responseBody.getMessage());
    }
}
