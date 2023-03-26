package com.example.menuservice.entity;

import lombok.Getter;

@Getter
public enum Days {
    MONDAY("Mon"),
    TUESDAY("Tue"),
    WEDNESDAY("Wed"),
    THURSDAY("Thu"),
    FRIDAY("Fri"),
    SATURDAY("Sat"),
    SUNDAY("San");

    private String code;

    Days(String code){
        this.code = code;
    }

    public static Days getByCode(String code){
        for (Days days : Days.values()){
            if (days.code.equals(code)){
                return days;
            }
        }
        throw new IllegalArgumentException("No enum constant " + Days.class.getName() + " with code " + code);
    }
}
