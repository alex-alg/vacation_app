package com.globalcorp.vacationapp.controllers;

import com.globalcorp.vacationapp.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    LeaveService leaveService;

    @GetMapping("list")
    public String allLeaveList(Model model){
        model.addAttribute("leaves", leaveService.getAll());
        return "admin/list";
    }
}
