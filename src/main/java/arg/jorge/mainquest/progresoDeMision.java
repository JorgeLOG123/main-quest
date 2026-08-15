package arg.jorge.mainquest;

import Enums.EstadoMision;

public class progresoDeMision {

    private Mision mision;
    private EstadoMision estado;


    public progresoDeMision(Mision mision){

        this.mision = mision;
        this.estado = EstadoMision.EN_CURSO;

    }

    public void completar(){
        this.estado = EstadoMision.COMPLETADA;
    }

    public boolean estaCompleta(){
       return estado == EstadoMision.COMPLETADA;

    }


    public Mision getMision() {
        return mision;
    }

    public EstadoMision getEstado() {
        return estado;
    }


}
