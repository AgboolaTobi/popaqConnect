package org.popaqConnect.services.ServiceInterfaces;

import org.popaqConnect.data.models.Admin;
import org.popaqConnect.dtos.requests.AdminLoginRequest;
import org.popaqConnect.dtos.requests.AdminRequest;

import java.util.List;

public interface AdminService {

   Admin createAdmin(AdminRequest request);
   Admin viewAdmin(Long id);
   List<Admin> viewAdmins();
   Admin updateAdmin(Long id);
   void deleteAdmin(Long id);

   Admin loginAdmin(AdminLoginRequest request);
   void logoutAdmin();


}
