package tests;

import core.Chadpp;

/**
 * ChadppTests
 */
public class ChadppTests implements UnitTest {
    private static final Test<Object> unitTest = new Test<>();

    @Override
    public void runTests() {
        unitTest.run("Core Chadpp", new Runnable[] {
                ChadppTests::test1,
                ChadppTests::test2,
        });
    }

    private static void test1() {
    }

    private static void test2() {
    }

}