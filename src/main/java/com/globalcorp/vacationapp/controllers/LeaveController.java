package com.globalcorp.vacationapp.controllers;

import com.globalcorp.vacationapp.models.Leave;
import com.globalcorp.vacationapp.models.LeaveType;
import com.globalcorp.vacationapp.models.User;
import com.globalcorp.vacationapp.models.VacationAppUserDetails;
import com.globalcorp.vacationapp.repositories.LeaveRepository;
import com.globalcorp.vacationapp.repositories.LeaveTypeRepository;
import com.globalcorp.vacationapp.repositories.UserRepository;
import com.globalcorp.vacationapp.services.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("leave")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

    @Autowired
    private LeaveTypeRepository leaveTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("list")
    public String leaveList(Map<String, Object> model, Authentication authentication){
        VacationAppUserDetails userDetails = (VacationAppUserDetails) authentication.getPrincipal();
        List<Leave> userLeaves = leaveService.getLeavesByUserId(userDetails.getId());

        model.put("leaves", userLeaves);

        return "leave/list";
    }

    @GetMapping("create")
    public String create(@ModelAttribute Leave leave, Authentication authentication){
        return "leave/create";
    }

    @PostMapping("create")
    public String create(@Valid @ModelAttribute Leave leave, BindingResult result, Authentication authentication){
        if(result.hasErrors()) {
            return "leave/create";
        }

        VacationAppUserDetails userDetails = (VacationAppUserDetails) authentication.getPrincipal();
        User user = new User(userDetails.getId(), userDetails.getUsername());
        leave.setUser(user);
        leaveService.save(leave);

        return "redirect:create";
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("allLeaveTypes", leaveTypeRepository.findAll());
    }
}
