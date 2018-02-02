package com.go.android.course.anntation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by go on 2018/2/1.
 */

public class TableCreator {
    public static void main(String[] args) throws Exception{

       com.TableCreator.main(args);
//
//        StringBuilder createCommand = new StringBuilder();
//
//        String className = "com.Member";
//        Class<?> cl = Class.forName(className);
//
//        DBTable dbTable = cl.getAnnotation(DBTable.class);
//
//        String tableName = dbTable.name();
//        //如果名字为空，就使用类名
//        if (tableName.length() < 1){
//            tableName = cl.getName().toUpperCase();
//        }
//
//        createCommand.append(
//                "CREATE TABLE " + tableName + "(");
//
//        List<String> columnDefs = new ArrayList<String>();
//        for (Field field : cl.getDeclaredFields()){
//            String columnName = null;
//            Annotation[] anns = field.getDeclaredAnnotations();
//            if (anns.length < 1){
//                continue;
//            }
//            //这里的写法之所以简单，因为我们每个Field上面最多只有一个 注解
//            if (anns[0] instanceof SQLInteger){
//                SQLInteger sInt = (SQLInteger)anns[0];
//                //没有名字的话，我们就使用Field的名字来做为 列名
//                if (sInt.name().length() < 1){
//                    columnName = field.getName();
//                }else {
//                    columnName = sInt.name();
//                }
//
//                columnDefs.add(columnName + " INT " + getConstraints(sInt.constraints()));
//            }
//
//            if (anns[0] instanceof SQLString){
//                SQLString sString = (SQLString)anns[0];
//                if (sString.name().length() < 1){
//                    columnName = field.getName();
//                }else {
//                    columnName = sString.name();
//                }
//
//                columnDefs.add(columnName + " VARCHAR(" + sString.value()
//                        + ")" + getConstraints(sString.constraints()));
//            }
//
//
//
//        }
//        for (String columnDef : columnDefs){
//            createCommand.append("\n\t" + columnDef + ",");
//        }
//
//        //移除最后的一个逗号
//        String tableCreate = createCommand.substring(0, createCommand.length()-1) + ");";
//
//        System.out.println("TABLE Creation SQL for " + className + "  is: \n" + tableCreate);
//
//    }
//
//    //解析出 Constraints 注解的内容
//    private static String getConstraints(Constraints con){
//        String constraints = "";
//        if (!con.allowNull()){
//            constraints += " Not Null";
//        }
//        if (con.primaryKey()){
//            constraints += " PRIMARY KEY ";
//        }
//
//        if (con.unique()){
//            constraints += " NNIQUE ";
//        }
//        return constraints;
    }
}
