package com.dvgiang.di_ioc;

// Constructor injection
public class SmartPhone {
    private final Pin pin;
    public SmartPhone(Pin pin) {
        this.pin = pin;
    }

    public Pin getPin() {
        return pin;
    }
}

// Setter injection
//public class SmartPhone {
//    private Pin pin;
//
//    public void setPin(Pin pin) {
//        this.pin = pin;
//    }
//
//    public Pin getPin() {
//        return pin;
//    }
//}

// Interface injection
//public class SmartPhone implements DependencyInjection {
//    private Pin pin;
//
//    @Override
//    public void inject(Object o) {
//        this.pin = (Pin) o;
//    }
//
//    public Pin getPin() {
//        return pin;
//    }
//}
