package com.ming.controller;

import com.ming.service.FlowableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @version 1.0
 * @description: TODO
 * @date 2023/11/3 15:55
 * @Replenishment:
 */
@RestController
public class FlowableController {
    @Autowired
    private FlowableService flowableService;

    @GetMapping("/pic")
    public void showPic(HttpServletResponse resp, String processId) throws Exception {
        flowableService.showPic(resp,processId);
    }
}
