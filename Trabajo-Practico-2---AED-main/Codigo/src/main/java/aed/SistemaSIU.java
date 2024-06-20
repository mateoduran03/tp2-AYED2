package aed;

public class SistemaSIU {
    // Completar atributos privados
    private Trie<Trie<Materia>> sistema;

    private Trie<Integer> estudiantes;

    private class Materia{
        int[] plantelDoc;
        ListaEnlazada<String> listaEstudiantes;
        ListaEnlazada<parCarreraMateria<String, Trie<Materia>>> materiasHermanas;

        private Materia(){ //plantelDoc[0] es AY2 ; plantelDoc[1] es AY1 ; plantelDoc[2] es JTP ; plantelDoc[3] es PROF
            plantelDoc = new int[4];
            for (int i=0; i<4; i++){
                plantelDoc[i] = 0;
            }
            listaEstudiantes = new ListaEnlazada<>();
            materiasHermanas = new ListaEnlazada<>(); 
        }
    }
    private class parCarreraMateria<L,R>{ //Armo una clase que defina la tupla parCarreraMateria
        private L left;
        private R right;

        public parCarreraMateria(L materia, R carrera){
            this.left = materia;
            this.right = carrera;
        }

        public L materia(){return left;}

        public R carrera(){return right;}
    }

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }


    public SistemaSIU(InfoMateria[] materiasEnCarreras, String[] libretasUniversitarias){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public void inscribir(String estudiante, String carrera, String materia){
        Integer valor = estudiantes.obtener(estudiante) + 1;
        estudiantes.definir(materia, valor);    
        Materia newMateria = sistema.obtener(carrera).obtener(materia);
        newMateria.listaEstudiantes.agregarAtras(estudiante);
        sistema.obtener(carrera).definir(materia, newMateria);
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Materia nuevaMateria = sistema.obtener(carrera).obtener(materia); // O(|c| + |m|)
        int[] nuevoPlantel = sistema.obtener(carrera).obtener(materia).plantelDoc; // O(|c| + |m|)
        nuevoPlantel[cargo.ordinal()] = nuevoPlantel[cargo.ordinal()] + 1; // O(1)
        nuevaMateria.plantelDoc = nuevoPlantel; // O(1)
        sistema.obtener(carrera).definir(materia, nuevaMateria); // O(|c| + |m|)
    }

    public int[] plantelDocente(String materia, String carrera){
        int[] plantel = sistema.obtener(carrera).obtener(materia).plantelDoc;
        return plantel;
    }

    public void cerrarMateria(String materia, String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int inscriptos(String materia, String carrera){
        int inscript = sistema.obtener(carrera).obtener(materia).listaEstudiantes.longitud();
        return inscript;
    }

    private boolean calculo(int estudiantes, int profesor, int modulo){
        return (modulo < estudiantes/profesor);
    }
    public boolean excedeCupo(String materia, String carrera){
        Materia nuevaMateria = sistema.obtener(carrera).obtener(materia);
        int[] plantel = nuevaMateria.plantelDoc;
        int cantEstudiantes = nuevaMateria.listaEstudiantes.longitud();
        if(calculo(cantEstudiantes, plantel[0], 30) || calculo(cantEstudiantes, plantel[1], 20) || calculo(cantEstudiantes, plantel[2], 100) || calculo(cantEstudiantes, plantel[3], 250) ){
            return false;
        }
        else{
            return true;
        }
    }

    public String[] carreras(){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public String[] materias(String carrera){
        throw new UnsupportedOperationException("Método no implementado aún");
    }

    public int materiasInscriptas(String estudiante){
        return estudiantes.obtener(estudiante);
    }
}
