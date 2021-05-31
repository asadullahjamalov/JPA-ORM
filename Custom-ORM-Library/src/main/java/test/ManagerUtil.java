package test;

import lib.util.ObjectToDataBaseMapper;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class ManagerUtil {

    public static void addManager() {
        String name = checkString("manager name", true);
        String surname = checkString("manager surname", true);
        Long id = checkLong("manager id");

        Manager manager = Manager.builder().id(id).name(name).surname(surname).build();
        try {
            ObjectToDataBaseMapper.myPersist(manager);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Duplicate id is not allowed!!!");
        }
    }

    public static void showAllManagers() {
        Manager manager = new Manager();
        try {
            ObjectToDataBaseMapper.showAll(manager);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeManager() {
        showAllManagers();
        Manager manager = new Manager();
        System.out.println("Please enter manager id to remove manager: ");
        Long id;
        while (true) {
            String data = new Scanner(System.in).next();
            if (data.matches("([0-9]+)")) {
                id = Long.parseLong(data);
                break;
            } else {
                System.out.println("Please enter corresponding id");
            }
        }

        try {
            ObjectToDataBaseMapper.myRemove(manager, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String checkString(String info, boolean successMessage) {
        System.out.println("Enter the " + info);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String data = scanner.nextLine();
            if (data.matches("[A-Za-z]+")) {
                if (successMessage) {
                    System.out.println(info + " was added");
                }
                return data;
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

}
