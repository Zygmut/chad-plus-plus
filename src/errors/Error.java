package errors;

/**
 * Error
 */
public class Error {

    // Error message
    private String message;
    // Error code
    private int code;
    // Error line
    private int line;

    public Error(int code, int line) {
        this.code = code;
        this.line = line;
    }

    @Override
    public String toString() {
        setMessage(); // Set the message based on the code
        return "Error [code=" + code + ", line=" + line + ", message=" + message + "]";
    }

    /**
     * Set the message based on the code
     */
    private void setMessage() {
        switch (code) {
            case 1:
                message = "Error: Invalid character";
                break;
            case 2:
                message = "Error: Invalid number";
                break;
            case 3:
                message = "Error: Invalid identifier";
                break;
            case 4:
                message = "Error: Invalid operator";
                break;
            case 5:
                message = "Error: Invalid assignment";
                break;
            case 6:
                message = "Error: Invalid expression";
                break;
            case 7:
                message = "Error: Invalid statement";
                break;
            case 8:
                message = "Error: Invalid declaration";
                break;
            case 9:
                message = "Error: Invalid type";
                break;
            case 10:
                message = "Error: Invalid function";
                break;
            case 11:
                message = "Error: Invalid return";
                break;
            case 12:
                message = "Error: Invalid if";
                break;
            case 13:
                message = "Error: Invalid while";
                break;
            case 14:
                message = "Error: Invalid for";
                break;
            case 15:
                message = "Error: Invalid break";
                break;
            case 16:
                message = "Error: Invalid continue";
                break;
            case 17:
                message = "Error: Invalid print";
                break;
            case 18:
                message = "Error: Invalid read";
                break;
            case 19:
                message = "Error: Invalid call";
                break;
            case 20:
                message = "Error: Invalid parameter";
                break;
            case 21:
                message = "Error: Invalid argument";
                break;
            case 22:
                message = "Error: Invalid array";
                break;
            case 23:
                message = "Error: Invalid index";
                break;
            case 24:
                message = "Error: Invalid length";
                break;
            case 25:
                message = "Error: Invalid value";
                break;
            case 26:
                message = "Error: Invalid return type";
                break;
            case 27:
                message = "Error: Invalid parameter type";
                break;
            case 28:
                message = "Error: Invalid argument type";
                break;
            case 29:
                message = "Error: Invalid index type";
                break;
            case 30:
                message = "Error: Invalid value type";
                break;
        }
    }

}