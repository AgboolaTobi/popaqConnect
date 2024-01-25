package org.popaqConnect.services.ServiceInterfaces;

import org.popaqConnect.data.models.Admin;
import org.popaqConnect.dtos.requests.AdminLoginRequest;
import org.popaqConnect.dtos.requests.AdminRequest;

import java.util.List;

public interface AdminService {

   Admin createAdmin(AdminRequest request);

   Admin viewAdmin(String id);

   List<Admin> viewAdmins();
   Admin updateAdmin(AdminRequest request);
   void deleteAdmin(String id);

   Admin loginAdmin(AdminLoginRequest request);
   void logoutAdmin(String id);


}
