package lib.util;

import lib.annotations.MyColumn;
import lib.annotations.MyEntity;
import lib.exceptions.ClassIsNotEntityException;
import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class DataBaseMapperUtil {
    private List<DataBaseColumn> dbColumns;
    private String tableName;

    static class DataBaseColumn {
        private String name;
        private String fieldName;

        DataBaseColumn(String name, String fieldName) {
            this.name = name;
            this.fieldName = fieldName;

        }
    }

    DataBaseMapperUtil(Class<?> clazz) throws Exception {
        this.dbColumns = getColumnNames(clazz);
        this.tableName = getTableName(clazz);
    }

    private String getTableName(Class clazz) throws ClassIsNotEntityException {
        MyEntity annotation = (MyEntity) clazz.getAnnotation(MyEntity.class);
        if (annotation != null) {
            return annotation.tableName();
        }
        throw new ClassIsNotEntityException("This class is not MyEntity type class!");
    }

    static Object getFieldValue(Object object, String fieldName) {
        Class<?> clazz = object.getClass();
        try {
            Field name = FieldUtils.getField(clazz, fieldName, true);
            return FieldUtils.readField(name, object, true);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<DataBaseColumn> getColumnNames(Class<?> clazz) throws Exception {
        List<DataBaseColumn> columnNames = new ArrayList<>();
        List<Field> fields = FieldUtils.getFieldsListWithAnnotation(clazz, MyColumn.class);
        if (fields.size() == 0) {

        }
        for (Field field : fields) {
            MyColumn annotation = field.getAnnotation(MyColumn.class);
            columnNames.add(new DataBaseColumn(annotation.columnName(), field.getName()));
        }
        return columnNames;
    }

}


