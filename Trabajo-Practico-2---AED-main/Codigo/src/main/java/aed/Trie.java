package aed;

public class Trie<T>{
    private T _valor;
    private Trie<T>[] _hijos;
    private int _longitud;

    public Trie(){
        _valor = null;
        _hijos = new Trie[256];
        _longitud = 0;
        for(int i = 0; i < 257; i++){
            _hijos[i] = null;
        }
    }

    public boolean esta(String clave){
        char letra = clave.charAt(0); // ACA SACO OBTENGO LA PRIMERA LETRA DE LA PALABRA
        int letraNum = (int) letra; // CASTEO LA LETRA CON EL INT ASI OBTENGO SU NUMERO ASCII
        clave = clave.substring(1); // ACA DIGO QUE LA NUEVA PALABRA ES EL STRING SIN LA PRIMERA LETRA
        if(_hijos[letraNum] == null){ 
            return false; // SI NO EXISTE EL HIJO CON ESA LETRA, DEVOLVER FALSO
        }
        else{ // EXISTE EL HIJO CON ESA LETRA
            if(clave.length() == 0){
                return _hijos[letraNum]._valor != null; // SI NO HAY MÁS LETRAS, VERIFICA QUE TENGA VALOR EN EL TRIE 
            }
            else{
                return _hijos[letraNum].esta(clave); // SI HAY MÁS LETRAS, HACE RECURSIÓN SOBRE EL TRIE ARRANCANDO DESDE EL ULTIMO NODO
            }
        }
    }

    public void definir(String clave, T valor){
        /*if(_longitud < clave.length()){
            _longitud = clave.length(); //Modifica la longitud si el length de la nueva definicion es mayor a la longitud previa
        } */
        char letra = clave.charAt(0); // ACA SACO OBTENGO LA PRIMERA LETRA DE LA PALABRA
        int letraNum = (int) letra; // CASTEO LA LETRA CON EL INT ASI OBTENGO SU NUMERO ASCII
        clave = clave.substring(1);
        if(clave.length() != 0){
            if(_hijos[letraNum] == null){
                _hijos[letraNum] = new Trie<T>();
            }
            else{
                _hijos[letraNum].definir(clave, valor); // Recorre recursivamente el trie, definiendo uno nuevo si la letra no esta anteriormente
            }
        }
        else{  
            if(_hijos[letraNum]._valor != null){
                _hijos[letraNum]._valor = valor;
            }
            else{
                _hijos[letraNum] = new Trie<T>();
                _hijos[letraNum]._valor = valor;
                _longitud++;
            }
        }
    }

    public T obtener(String clave){  // REQUIERE QUE LA CLAVE ESTÉ DEFINIDA

        char letra = clave.charAt(0); // ACA SACO OBTENGO LA PRIMERA LETRA DE LA PALABRA
        int letraNum = (int) letra; // CASTEO LA LETRA CON EL INT ASI OBTENGO SU NUMERO ASCII
        clave = clave.substring(1); // ACA DIGO QUE LA NUEVA PALABRA ES EL STRING SIN LA PRIMERA LETRA
        if(clave.length() == 0){
            return _hijos[letraNum]._valor; // SI NO HAY MÁS LETRAS, VERIFICA QUE TENGA VALOR EN EL TRIE 
        }
        else{
            return _hijos[letraNum].obtener(clave); // SI HAY MÁS LETRAS, HACE RECURSIÓN SOBRE EL TRIE ARRANCANDO DESDE EL ULTIMO NODO
        }
    }
    private boolean tieneHijos(){ // SE FIJA SI UN NODO DEL TRIE TIENE AL MENOS UN HIJO DEFINIDO
        for(int i = 0; i < 257; i++){
            if(_hijos[i] != null){
                return true;
            }
        }
        return false;
    }
    private boolean tieneMasDeUnHijo(){ // SE FIJA SI UN NODO DEL TRIE TIENE MÁS DE UN HIJO DEFINIDO
        int suma = 0;
        for(int i = 0; i < 257; i++){
            if(_hijos[i] != null){
                suma++;
            }
        }
        return (suma > 1);
    }

    public void borrar(String clave){
        char letra = clave.charAt(0);
        int letraNum = (int) letra;
        clave = clave.substring(1);
        Trie<T> ultimo = null;
        Trie<T> actual = this;
        int ultimaLetra = 0; // numero random para que no tire error en el if
        while (clave.length() != 0){
            if (actual._valor != null || actual.tieneMasDeUnHijo()){ // SI EL NODO DONDE ESTAS PARADO TIENE VALOR O UNA BIFURCACIÓN, LO ALMACENA
                ultimo = actual;
                ultimaLetra = letraNum;
            }
            letra = clave.charAt(0);
            letraNum = (int) letra;
            clave = clave.substring(1);
            actual = actual._hijos[letraNum];
        }
        if (actual._hijos[letraNum].tieneHijos()){ 
            actual._hijos[letraNum]._valor = null;
            _longitud--;
        }
        else{
            if (ultimo != null){
                ultimo._hijos[ultimaLetra] = null;
                _longitud--;
            }
            else{
                actual._hijos[letraNum] = null;
                _longitud--;
            }
        }
    }

    public int tamaño(String clave, T valor){
        return _longitud;
    }

}