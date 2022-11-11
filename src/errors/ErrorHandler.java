package errors;

import java.util.ArrayList;

import utils.Env;
import utils.Phase;

/**
 * ErrorHandler - Clase para manejar los errores de compilacion
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */
public class ErrorHandler {

    private static ArrayList<Error> errors = new ArrayList<>();

    /*
     * Add an error to the list of errors
     *
     * @param error
     */
    public static void addError(Error error) {
        if (!Env.TEST_MODE) {
            errors.add(error);
        }
    }

    /*
     * Add an error to the list of errors
     *
     * @param int code
     *
     * @param int line
     *
     * @param int phase
     *
     */
    public static void addError(int code, int line, Phase phase) {
        if (!Env.TEST_MODE) {
            errors.add(new Error(code, line, phase));
        }
    }

    /*
     * Add an error to the list of errors
     *
     * @param int code
     *
     * @param int line
     *
     * @param int charloc
     * 
     * @param int phase
     *
     */
    public static void addError(int code, int line, int charloc, Phase phase) {
        if (!Env.TEST_MODE) {
            errors.add(new Error(code, line, charloc, phase));
        }
    }

    /*
     * Print all errors
     */
    public static void printErrors() {
        for (Error error : errors) {
            System.out.println(error);
        }
    }

    /*
     * Check if there are any errors
     *
     * @return boolean
     */
    public static boolean hasErrors() {
        return errors.size() > 0;
    }

    /*
     * Get the list of errors
     *
     * @return ArrayList<Error>
     */
    public static ArrayList<Error> getErrors() {
        return errors;
    }

}