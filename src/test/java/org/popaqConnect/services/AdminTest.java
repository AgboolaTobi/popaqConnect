package org.popaqConnect.services;

import org.junit.jupiter.api.Test;
import org.popaqConnect.data.models.Admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {
    @Test
    public void AdminRegistrationtest() {
        Admin admin = new Admin();
        admin.setFirstName("Sandra");
        admin.setLastName("Macaulay");
        admin.setEmail("sandra@mayaulay.com");
        admin.setPhone("07034467678");
        admin.setPassword("sandra003");
        assertEquals(admin.getEmail(), "sandra@mayaulay.com");

    }
    @Test
    public void AdminLoginTest(){
        Admin admin = new Admin();
        admin.setEmail("sandra@mayaulay.com");
        admin.setPassword("sandra003");
        assertEquals(admin.getEmail(), "sandra@mayaulay.com");
        assertEquals(admin.getPassword(), "sandra003");
    }

    @Test
    public void AdminLogoutTest(){
        Admin admin = new Admin();



    }
}
