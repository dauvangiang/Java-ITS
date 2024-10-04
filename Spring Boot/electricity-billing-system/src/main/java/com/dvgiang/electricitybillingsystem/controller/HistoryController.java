package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.HistoryDTO;
import com.dvgiang.electricitybillingsystem.model.History;
import com.dvgiang.electricitybillingsystem.response.ResponseHandler;
import com.dvgiang.electricitybillingsystem.service.HistoryService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/histories")
public class HistoryController {
    private final HistoryService historyService;

    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/search")
    public ResponseEntity<Object> getHistoryDetail(@Valid @RequestBody HistoryDTO historyDTO) {
        History history = historyService.getHistoryDetail(historyDTO);
        return ResponseHandler.responseBuilder(history, HttpStatus.OK, null, null);
    }

    @GetMapping("/search/{customerId}")
    public ResponseEntity<Object> getListHistoryByCustomerId(@PathVariable Long customerId) {
        List<History> listHistory = historyService.getListHistoryByCustomerId(customerId);
        return ResponseHandler.responseBuilder(listHistory, HttpStatus.OK, null, null);
    }
}
