package com.ycy.jsbridge.jsbridge;

public enum JSCallbackType {
    SUCCESS(0), ERROR(1);

    private int value;

    JSCallbackType(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }
}