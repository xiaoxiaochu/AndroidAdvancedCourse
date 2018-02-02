package com.go.android.course.anntation;

/**
 * Created by go on 2018/2/1.
 */

@DBTable(name = "MEMBER")
public class Member {


    @SQLString(30)
    String firstName;


    @SQLInteger
    Integer age;

    @SQLString(value = 30,
            constraints = @Constraints(primaryKey = true))
    String handle;

    static int memberCount;
}
