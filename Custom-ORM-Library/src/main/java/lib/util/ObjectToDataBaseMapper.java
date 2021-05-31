package lib.util;

import lib.annotations.MyColumn;
import lib.annotations.MyEntity;
import lib.annotations.MyId;
import lib.exceptions.ClassIsNotEntityException;
import lib.exceptions.NoIdDefinedException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class ObjectToDataBaseMapper {

    public static String[] getDBInfo() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("app.config"));
        String userName = properties.getProperty("app.user");
        String password = properties.getProperty("app.password");
        String databaseName = properties.getProperty("app.database");
        String databaseType = properties.getProperty("app.database_type");
        String serverHost = properties.getProperty("app.server_host");
        return new String[]{userName, password, databaseName, databaseType, serverHost};
    }

    public static String getConnectionURL() throws IOException {
        String[] dbInfo = getDBInfo();
        return "jdbc:" + dbInfo[3] + "://" + dbInfo[4] + "/"
                + dbInfo[2] + "?user=" + dbInfo[0] + "&password=" + dbInfo[1];
    }

    /**
     * This method is for checking the existence of MyEntity and MyId custom annotations in the class.
     * In case of non-existence of one of them, method throws custom exceptions:
     * ClassIsNotEntityException or NoIdDefinedException.
     *
     * @param myObject
     * @param <T>
     * @throws Exception
     */
    public static <T> void objectChecker(T myObject) throws ClassIsNotEntityException, NoIdDefinedException {
        int count = 0;
        if (!myObject.getClass().isAnnotationPresent(MyEntity.class)) {
            throw new ClassIsNotEntityException("This class is not MyEntity type class!");
        }
        Field[] fields = myObject.getClass().getDeclaredFields();
        for (int j = 0; j < fields.length; j++) {
            if (fields[j].isAnnotationPresent(MyId.class)) {
                count++;
            }
        }
        if (count == 0) {
            throw new NoIdDefinedException("Id field did not exist");
        }

    }


    /**
     * This method is for adding elements to database table (like 'persist' method in Hibernate).
     *
     * @param myObject
     * @param <T>
     * @throws Exception
     */
    public static <T> void myPersist(T myObject) throws Exception {
        objectChecker(myObject);
        String url = ObjectToDataBaseMapper.getConnectionURL();
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        List<String> objectFieldElements = new LinkedList<>();
        List<String> columnNames = new LinkedList<>();
        Class<?> objectClass = myObject.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            columnNames.add(field.getAnnotation(MyColumn.class).columnName());
            objectFieldElements.add(field.get(myObject).toString());
        }
        StringBuilder fieldElements = new StringBuilder("'" + objectFieldElements.get(0) + "'");
        for (String element : objectFieldElements.subList(1, objectFieldElements.size())) {
            fieldElements.append(", '" + element + "'");
        }

        String sql = "INSERT INTO "
                + myObject.getClass().getAnnotation(MyEntity.class).tableName()
                + " VALUES (" + fieldElements + ")";

        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            System.out.println("Duplicate id is not allowed!!!");
        } finally {
            statement.close();
            connection.close();
        }
    }

    /**
     * This method is for showing all of the data in the database table.
     *
     * @param myObject
     * @param <T>
     * @throws Exception
     */
    public static <T> void showAll(T myObject) throws Exception {
        objectChecker(myObject);
        String url = ObjectToDataBaseMapper.getConnectionURL();
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM "
                + myObject.getClass().getAnnotation(MyEntity.class).tableName();

        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            System.out.println("-".repeat(20));
            for (int i = 1; i <= myObject.getClass().getDeclaredFields().length; i++) {
                System.out.print(rs.getString(i) + " ".repeat(3));
            }
            System.out.println();
        }

        rs.close();
        statement.close();
        connection.close();
    }

    /**
     * This method is for removing elements from table based on id.
     *
     * @param myObject
     * @param id
     * @param <T>
     * @throws Exception
     */
    public static <T> void myRemove(T myObject, Long id) throws Exception {
        objectChecker(myObject);
        String url = ObjectToDataBaseMapper.getConnectionURL();
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        String sql = "DELETE FROM "
                + myObject.getClass().getAnnotation(MyEntity.class).tableName()
                + " WHERE id=" + id;

        try {
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {

        } finally {
            statement.close();
            connection.close();
        }

    }

    /**
     * This method is not completed yet.
     *
     * @param myObject
     * @param <T>
     * @throws Exception
     */
    public static <T> void createTable(T myObject) throws Exception {
        objectChecker(myObject);
        String url = ObjectToDataBaseMapper.getConnectionURL();
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();

        List<String> objectFieldElements = new LinkedList<>();
        List<String> columnNames = new LinkedList<>();
        Class<?> objectClass = myObject.getClass();

        for (Field field : objectClass.getDeclaredFields()) {
            columnNames.add(field.getAnnotation(MyColumn.class).columnName());
        }

//        String sql = "CREATE TABLE " + myObject.getClass().getAnnotation(MyEntity.class).tableName() +
//                "(id INTEGER not NULL, " +
//                " name VARCHAR(255), " +
//                " surname VARCHAR(255) )";
//
//        statement.executeUpdate(sql);
    }


}
