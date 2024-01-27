package org.popaqConnect.utils;

public class Verification {

    public static boolean verifyPassword(String password){
        return password.matches("[A-Z][a-zA-Z]{2,}[0-9@!#$%^&():;.*_~`+{}]{1,9}");
    }
    public static boolean verifyEmail(String email){
        return email.matches("[a-zA-Z0-9!#$%^&():;.*_~`+{}]+@[a-z]+[.][a-z]{2,3}");
    }
    public static boolean verifyPhoneNumber(String phoneNumber){
        return phoneNumber.matches("[+234]{2,}[0-9]{7,}");
    }

    public static boolean verifyDate(String date){
        if(date.matches("[0-9]{1,2}/[1-9]{1,2}/[2-9][0-9]{3}")) {
            String[] array = date.split("/");
            if (Integer.parseInt(array[0]) <= 0 || Integer.parseInt(array[0]) > 31) {
                return false;
            }
            if (Integer.parseInt(array[1]) <= 0 || Integer.parseInt(array[1]) > 12) {
                return false;
            }
            return true;
        }
        return false;
    }

}
