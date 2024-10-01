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

    //Tim kiem lich su theo ma KH va chu ky thanh toan dien
    @PostMapping("/search")
    public ResponseEntity<Object> getHistoryDetail(@Valid @RequestBody HistoryDTO historyDTO) {
        return ResponseHandler.responseBuilder(historyService.getHistoryDetail(historyDTO), HttpStatus.OK, null, null);
    }

    //Lây danh sách tất cả lịch sử theo mã khách hàng
    @GetMapping("/search/{customerId}")
    public List<History> getListHistoryByCustomerId(@PathVariable Long customerId) {
        return historyService.getListHistoryByCustomerId(customerId);
    }
}
