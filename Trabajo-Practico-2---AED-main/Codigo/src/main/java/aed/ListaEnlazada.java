package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo _primer;
    private Nodo _ultimo;
    private int _size;

    private class Nodo {
        T valor;
        Nodo sig;
        Nodo ant;

        Nodo(T v){
            valor = v;
            sig = null;
            ant = null;
        }
    }

    public ListaEnlazada() {
        _primer = null;
        _ultimo = null;
        _size = 0;
    }

    public int longitud() {
        return _size;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if(_primer == null){
            _primer = nuevo;
            _ultimo = nuevo;
        }
        else{
            nuevo.sig = _primer;
            _primer.ant = nuevo;
            _primer = nuevo;
        }
        _size++;
    }

    public void agregarAtras(T elem){
        Nodo nuevo = new Nodo(elem);
        if(_ultimo == null){
            _primer = nuevo;
            _ultimo = nuevo;
        }
        else{
            Nodo actual = _ultimo;
            actual.sig = nuevo;
            nuevo.ant = actual;
            _ultimo = nuevo;
            }
        _size++;
        
    }

    public T obtener(int i) {
        Nodo actual = _primer;
        int j = 0;
        while (j != i){
            actual = actual.sig;
            j++;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = _primer;
        int j = 0;

        
        if(i != 0 && i < _size-1){
            while (j != i){
                actual = actual.sig;
                j++;
            }
            actual.sig.ant = actual.ant;
            actual.ant.sig = actual.sig;
        }
        else{
            if(_size == 1){
                _primer = null;
                _ultimo = null;
            }
            else{
                if (i == 0) {
                _primer = _primer.sig;
                _primer.ant = null;
                }
                if (i == _size-1){
                _ultimo = _ultimo.ant;
                _ultimo.sig = null; 
                }
            }
        }
        _size--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = _primer;
        int cont = 0;
        while(cont != indice){
            actual = actual.sig;
            cont++;
        }
        actual.valor = elem;
    }
    
    public ListaEnlazada<T> copiar() {
        Nodo actual = _primer;
        int cont = 0;
        ListaEnlazada<T> listaFinal = new ListaEnlazada();
        while(cont != _size){
            listaFinal.agregarAtras(actual.valor);
            actual = actual.sig;
            cont++;
        }
        listaFinal._size = this._size; 
        return listaFinal; 
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        ListaEnlazada<T> listaIntermedia = new ListaEnlazada();
        listaIntermedia = lista.copiar();
        this._primer = listaIntermedia._primer;
        this._ultimo = listaIntermedia._ultimo;
        this._size = listaIntermedia.longitud();
    }
    
    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append("[");
        int cont = 0;
        Nodo actual = _primer;
        while(cont != _size){
            sbuffer.append(actual.valor);
            if(cont != _size-1){
                sbuffer.append(", ");
            }
            actual = actual.sig;
            cont++;
        }
        sbuffer.append("]");
        
        return sbuffer.toString();
    } 
}
