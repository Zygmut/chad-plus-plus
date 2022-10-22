package utils;

/**
 * Clase encargada de checkear que un elemento cumpla con las restricciones
 * impuestas
 *
 * Assignatura 21742 - Compiladors
 * Estudis: Grau en Informàtica
 * Itinerari: Computació
 * Curs: 2022 - 2023
 */
public class Sanity {

    /**
     * Comprueba los datos de entrada del programa. Los datos de entrada deben ser:
     * Ejemplo del input completo seria: chadpp test.chpp target/output
     * Esto deberia compilar test.chpp a target/ a un fichero llamado output
     *
     * - El nombre del fichero a compilar (Obligatorio)
     * - El path (y nombre) donde queremos que se genere el fichero de salida
     * (Opcional)
     *
     * @param args
     * @return int
     */
    public static int checkInput(String[] args) {

        if (args.length == 0) {
            System.out.println(Env.welcomeString);
            System.exit(0);
        }

        // Comprobar que es un fichero con extension .chpp o .txt
        if (!(args[0].contains(".") && !(args[0].endsWith(".")))) {
            return Env.Error;
        }

        switch (args.length) {
            case 1:
                Env.FILE_DATA.setMultipleFileData(args[0].split("[.]")[0], args[0].split("[.]")[1], null);
                break;
            case 2:
                Env.FILE_DATA.setMultipleFileData(args[0].split("[.]")[0], args[0].split("[.]")[1], args[1]);
                break;
        }

        if (Env.DEBUG_MODE) {
            System.out.println(Env.FILE_DATA);
        }

        return Env.FILE_DATA.checkFileData() ? Env.Ok : Env.Error;
    }

}
