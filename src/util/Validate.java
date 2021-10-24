package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

/**
 * @author kazaf
 */
public class Validate {
    private static final Scanner sc = new Scanner(System.in);

    public static Integer getInt(String msg, String err, int min, int max, String ignore) {
        while (true) {
            try {
                System.out.print(msg);
                int num;
                String str = sc.nextLine();
                if (ignore != null && str != null && str.trim().equals(ignore)) {
                    return null;
                }
                num = Integer.parseInt(str);
                if (min <= num && num <= max) {
                    return num;
                } else {
                    System.out.println("Number inrange[" + min + "-" + max + "]");
                }
            } catch (NumberFormatException ex) {
                System.out.println(err);
            }
        }
    }

    public static Double getDouble(String msg, String err, double min, double max, String ignore) {
        while (true) {
            try {
                System.out.print(msg);
                double num;
                String str = sc.nextLine();
                if (ignore != null && str != null && str.trim().equals(ignore)) {
                    return null;
                }
                num = Double.parseDouble(str);
                if (min <= num && num <= max) {
                    return num;
                } else {
                    System.out.println("Number inrange[" + min + "-" + max + "]");
                }
            } catch (NumberFormatException ex) {
                System.out.println(err);
            }
        }
    }

    public static String getString(String msg, String err, String regex, String ignore) {
        while (true) {
            try {
                System.out.print(msg);
                String str = sc.nextLine();
                if (ignore != null && str != null && str.trim().equals(ignore)) {
                    return null;
                }
                if (str.matches(regex)) {
                    return str.trim();
                }
            } catch (Exception e) {
                System.out.println("Meet IOException");
            }
            System.out.println(err);
        }
    }

    public static String getDate(String msg, String err, String format, String ignore) { // formmat đầu vào cho ngày sinh
        Date date = null;
        LocalDate now = LocalDate.now(); // get time now
        SimpleDateFormat df = new SimpleDateFormat(format);
        df.setLenient(false);           // check day not exist       
        while (true) {
            try {
                System.out.print(msg);
                String str = sc.nextLine();
                if (ignore != null && str != null && str.trim().equals(ignore)) {
                    return null;
                }
                date = df.parse(str);
                String[] a = str.split("/"); // split time to int
                int day = Integer.parseInt(a[0]);
                int month = Integer.parseInt(a[1]);
                int year = Integer.parseInt(a[2]);
                if (now.getYear() - year < 18) {
                    System.out.println("Employees < 18 age,please input again");
                    continue;
                }
                if (now.getYear() - year == 18 && now.getMonthValue() < month) {
                    System.out.println("Employees  < 18 age,please input again");
                    continue;
                }
                if ((now.getYear() - year == 18) && (now.getMonthValue() == month)
                        && (now.getDayOfMonth() < day)) {
                    System.out.println("Employees  < 18 age,please input again");
                    continue;
                }
                break;
            } catch (NumberFormatException | ParseException e) {
                System.out.println(err);
            }
        }
        return df.format(date);
    }
}
