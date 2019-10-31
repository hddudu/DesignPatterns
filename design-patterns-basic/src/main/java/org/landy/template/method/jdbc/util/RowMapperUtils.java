package org.landy.template.method.jdbc.util;

import org.landy.template.method.jdbc.RowMapper;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by hongdu on 2019/10/31.
 */
public class RowMapperUtils<T> implements RowMapper<T> {

    /**
     * 任意类： 作为反射 的基础调用参数
     */
    private Class<?> targetClass;

    /**
     * 字段map ： 缓存 字段名
     */
    private HashMap<String, Field> fieldHashMap;

    public RowMapperUtils(Class<?> targetClass) {
        this.targetClass = targetClass;
        fieldHashMap = new HashMap<>();
        //获取该类声明的所有字段
        Field[] fields = targetClass.getDeclaredFields();
        for (Field field : fields) {
            // 同时存入大小写，如果表中列名区分大小写且有列ID和列iD，则会出现异常。
            // 阿里开发公约，建议表名、字段名必须使用小写字母或数字；禁止出现数字开头，禁止两个下划线中间只出现数字。
            fieldHashMap.put(field.getName(), field);
            // fieldMap.put(getFieldNameUpper(field.getName()), field);
        }
    }

    @Override
    public T mapRow(ResultSet rs, int rowNum) throws Exception {
        T obj = null;
        try {
            obj = (T) targetClass.newInstance();
            final ResultSetMetaData metaData = rs.getMetaData();
            int columnLength = metaData.getColumnCount();
            String columnName = null;
            for (int i = 1; i <= columnLength; i++) {
                // 获取列名
                columnName = metaData.getColumnName(i);
                //获取字段类型
                Class fieldType = fieldHashMap.get(columnName).getType();
                //获取字段对象
                Field field = fieldHashMap.get(columnName);
                //设置字段可以访问
                field.setAccessible(true);
                // fieldClazz == Character.class || fieldClazz == char.class
                if (fieldType == int.class || fieldType == Integer.class) { // int
                    //往对象中设置 字段对象的 值 field: 字段对象
                    // ojb  实例对象
                    //rs.getInt(columnName) 获取结果集中的某个字段的值
                    field.set(obj, rs.getInt(columnName));
                } else if (fieldType == boolean.class || fieldType == Boolean.class) { // boolean
                    field.set(obj, rs.getBoolean(columnName));
                } else if (fieldType == String.class) { // string
                    field.set(obj, rs.getString(columnName));
                } else if (fieldType == float.class) { // float
                    field.set(obj, rs.getFloat(columnName));
                } else if (fieldType == double.class || fieldType == Double.class) { // double
                    field.set(obj, rs.getDouble(columnName));
                } else if (fieldType == BigDecimal.class) { // bigdecimal
                    field.set(obj, rs.getBigDecimal(columnName));
                } else if (fieldType == short.class || fieldType == Short.class) { // short
                    field.set(obj, rs.getShort(columnName));
                } else if (fieldType == Date.class) { // date
                    field.set(obj, rs.getDate(columnName));
                } else if (fieldType == Timestamp.class) { // timestamp
                    field.set(obj, rs.getTimestamp(columnName));
                } else if (fieldType == Long.class || fieldType == long.class) { // long
                    field.set(obj, rs.getLong(columnName));
                }
                field.setAccessible(false);
            }

        }catch(Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


    /**
     * 方法首字母大写.
     *
     * @param fieldName
     *            字段名.
     * @return 字段名首字母大写.
     */
    private String getFieldNameUpper(String fieldName) {
        char[] cs = fieldName.toCharArray();
        cs[0] -= 32; // 方法首字母大写
        return String.valueOf(cs);
    }
}
