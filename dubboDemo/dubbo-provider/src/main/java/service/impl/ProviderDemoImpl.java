package service.impl;

import service.HelloDemo;

public class ProviderDemoImpl implements HelloDemo {
    public String sayHello(String word) {
        return "word值"+word;
    }
}
