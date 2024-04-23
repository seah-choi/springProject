package org.fullstack4.chap01.utils;

public class ValidatorCheck {

    public static boolean check(String param) {
        boolean param_check = true;

        if (param == null || param.equals("") || param.isEmpty()) {
            param_check = false;
        }
        if (param.length() < 2 || param.length() > 10) {
            param_check = false;
        }

        return param_check;
    }

}
