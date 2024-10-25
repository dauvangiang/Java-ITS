package com.dvgiang.di_ioc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DiAndIoCApplication {

    public static void main(String[] args) {
        PinVN pinVN = new PinVN();
        SmartPhone phone = new SmartPhone(pinVN);
//        phone.setPin(pinVN);
//        phone.inject(pinVN);
        phone.getPin().start();
    }

}
