package util;

import base.Person;

import java.util.Scanner;

public class GeneralUtil {

    public static String checkString(String info, boolean successMessage) {
        System.out.println("Enter the " + info);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.matches("[A-Za-z0-9_\\s]+")) {
                if (successMessage) {
                    System.out.println(info + " was added");
                }
                return data;
            } else {
                System.out.println("Please enter " + info + " in correct format");
            }
        }
    }

    public static Integer checkInteger(String info, boolean successMessage) {
        System.out.println("Enter the " + info);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.matches("[0-9]+")) {
                if (successMessage) {
                    System.out.println(info + " was added");
                }
                return Integer.parseInt(data);
            } else {
                System.out.println("Please enter " + info + " in correct format");
            }
        }
    }

    public static long checkLong(String info) {
        System.out.println("Enter the " + info);
        while (true) {
            String data = new Scanner(System.in).next();
            if (data.matches("([0-9]+)")) {
                return Long.parseLong(data);
            } else {
                System.out.println("Please enter corresponding " + info);
            }
        }
    }

    public static String checkEmail() {
        System.out.println("Enter the email");
        OUT:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.matches("[A-Za-z[.]_0-9]+[@][A-Za-z[.]_0-9]+[.][a-z]{2,4}")) {
                System.out.println("Email was added");
                return data;
            } else {
                System.out.println("Please enter email in correct format");
            }
        }
    }

    public static String checkPhoneNumber() {
        System.out.println("Enter the phone number (+YYY-YY-YYY-YY-YY)");
        OUT:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.matches("[+][0-9]{3}[-][0-9]{2}[-][0-9]{3}[-][0-9]{2}[-][0-9]{2}")) {
                System.out.println("Phone number was added");
                return data;
            } else {
                System.out.println("Please enter email in correct format (+YYY-YY-YYY-YY-YY)");
            }
        }
    }

    public static boolean changeHelper(String info) {
        System.out.println("Do you want to change " + info + "? (Y/N)");
        OUT:
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.equalsIgnoreCase("yes") || data.equalsIgnoreCase("y")) {
                return true;
            } else if (data.equalsIgnoreCase("no") || data.equalsIgnoreCase("n")) {
                return false;
            } else {
                System.out.println("Please, enter corresponding answer (Y/N)");
                continue OUT;
            }
        }
    }


}
