package edu.school21.reflection.orm;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;
import java.util.Locale;

public class OrmManager {

    private DataSource ds;
//    private OrmEntity ormEntity;
//    private Class userClass;
    private StringBuilder createCommand;
    private String columnDef = "";


    public OrmManager(DataSource ds) {
        this.ds = ds;
        init();
    }

    public void init() {

        OrmEntity ormEntity = null;
        Class userClass = null;
        try {
            userClass = Class.forName("edu.school21.reflection.entity.User");
            ormEntity = (OrmEntity) userClass.getAnnotation(OrmEntity.class);
            if (ormEntity == null) {
                System.err.println("No OrmEntity annotations for class " + userClass.getName());
                System.exit(-1);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        String tableName = ormEntity.table();
        if (tableName.length() < 1) {
            System.err.println("Table has empty name");
            System.exit(-1);

        }
        createCommand = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + " (");
        for (Field field : userClass.getDeclaredFields()) {
            String columnName = null;
            Annotation[] annotations = field.getDeclaredAnnotations();
            if (annotations.length < 1) {
                System.err.println("Field " + field.getName() + " is not database column");
                System.exit(-1);
            }
            if (annotations[0] instanceof OrmColumn) {
                OrmColumn ormColumn = (OrmColumn) annotations[0];
                columnName = ormColumn.name();
                if (field.getType().getSimpleName().equals("Integer")) {
                    columnDef = columnName + " INTEGER ";
                } else {
                    columnDef = columnName + " VARCHAR( " + columnName.length() + " ) ";
                }
            } else if (annotations[0] instanceof OrmColumnId) {
                OrmColumnId ormColumnId = (OrmColumnId) annotations[0];
                columnName = ormColumnId.name();
                columnDef = columnName + " SERIAL PRIMARY KEY ";
            }
            createCommand.append("\n    ").append(columnDef).append(", ");
        }
        String tableCreate =  "DROP TABLE IF EXISTS "+ tableName + ";\n" + createCommand.substring(0, createCommand.length() - 2) + ");";
        System.out.println(tableCreate);
        createTable(tableCreate);
    }

    private void createTable(String sql) {
        try (Connection con = ds.getConnection()){
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void save(Object entity) {

        Class clazz = entity.getClass();
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity entity1 = (OrmEntity) clazz.getAnnotation(OrmEntity.class);
            String tableName = entity1.table();

            StringBuilder stringBuilder = new StringBuilder("INSERT INTO " + tableName + "\n" +
                    "() VALUES ()");

            String columnName = null;
            OrmColumn ormColumn = null;
            for (Field field : clazz.getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                field.setAccessible(true);
                if (annotations[0] instanceof OrmColumn) {
                    ormColumn = (OrmColumn) annotations[0];
                    columnName = ormColumn.name();
                    stringBuilder.insert(stringBuilder.indexOf(")"), columnName + ",");
                    try {
                        String value = field.get(entity).toString();

                        if (field.getType().getName().contains("String")) {
                            value = "'" + value + "'";
                        }
                        stringBuilder.insert(stringBuilder.lastIndexOf(")"), value + ",");

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
            String sql = stringBuilder.deleteCharAt(stringBuilder.indexOf(")") - 1)
                    .deleteCharAt(stringBuilder.lastIndexOf(",")).toString();
            System.out.println(sql);
            createTable(sql);
        }

    }

    public void update(Object entity) {

        Class clazz = entity.getClass();
        if (clazz.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity entity1 = (OrmEntity) clazz.getAnnotation(OrmEntity.class);
            String tableName = entity1.table();

            StringBuilder stBuilder = new StringBuilder("UPDATE " + tableName + " SET ");

            String columnName = null;
            OrmColumn ormColumn = null;
            Field idField = null;
            for (Field field : clazz.getDeclaredFields()) {
                Annotation[] annotations = field.getDeclaredAnnotations();
                field.setAccessible(true);
                if (annotations[0] instanceof OrmColumn) {
                    ormColumn = (OrmColumn) annotations[0];
                    columnName = ormColumn.name();
                    stBuilder.append(columnName).append(" = ");
                    try {
                        String value;

                        if (field.get(entity) == null) {
                            value = null;
                        } else {
                            value = field.get(entity).toString();
                        }
                        if (field.getType().getName().contains("String")) {
                            value = "'" + value + "'";
                        }
                        stBuilder.append(value).append(", ");

                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        return;
                    }
                } else if (annotations[0] instanceof  OrmColumnId) {
                    idField = field;
                }
            }
            stBuilder.deleteCharAt(stBuilder.lastIndexOf(",")).append(" WHERE id = ");
            try {
                String sql = stBuilder + idField.get(entity).toString() + ";";
                createTable(sql);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return;
            }

        }
    }

    public <T> T findById(Long id, Class<T> aClass) {
        if (aClass.isAnnotationPresent(OrmEntity.class)) {
            OrmEntity ormEntity = aClass.getAnnotation(OrmEntity.class);
            String query = "SELECT * FROM " +
                    ormEntity.table() + " WHERE id = " + id;


            try (Connection con = ds.getConnection();
                 Statement st = con.createStatement()) {
                ResultSet rs = st.executeQuery(query);
                T obj = aClass.newInstance();
                Field[] fields = aClass.getDeclaredFields();

                if (!rs.next()) {
                    throw new SQLException("User with id: " + id + " NOT FOUND");
                }

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(OrmColumnId.class)) {
                        field.set(obj, rs.getLong("id"));
                    } else {

                        OrmColumn column = field.getAnnotation(OrmColumn.class);
                        Object value = rs.getObject(column.name());
                        field.set(obj, value);
                    }

                }

                return obj;
            } catch (IllegalAccessException | InstantiationException | SQLException e) {
                System.out.println(e.getMessage());
            }

        }
        return null;
    }
    
}

