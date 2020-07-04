package com.example.demo.util;

import java.util.HashSet;
import java.util.Set;

public class SetUtil {
    public static Set<String> convertStringToSet(String s){
        String[] strings = s.split(",");
        Set<String> stringSet = new HashSet<>();
        for (String string : strings) {
            if (!string.isEmpty()) {
                stringSet.add(string);
            }
        }
        return stringSet;
    }

    public static String convertSetToString(Set<String> stringSet){
        StringBuilder sb = new StringBuilder();
        for (String string : stringSet){
            if (!string.isEmpty()){
                sb.append(string).append(",");
            }
        }
        return sb.toString();
    }
}
