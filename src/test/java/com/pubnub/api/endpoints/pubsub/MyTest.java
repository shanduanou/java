package com.pubnub.api.endpoints.pubsub;

import org.junit.Test;

public class MyTest {


    @Test
    public void name() {
        System.out.println("go");

        new Person("Ted", "Who", "dfa", 11).printPerson();
        new Person("Ted", "Who", "dfa").printPerson();
        new Person("Ted", "Who").printPerson();

    }
}
