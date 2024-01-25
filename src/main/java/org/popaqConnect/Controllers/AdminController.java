package org.popaqConnect.Controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.popaqConnect.data.models.Admin;
import org.popaqConnect.dtos.requests.AdminLoginRequest;
import org.popaqConnect.dtos.requests.AdminRequest;
import org.popaqConnect.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/admins")
    @RequiredArgsConstructor
    @AllArgsConstructor
    public class AdminController {

       @Autowired
        private AdminServiceImpl adminService;

        @PostMapping("/create")
        public Admin createAdmin(@RequestBody AdminRequest request) {
            return adminService.createAdmin(request);
        }

        @GetMapping("/view/{id}")
        public Admin viewAdmin(@PathVariable String id) {
            return adminService.viewAdmin(id);
        }

        @GetMapping("/viewAll")
        public List<Admin> viewAllAdmins() {
            return adminService.viewAdmins();
        }

        @PutMapping("/update")
        public Admin updateAdmin(@RequestBody AdminRequest request) {
            return adminService.updateAdmin(request);
        }

        @DeleteMapping("/delete/{id}")
        public void deleteAdmin(@PathVariable String id) {
            adminService.deleteAdmin(id);
        }

        @PostMapping("/login")
        public Admin loginAdmin(@RequestBody AdminLoginRequest request) {
            return adminService.loginAdmin(request);
        }

        @PostMapping("/logout/{adminId}")
        public void logoutAdmin(@PathVariable String adminId) {
            adminService.logoutAdmin(adminId);
        }


    }