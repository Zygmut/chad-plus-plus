package errors;

import java.util.ArrayList;

/**
 * ErrorHandler
 */
public class ErrorHandler {

    private ArrayList<Error> errors;

    public ErrorHandler() {
        this.errors = new ArrayList<Error>();
    }

    /*
     * Add an error to the list of errors
     *
     * @param error
     */
    public void addError(Error error) {
        this.errors.add(error);
    }

    /*
     * Add an error to the list of errors
     *
     * @param int code
     *
     * @param int line
     *
     */
    public void addError(int code, int line) {
        this.errors.add(new Error(code, line));
    }

    /*
     * Print all errors
     */
    public void printErrors() {
        for (Error error : this.errors) {
            System.out.println(error);
        }
    }

    /*
     * Check if there are any errors
     *
     * @return boolean
     */
    public boolean hasErrors() {
        return this.errors.size() > 0;
    }

    /*
     * Get the list of errors
     *
     * @return ArrayList<Error>
     */
    public ArrayList<Error> getErrors() {
        return this.errors;
    }

}