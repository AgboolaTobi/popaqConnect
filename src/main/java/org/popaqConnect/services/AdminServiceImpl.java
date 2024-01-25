package org.popaqConnect.services;

import org.popaqConnect.data.models.Admin;
import org.popaqConnect.data.repositories.AdminRepository;
import org.popaqConnect.dtos.requests.AdminLoginRequest;
import org.popaqConnect.dtos.requests.AdminRequest;
import org.popaqConnect.exceptions.AdminAlreadyExists;
import org.popaqConnect.exceptions.AdminNotFoundException;
import org.popaqConnect.exceptions.InvalidDetailsException;
import org.popaqConnect.exceptions.InvalidLoginException;
import org.popaqConnect.services.ServiceInterfaces.AdminService;
import org.popaqConnect.utils.VerifyPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @Override
    public Admin createAdmin(AdminRequest adminRequest) {
        if(userExist(adminRequest.getEmail())){
            throw new AdminAlreadyExists("Admin already exists");
        }
        Admin admin = new Admin();

        admin.setFirstName(adminRequest.getFirstName());
        admin.setLastName(adminRequest.getLastName());
        admin.setEmail(adminRequest.getEmail());
        admin.setPhone(adminRequest.getPhone());
        admin.setPassword( passwordEncoder.encode(adminRequest.getPassword()));

        if(!VerifyPassword.verifyPassword(adminRequest.getPassword()))throw new InvalidDetailsException("weak password format");

        return adminRepository.save(admin);
    }

    private boolean userExist(String email) {
        Admin admin = adminRepository.findByEmail(email);
        return admin != null;
    }

    @Override
    public Admin viewAdmin(String id) {
        return adminRepository.findById(id).orElseThrow(()-> new AdminNotFoundException("Admin not found!"));
    }

    @Override
    public List<Admin> viewAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin updateAdmin(AdminRequest request ) {
        Admin admin = new Admin();
        admin.setId(request.getId());
        admin.setFirstName(request.getFirstName());
        admin.setLastName(request.getLastName());
        admin.setEmail(request.getEmail());
        admin.setPhone(request.getPhone());
        if(!VerifyPassword.verifyPassword(request.getPassword()))throw new InvalidDetailsException("weak password format");
        return adminRepository.save(admin);
    }
    @Override
    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }


    @Override
    public Admin loginAdmin(AdminLoginRequest request) {
        Admin admin = adminRepository.findByEmail(request.getEmail());

        if (admin == null) throw new AdminNotFoundException("Admin not found");

        if (passwordEncoder.matches(request.getPassword(), admin.getPassword())) {
            admin.setLoggedIn(true);
            adminRepository.save(admin);
            return admin;
        } else {
            throw new InvalidLoginException("Invalid credentials");
        }
    }


    @Override
    public void logoutAdmin(String id) {
        Admin admin =  viewAdmin(id);
        admin.setLoggedIn(false);
        adminRepository.save(admin);
    }
}


