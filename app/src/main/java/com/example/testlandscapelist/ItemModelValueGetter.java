package com.example.testlandscapelist;

import java.lang.reflect.Field;

public class ItemModelValueGetter {
    private Field field;
    public String fieldName;

    public void SetFieldName(String fieldName) {
        this.fieldName = fieldName;
        try {
            field = ItemModel.class.getField(fieldName);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (field != null) {
            field.setAccessible(true);
        }
    }

    public String GetValue(ItemModel item) {
        if (field == null)
            return "No field";
        String value = "";
        try {
            value = field.get(item).toString();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return value;
    }
}
