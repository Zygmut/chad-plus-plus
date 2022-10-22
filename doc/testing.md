# Testing

## Test Class

La clase Test es una clase que permite realizar tests de forma sencilla. Para ello, se debe crear una instancia de Test y llamar al método `run()`. Concretamente, se debe llamar al método `customAssert()` que recibe dos parámetros:

- El objeto que se quiere comprobar
- El valor esperado

>**Note**: Los argumentos del método `customAssert()` deben ser tipos no primitivos a excepción de `boolean`.

Ejemplo:

```java
private final Test<Object> unitTest = new Test<>();

public void test() {
    String test = "test";
    unitTest.customAssert(test, "test");    // Resultado: OK

    Integer test2 = 56;
    unitTest.customAssert(test2, 1);        // Resultado: ERROR

    Boolean test3 = true;
    unitTest.customAssert(test3, true);     // Resultado: OK
}
```

## Creación de tests

### Explicación

Para generar tests de una unidad se debe crear una clase llamada `{Estructura de dato}Tests.java`, esta clase debe implementar la interfaz `UnitTest.java` y dentro de ella sobreescribir el método llamado `runTests()`. Dentro de este método se llamarán a los diferentes métodos en los que se hacen los unit test como tal. Específicamente, se llamará a `run({Nombre Test}, {[]Tests})`.

### Pasos a seguir

1. Crear una clase para agrupar los tests de un mismo tipo de estructura o tema. Esta debe implementar la interfaz `UnitTest`.
2. Crear una instancia de Test, i.e `private static final Test<Object> unitTest = new Test<>();`.
3. Sobreescribir el método `runTests()` de la interfaz `UnitTest`. En este método se deberá llamar a `unitTest.run({Nombre del test}, new Runnable[]{})` donde los contenidos del array son todos los tests definidos en la propia clase. Para añadirlos al array, se declaran tal que `{nombre de la clase}::{nombre del metodo}`
4. Crear metodos `test{num. test}()`.
5. Usar la función `customAssert(objeto, esperado)` para crear las aserciones.

### Ejemplo

```java
// StringTests.java
public class StringTests implements UnitTest{
   private static final Test<Object> unitTest = new Test<>();

    @Override
    public static void runTests() {
        unitTest.run("FileData", new Runnable[] {
                FileDataTests::test1,
                ...
        });
    }

    private static void test1() {
        String test = "test";
        unitTest.customAssert(test, "test");
    }
    
    ...
}
```
