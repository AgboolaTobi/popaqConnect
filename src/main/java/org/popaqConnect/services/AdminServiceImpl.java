package org.popaqConnect.services;

import org.popaqConnect.data.models.Admin;
import org.popaqConnect.data.repositories.AdminRespository;
import org.popaqConnect.dtos.requests.AdminLoginRequest;
import org.popaqConnect.dtos.requests.AdminRequest;
import org.popaqConnect.services.ServiceInterfaces.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRespository adminRespository;



    /**
     * @param request
     * @return
     */
    @Override
    public Admin createAdmin(AdminRequest request) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Admin viewAdmin(Long id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Admin> viewAdmins() {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Admin updateAdmin(Long id) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteAdmin(Long id) {

    }

    /**
     * @param request
     * @return
     */
    @Override
    public Admin loginAdmin(AdminLoginRequest request) {
        return null;
    }

    /**
     *
     */
    @Override
    public void logoutAdmin() {

    }
}
