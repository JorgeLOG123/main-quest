package arg.jorge.mainquest.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "MISIONES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO")
public abstract class Mision extends Persistible {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "XP")
    private int XP;

    // Solo para hibernate
    protected Mision() { }

    public Mision(String nombre, int XP) {
        setNombre(nombre);
        setXP(XP);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (ValidationUtils.isEmpty(nombre)) {
            throw new IllegalArgumentException("El nombre de la misión es obligatorio");
        }
        this.nombre = nombre;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        if (XP <= 0) {
            throw new IllegalArgumentException("La misión debe otorgar XP positiva");
        }
        this.XP = XP;
    }

    /**
     * Calcula la XP final que otorga la misión.
     * Cada tipo de misión lo implementa a su manera.
     * @return la XP final de la misión
     */
    public abstract int calcularXpFinal();
}