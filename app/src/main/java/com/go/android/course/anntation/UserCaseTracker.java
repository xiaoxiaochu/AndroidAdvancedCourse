package com.go.android.course.anntation;



import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.Method;
import java.util.ArrayList;

import java.util.Collections;
import java.util.List;

/**
 * Created by go on 2018/2/1.
 */

public class UserCaseTracker  {


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void main(String[] args) {
        List<Integer> userCases =  new ArrayList<>();
        Collections.addAll(userCases, 47,48, 49, 50);

        trackUseCases(userCases, PasswordUtils.class);

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void trackUseCases(List<Integer> useCases, Class<?> cl){
        for (Method m : cl.getDeclaredMethods()){
            UserCase uc = m.getDeclaredAnnotation(UserCase.class);
            if (uc != null){

                String name = m.getName();
                System.out.println("Found UseCase :" + uc.id() + "\t" + uc.description() +  "\t" + name);
                useCases.remove(new Integer(uc.id()));
            }
        }

        System.out.println("--------------------------------------");
        for (int i : useCases){
            System.out.println("Warning : Miss use case --" + i);
        }
    }
}
