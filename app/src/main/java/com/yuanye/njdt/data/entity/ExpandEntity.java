package com.yuanye.njdt.data.entity;

/**
 * Created by llay on 2017/9/12.
 */

public class ExpandEntity {

    private String key;

    private String value;

    public ExpandEntity(String _key, String _value) {
        key = _key;
        value = _value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String _key) {
        key = _key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String _value) {
        value = _value;
    }
}
