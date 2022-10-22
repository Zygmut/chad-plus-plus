package utils;

public class Sanity {

    /**
     * Comprueba los datos de entrada del programa. Los datos de entrada deben ser:
     * - El nombre del fichero a compilar (Obligatorio)
     * - El nombre del fichero de salida (Opcional)
     * - El path donde queremos que se genere el fichero de salida (Opcional)
     * 
     * @param args
     * @return int
     */
    public int checkInput(String[] args) {
        /*
         * Comprobar longitud de los argumentos y dependiendo de la longitud añadirlos a
         * Env.FILE_DATA, una vez añadidos llamar a checkFileData() para comprobar que
         * cumple los requisitos de nombre y extension (el path y el output path son
         * opcionales). Además, devolver el tipo de error que se ha producido u opción
         * obtenida
         */
        if (args.length == 0) {
            System.out.println(Env.welcomeString);
            System.exit(0);
        }

        return Env.Ok;
    }

}
