package errors;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
            for (Error err : errors) {
                if (err.equals(error)) {
                    return;
                }
            }
            errors.add(error);
        }
    }

    /**
     * Add an error to the list of errors
     *
     * @param code
     * @param line
     * @param phase
     */
    public static void addError(ErrorCode code, int line, Phase phase) {
        if (!Env.TEST_MODE) {
            Error error = new Error(code, line, phase);
            for (Error err : errors) {
                if (err.equals(error)) {
                    return;
                }
            }
            errors.add(error);
        }
    }

    /**
     * Add an error to the list of errors
     *
     * @param code
     * @param line
     * @param charloc
     * @param phase
     */
    public static void addError(ErrorCode code, int line, int charloc, Phase phase) {
        if (!Env.TEST_MODE) {
            Error error = new Error(code, line, charloc, phase);
            for (Error err : errors) {
                if (err.equals(error)) {
                    return;
                }
            }
            errors.add(error);
        }
    }

    /**
     *
     * Add an error to the list of errors
     *
     * @param message
     * @param line
     * @param charloc
     * @param phase
     */
    public static void addError(String message, int line, int charloc, Phase phase) {
        if (!Env.TEST_MODE) {
            Error error = new Error(message, line, charloc, phase);
            for (Error err : errors) {
                if (err.equals(error)) {
                    return;
                }
            }
            errors.add(new Error(message, line, charloc, phase));
        }
    }

    /*
     * Print all errors
     */
    public static void printErrors() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(Env.GENERATED_FILES + "/" + "errores.txt"));
            for (Error error : errors) {
                System.out.println(error);
                writer.write(error.toString() + "\n");
            }
            writer.close();
        } catch (IOException err) {
            System.out.println(err);
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

    public static String getErrorsString() {
        String s = "";
        for (Error error : errors) {
            error.toggleColors();
            s += error.toString() + "\n";
            error.toggleColors();
        }
        return s;
    }

}