package tests;

public class ErrorHandlingTests implements UnitTest {

    private static final Test<Object> unitTest = new Test<>();

    @Override
    public void runTests() {
        unitTest.run("Error Handling", new Runnable[] {
                ErrorHandlingTests::test1,
                ErrorHandlingTests::test2,
        });
    }

    private static void test1() {
    }

    private static void test2() {
    }
}
