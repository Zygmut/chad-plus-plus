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
            warnings.add(new Warning(code, line, phase));
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
            warnings.add(new Warning(code, line, charloc, phase));
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
            warnings.add(new Warning(message, line, charloc, phase));
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
    public static ArrayList<Warning> geWarnings() {
        return warnings;
    }

}
