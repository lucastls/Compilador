package Util;

public class Util {

    public static boolean isNumeric(String type) {

        if (type.equals("inteiro")) {
            return true;
        }

        return false;

    }

    public static String getNumericType(String type1, String type2) {
        if (type1.equals("real") || type2.equals("real")) {
            return "real";
        }
        return "inteiro";
    }

    public static boolean canAssign(String typee, String typed) {

        if (isNumeric(typee) && isNumeric(typed)) {

            if (typee.equals("inteiro") || typed.equals("inteiro")) {
                return true;
            }

        } else {

            if (typee.equals(typed)) {
                return true;
            }
        }

        return false;

    }

}