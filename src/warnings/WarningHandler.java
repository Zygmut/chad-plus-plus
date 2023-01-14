package warnings;

import java.util.ArrayList;

import utils.Env;
import utils.Phase;

/**
 * WarningHandler - Clase para manejar los warnings de compilacion
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */
public class WarningHandler {

    private static ArrayList<Warning> warnings = new ArrayList<>();

    /**
     * Add a warning to the list of warnings
     *
     * @param warning
     */
    public static void addWarning(Warning warning) {
        if (!Env.TEST_MODE) {
            for (Warning war : warnings) {
                if (war.equals(warning)) {
                    return;
                }
            }
            warnings.add(warning);
        }
    }

    /**
     * Add a warning to the list of warnings
     *
     * @param code
     * @param line
     * @param phase
     */
    public static void addWarning(WarningCode code, int line, Phase phase) {
        if (!Env.TEST_MODE) {
            Warning warning = new Warning(code, line, phase);
            for (Warning war : warnings) {
                if (war.equals(warning)) {
                    return;
                }
            }
            warnings.add(warning);
        }
    }

    /**
     * Add a warning to the list of warnings
     *
     * @param code
     * @param line
     * @param charloc
     * @param phase
     */
    public static void addWarning(WarningCode code, int line, int charloc, Phase phase) {
        if (!Env.TEST_MODE) {
            Warning warning = new Warning(code, line, charloc, phase);
            for (Warning war : warnings) {
                if (war.equals(warning)) {
                    return;
                }
            }

            warnings.add(warning);
        }
    }

    /**
     * Add a warning to the list of warnings
     *
     * @param code
     * @param line
     * @param charloc
     * @param phase
     */
    public static void addWarning(String message, int line, int charloc, Phase phase) {
        if (!Env.TEST_MODE) {
            Warning warning = new Warning(message, line, charloc, phase);
            for (Warning war : warnings) {
                if (warning.equals(war)) {
                    return;
                }
            }

            warnings.add(warning);
        }
    }

    /**
     * Print all warnings
     *
     */
    public static void printWarnings() {
        for (Warning warning : warnings) {
            System.out.println(warning);
        }
    }

    /**
     * Check if there are any warnings
     *
     * @return boolean
     */
    public static boolean hasWarnings() {
        return warnings.size() > 0;
    }

    /**
     * Get the list of warnings
     *
     * @return ArrayList<Warning>
     */
    public static ArrayList<Warning> getWarnings() {
        return warnings;
    }

    public static String getWarningString() {
        String s = "";
        for (Warning warning : warnings) {
            warning.toggleColors();
            s += warning.toString() + "\n";
            warning.toggleColors();
        }
        return s;
    }

}
