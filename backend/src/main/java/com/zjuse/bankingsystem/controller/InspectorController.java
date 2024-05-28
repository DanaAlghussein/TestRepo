package com.zjuse.bankingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zjuse.bankingsystem.service.CreditCardService;
import com.zjuse.bankingsystem.utils.ApiResult;
import com.zjuse.bankingsystem.utils.RespResult;

@RestController
@RequestMapping("/credit-card/inspector")
public class InspectorController {
    @Autowired
    private CreditCardService creditCardService;

    @PostMapping("/login")
    public RespResult loginInspector(@RequestParam String name, @RequestParam String password) {
        ApiResult apiResult = creditCardService.loginInspector(name, password);
        if (apiResult.ok) {
            return RespResult.success(apiResult.payload);
        } else {
            return RespResult.fail("登录失败");
        }
    }

    @GetMapping("/request")
    public RespResult queryRequestsByInspector(@RequestParam Integer permission) {
        ApiResult apiResult = creditCardService.queryRequestsByInspector(permission);
        return RespResult.success(apiResult.payload);
    }

    @GetMapping("/request/accept")
    public RespResult acceptRequest(@RequestParam Long id) {
        ApiResult apiResult = creditCardService.acceptRequest(id);
        return RespResult.success(apiResult.payload);
    }

    @GetMapping("/request/reject")
    public RespResult rejectRequest(@RequestParam Integer id) {
        ApiResult apiResult = creditCardService.rejectRequest(id);
        return RespResult.success(apiResult.payload);
    }
}
