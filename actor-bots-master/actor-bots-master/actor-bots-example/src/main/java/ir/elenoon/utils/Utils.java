package ir.elenoon.utils;

import im.actor.bots.jdf.JDF;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohammad on 6/3/16.
 */
public class Utils {

    private final static String tag = "Utils";

//    private static utils instance;
//
//    public static utils getInstance() {
//        if (instance == null)
//            instance = new utils();
//        return instance;
//    }

    public static void log(String tag, String msg) {
        System.out.println("################### " + new Date() + "::: " + tag + " ----> " + msg + " ###################");
    }


    public static String getStandardPhoneNumber(String phoneNumber) {

        if (phoneNumber != null) {
            String sample = "[PhoneContactRecord(989153523084)]";
            phoneNumber = phoneNumber.trim().substring(phoneNumber.indexOf("(") + 1, phoneNumber.lastIndexOf(")"));

            if (phoneNumber.matches("09\\d{9}"))
                return phoneNumber;
            if (phoneNumber.matches("989\\d{9}"))
                return "0" + phoneNumber.substring(2);
            if (phoneNumber.startsWith("+") && phoneNumber.substring(1).matches("989\\d{9}"))
                return "0" + phoneNumber.substring(3);
            return null;
        } else {
            return null;
        }
    }

    public static boolean isPhoneNumber(String str) {
        return str != null && !str.trim().isEmpty()
                && (str.matches("09\\d{9}") || str.matches("989\\d{9}") || str.startsWith("+") && str.substring(1).matches("989\\d{9}"));
    }

    public static boolean isRealPhoneNumber(String str) {
        return str != null
                && !str.trim().isEmpty()
                && (str.matches("090\\d{8}") || str.matches("091\\d{8}") || str.matches("092\\d{8}") || str.matches("093\\d{8}")
                || str.matches("9890\\d{8}") || str.matches("9891\\d{8}") || str.matches("9892\\d{8}") || str.matches("9893\\d{8}") || str
                .startsWith("+")
                && (str.substring(1).matches("9890\\d{8}") || str.substring(1).matches("9891\\d{8}")
                || str.substring(1).matches("9892\\d{8}") || str.substring(1).matches("9893\\d{8}")));
    }

    public static Date getDate() {
        return new Date();
    }

    public static String getIranianDate(Date date) {

        if (date != null) {

            try {
                DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
                String reportDate = df.format(date);
                String[] parts = reportDate.split("/");
                String part1 = parts[0];
                String part2 = parts[1];
                String part3 = parts[2];

                String[] partss = part3.split(" ");
                String part4 = partss[0];

                int day = Integer.parseInt(part2);
                int month = Integer.parseInt(part1);
                int year = Integer.parseInt(part4);

                JDF jdf = new JDF(year, month, day);
                // jdf.setGregorianDate(year, month, day);

                return jdf.getIranianDate();

            } catch (Exception e) {
                log(tag, e.getMessage());
                return "";
            }
        } else {
            log(tag, "Null numbers for getIranianDate() method");
            return "";
        }
    }
}
