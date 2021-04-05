package com.nikita.kut.android.simbirsoft_workshop;

public class Lambda {

    MyClosure myClosure = (String x) -> System.out.println(x);

    public void repeatTask(int times, MyClosure task) {
        for (int i = 0; i <= times; i++) {
            task.print("I love Java");
        }

    }

    public interface MyClosure {
        void print(String str);
    }
}
