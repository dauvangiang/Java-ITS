package com.dvgiang.electricitybillingsystem.controller;

import com.dvgiang.electricitybillingsystem.dto.HistoryRequest;
import com.dvgiang.electricitybillingsystem.model.History;
import com.dvgiang.electricitybillingsystem.service.HistoryService;
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
    public History getHistoryDetail(@RequestBody HistoryRequest historyRequest) {
        return historyService.getHistoryDetail(historyRequest);
    }

    @GetMapping("/search/{customerId}")
    public List<History> getListHistoryByCustomerId(@PathVariable Long customerId) {
        return historyService.getListHistoryByCustomerId(customerId);
    }
}
