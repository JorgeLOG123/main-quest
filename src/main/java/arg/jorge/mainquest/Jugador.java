package arg.jorge.mainquest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "JUGADORES")
public class Jugador extends Persistible {

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "email")
    private String email;

    @Column(name = "XP")
    private Long xp;

    public static final int XP_POR_NIVEL = 100;

    // Solo para hibernate
    protected Jugador() { }

    public Jugador(String nombre, String contrasena, String email) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.xp = 0L;
        this.email = email;
    }



    public Long getXp() {
        return xp;
    }




    public void setXp(Long xp) {
        this.xp = xp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void sumarXP(long cantidad) {
        this.xp += cantidad;
    }

    public int calcularNivel() {
        return (int) (this.xp / XP_POR_NIVEL) + 1;
    }
}