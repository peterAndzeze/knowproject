package com.sw.project.teamshrio.common;

public enum TrueFalseEnum {
    TRUE("是", "1"),
    FALSE("否", "0");

    private String label;
    private String value;

    private TrueFalseEnum(String label, String value) {
        this.label = label;
        this.value = value;
    }

    public String getLabel() {
        return this.label;
    }

    public String getValue() {
        return this.value;
    }
}
