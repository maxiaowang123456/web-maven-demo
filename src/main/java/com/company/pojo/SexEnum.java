package com.company.pojo;

public enum SexEnum {
    MALE(1,"男"),FEMALE(0,"女");
    private int id;
    private String name;

    SexEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public  static SexEnum getSexEnumByNum(int id) {
        for (SexEnum sex : SexEnum.values()) {
            if (sex.id == id) {
                return sex;
            }
        }
        return null;
    }

    public static String getNameByNum(int id){
        return getSexEnumByNum(id).name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
