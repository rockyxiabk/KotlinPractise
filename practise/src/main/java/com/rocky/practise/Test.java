package com.rocky.practise;

public class Test {
    private Test() {
    }

    private static class Holder {
        private static final Test INSTANCE = new Test();
    }

    public static Test getInstance() {
        return Holder.INSTANCE;
    }
}
