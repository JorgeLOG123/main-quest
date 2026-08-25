package arg.jorge.mainquest;

import Enums.EstadoMision;
import jakarta.persistence.*;

@Entity
@Table(name = "progreso_mision")
public class progresoDeMision extends Persistible {

    @ManyToOne
    @JoinColumn(name = "id_jugador")
    private Jugador jugador;

    @ManyToOne
    @JoinColumn(name = "id_mision")
    private Mision mision;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado")
    private EstadoMision estado;

    // Solo para hibernate
    protected progresoDeMision() { }

    public progresoDeMision(Jugador jugador, Mision mision) {
        this.jugador = jugador;
        this.mision = mision;
        this.estado = EstadoMision.EN_CURSO;
    }

    public void completar() {
        this.estado = EstadoMision.COMPLETADA;
    }

    public boolean estaCompleta() {
        return estado == EstadoMision.COMPLETADA;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Mision getMision() {
        return mision;
    }

    public EstadoMision getEstado() {
        return estado;
    }
}