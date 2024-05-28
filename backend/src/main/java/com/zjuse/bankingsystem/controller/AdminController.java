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
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CreditCardService creditCardService;


    @PostMapping("/creditCard/admin/login/log")
    public RespResult loginAdmin(@RequestParam String name, @RequestParam String password) {
        System.out.println("loginAdmin where name = " + name + " and password = " + password);
        ApiResult apiResult = creditCardService.loginAdmin(name, password);
        if (apiResult.ok) {
            return RespResult.success(null);
        } else {
            return RespResult.fail("登录失败");
        }
    }

    @GetMapping("/creditCard/admin/inspector")
    public RespResult queryInspectors() {
        ApiResult apiResult = creditCardService.queryInspectors();
        return RespResult.success(apiResult.payload);
    }

    @PostMapping("/creditCard/admin/inspector/modify")
    public RespResult modifyInspectorPassword(@RequestParam Integer id, @RequestParam String password) {
        ApiResult apiResult = creditCardService.modifyInspectorPassword(id, password);
        return RespResult.success(null);
    }

    @PostMapping("/creditCard/admin/inspector/update")
    public RespResult modifyInspectorLevel(@RequestParam Integer id, @RequestParam Integer permission) {
        creditCardService.modifyInspectorLevel(id, permission);
        return RespResult.success(null);
    }

    @GetMapping("/creditCard/admin/inspector/delete")
    public RespResult deleteInspector(@RequestParam Integer id) {
        creditCardService.deleteInspector(id);
        return RespResult.success(null);
    }

    @PostMapping("/creditCard/admin/inspector/add")
    public RespResult addNewInspector(@RequestParam String name, @RequestParam String password, @RequestParam Integer permission) {
        creditCardService.addNewInspector(name, password, permission);
        return RespResult.success(null);
    }
}
