package symbol_table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * Tabla de simbolos. Contiene todos los simbolos del programa. Se divide en dos
 * tablas:
 * <ul>
 * <li>Tabla de accesos</li>
 * <li>Tabla de simbolos</li>
 * </ul>
 * La tabla de accesos contiene los rangos de simbolos de cada función. La tabla
 * de simbolos contiene todos los simbolos del programa.
 *
 * Funcionamiento: La tabla de accesos se usa para saber en que función estamos
 * y en que rango de la tabla de simbolos se encuentran los simbolos, de dicha
 * función, ya que la tabla de simbolos contiene todos los simbolos del
 * programa. Por ejemplo, si tenemos una función con 3 variables, la tabla de
 * simbolos tendrá un rango de 4 simbolos (3 variables + 1 función) para dicha
 * función. Si tenemos 2 funciones, la tabla de accesos tendrá 4 rangos de
 * accesos, uno para las variables globales; uno para cada función y un último
 * para la funcion principal.
 *
 * @see Symbol
 * @see ta
 * @see ts
 * @see taIndex
 */
public class SymbolTable {

    /**
     * Tabla de accesos. Contiene los rangos de simbolos de cada función.
     * Formato: [inicio, fin] (int, int) donde inicio y fin son indices de la tabla
     * de simbolos.
     * Estructura:
     * <ul>
     * <li>Primero -> Variables globales</li>
     * <li>N -> Funciones</li>
     * <li>Último -> Main</li>
     * </ul>
     */
    private ArrayList<Integer[]> ta;
    /**
     * Tabla de simbolos. Contiene todos los simbolos del programa.
     */
    private ArrayList<Symbol> ts;
    /**
     * Indice de acceso actual. Se usa para saber en que funcion estamos añadiendo o
     * mirando simbolos.
     */
    private int taIndex;
    /**
     * Constante para indicar que no hay simbolos definidos para el acceso actual.
     */
    private final int NOT_AVALIABLE = -1;

    /**
     * Constructor de la tabla de simbolos. Inicializa las tablas y el indice de
     * acceso a -1.
     */
    public SymbolTable() {
        this.ta = new ArrayList<>();
        this.ts = new ArrayList<>();
        this.taIndex = -1;
    }

    /**
     * Añadir +1 al indice de acceso para poder identificar en que funcion estamos
     * añadiendo o mirando simbolos. Devuelve el indice actual una vez modificado
     *
     * @return int taIndex
     */
    public int incrementTaIndex() {
        return this.taIndex++;
    }

    /**
     * El access se crea con indices -1 en inicio y final para que quede
     * reflejado el caso que no se añadan nuevos simbolos. En caso que se añadan se
     * modificarán acordemente. Devuelve true si la inserción a sido correcta y
     * false en caso contrario.
     *
     * Automaticamente se añade +1 al indice de acceso
     *
     * @return boolean
     * @see incrementTaIndex()
     */
    public boolean addAccess() {
        Integer[] access = { this.NOT_AVALIABLE, this.NOT_AVALIABLE };
        // Añadir +1 al indice de acceso para poder identificar en que funcion estamos
        // añadiendo o mirando simbolos.
        this.taIndex++;
        return this.ta.add(access);
    }

    /**
     * Añade un simbolo a la tabla de simbolos. Retorna true si la inserción a sido
     * correcta y false en caso contrario.
     *
     * @param symbol simbolo a añadir
     * @return boolean
     */
    public boolean addSymbol(Symbol symbol) {
        // Si el simbolo a añadir es una funcion, hacemos un recorrido completo de la
        // tabla de simbolos
        if (symbol.getStructureType() == StructureType.FUNCTION) {
            for (Symbol symb : ts) {
                if (symb.equals(symbol)) {
                    return false;
                }
            }
        }
        // Mirar que el simbolo no este ya presente
        Integer[] range = this.ta.get(this.taIndex);
        if (range[0] != this.NOT_AVALIABLE) {
            for (int i = range[0]; i <= range[1]; i++) {
                if (this.ts.get(i).equals(symbol)) {
                    return false;
                }
            }
        } else {
            range = new Integer[] { this.ts.size(), this.ts.size() - 1 };
        }

        // Para asegurarnos que actualizamos por completo el rango de accesos,
        // eliminamos la instancia y creamos una nueva
        this.ta.remove(this.taIndex);
        this.ta.add(this.taIndex, new Integer[] { range[0], range[1] + 1 });

        this.ts.add(symbol);
        return true;
    }

    /**
     * Busca en el access pasado por parametro el id pasado por parametro.
     * Devuelve el simbolo cuyo id sea el mismo que el pasado por parametro si se ha
     * encontrado y null en caso contrario.
     *
     * @param access indice de acceso
     * @param id     nombre del simbolo
     * @return Symbol | null
     */
    public Symbol searchSymbolAtAccess(int access, String id) {
        // Mirar si tiene simbolos
        Integer[] range = this.ta.get(access);
        if (range[0] == this.NOT_AVALIABLE) {
            // No tiene simbolos
            return null;
        }

        // Buscar el simbolo el en access
        for (int i = range[0]; i <= range[1]; i++) {
            Symbol symbol = this.ts.get(i);
            if (symbol.getName().equals(id)) {
                return symbol;
            }
        }

        return null;
    }

    /**
     * Con respecto al acceso actual, busca en las definiciones de simbolos el
     * simbolo pasado por parametro. Devuelve el simbolo si se ha encontrado y null
     * en caso contrario.
     *
     * @param id nombre del simbolo
     * @return Symbol | null
     */
    public Symbol getSymbol(String id) {
        // Mirar si hay simbolos locales
        Symbol returnSymbol = this.searchSymbolAtAccess(this.taIndex, id);
        if (returnSymbol != null) {
            return returnSymbol;
        }

        // Mirar si hay simbolos globales
        return this.searchSymbolAtAccess(0, id);
    }

    /**
     * Busca en todos las funciones definidas en la tabla de accesos por una que
     * tenga el mismo nombre que el pasado por parametro. Esta funcion esta
     * parcialmente optimizada para que no se tenga que hacer un recorrido completo
     * de la tabla de simbolos y aprovechar la información que tenemos de las
     * funciones en la tabla de accesos para solo visitar los simbolos necesarios.
     * Devuelve el simbolo si se ha encontrado y null en caso contrario.
     *
     * @param id nombre del simbolo
     * @return Symbol | null
     */
    @SuppressWarnings("unchecked")
    public Symbol getFunction(String id) {
        ArrayList<Integer[]> functionAccess = (ArrayList<Integer[]>) ta.clone();
        functionAccess.remove(0);
        functionAccess.remove(functionAccess.size() - 1);

        // Mirar si hay funciones
        if (functionAccess.isEmpty()) {
            return null;
        }

        // Iterar en todas las funciones
        for (Integer[] access : functionAccess) {

            // Obtenemos el symbolo Type=FUNCTION
            if (!ts.get(access[1]).getName().equals(id)) {
                continue;
            }
            return ts.get(access[1]);
        }

        return null;
    }

    /**
     * Busca la funcion pasada por parametro de manera eficiente. Una vez encontrada
     * mediante indexación encuentra el parametro en concreto. En caso que el
     * parametro indexado este fuera de limite de la tabla de simbolos devolvera
     * null. Devuelve el simbolo indexado si es un parametro y null en caso
     * contrario.
     *
     * @param functionId nombre de la funcion
     * @param nParam     numero de parametro
     * @return Symbol | null
     * @see getFunction(String id)
     */
    @SuppressWarnings("unchecked")
    public Symbol getParameter(String functionId, int nParam) {
        ArrayList<Integer[]> functionAccess = (ArrayList<Integer[]>) ta.clone();
        functionAccess.remove(0);
        functionAccess.remove(functionAccess.size() - 1);

        // Mirar si hay funciones
        if (functionAccess.isEmpty()) {
            return null;
        }

        // Iterar en todas las funciones
        for (Integer[] access : functionAccess) {

            // Buscamos la función correspondiente
            if (!ts.get(access[1]).getName().equals(functionId)) {
                continue;
            }

            // Cogemos todos los parametros
            ArrayList<Symbol> params = new ArrayList<>();
            int index = access[0];
            for (Symbol parameter = ts.get(index); parameter.getStructureType()
                    .equals(StructureType.PARAMETER); parameter = ts.get(index++)) {
                params.add(parameter);
            }

            // Los parametros se guardan en orden inverso, por lo que invertimos el
            // arrayList de parametros
            Collections.reverse(params);

            // Tenemos todos los parametros guardados en params, miramos de acceder y si no
            // se puede retornamos null
            try {
                return params.get(nParam);
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Busca los parametros de la funcion pasada por parametro. Devuelve un stack
     * con los parametros si se ha encontrado y null en caso contrario.
     *
     * @param functionId nombre de la funcion
     * @return Stack<Symbol> | null
     */
    @SuppressWarnings("unchecked")
    public Stack<Symbol> getParameters(String functionId) {
        ArrayList<Integer[]> functionAccess = (ArrayList<Integer[]>) ta.clone();
        functionAccess.remove(0);
        functionAccess.remove(functionAccess.size() - 1);

        // Mirar si hay funciones
        if (functionAccess.isEmpty()) {
            return null;
        }

        // Iterar en todas las funciones
        for (Integer[] access : functionAccess) {

            // Buscamos la función correspondiente
            if (!ts.get(access[1]).getName().equals(functionId)) {
                continue;
            }

            // Cogemos todos los parametros
            Stack<Symbol> params = new Stack<>();
            int index = access[0];
            for (Symbol parameter = ts.get(index); parameter.getStructureType()
                    .equals(StructureType.PARAMETER); parameter = ts.get(++index)) {
                params.add(parameter);
            }

            return params;
        }

        return null;
    }

    /**
     * Devuelve el n-esimo argumento de la tupla pasada por parametro. En caso que
     * no exista el simbolo o el n-ésimo argumento devolvera null. En caso contrario
     * devolvera el simbolo.
     *
     * @param id   nombre de la tupla
     * @param nArg numero de argumento
     * @return Symbol | null
     * @see getSymbol(String id)
     */
    public Symbol getNTupleArgument(String id, int nArg) {
        // Buscamos el simbolo del id pasado por parametro
        Symbol nTuple = this.getSymbol(id);
        if (nTuple == null) {
            return null;
        }

        // Devolvemos el contenido del simbolo del nArg
        ArrayList<Symbol> content = nTuple.getContent();
        if (content == null) { // Si no tiene contenido devolvemos null
            return null;
        }

        try {
            return content.get(nArg);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Devuelve la tabla de simbolos.
     *
     * @return ArrayList<Symbol> tabla de simbolos
     */
    public ArrayList<Symbol> getTs() {
        return this.ts;
    }

    /**
     * Devuelve la tabla de accesos.
     *
     * @return ArrayList<Integer[]> tabla de accesos
     */
    public ArrayList<Integer[]> getTa() {
        return this.ta;
    }

    /**
     * Devuelve el indice de acceso actual.
     *
     * @return int indice de acceso actual
     */
    public int getTaIndex() {
        return this.taIndex;
    }

    /**
     * Devuelve una representación en String de la tabla de simbolos.
     *
     * @return String representación de la tabla de simbolos
     */
    @Override
    public String toString() {
        String returnstr = "TA: \n";

        for (Integer[] range : ta) {
            returnstr += range[0] + " " + range[1] + "\n";
        }

        returnstr += "TS: \n";
        for (Symbol s : ts) {
            returnstr += s.toString() + "\n";
        }
        return returnstr;
    }
}
