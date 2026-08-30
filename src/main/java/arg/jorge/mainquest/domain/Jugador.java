package arg.jorge.mainquest.domain;
import arg.jorge.mainquest.domain.ValidationUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "JUGADORES")
public class Jugador extends Persistible {
    public static final String ERR_EMAIL_OBLIGATORIO = "El email es obligatorio";

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
        setNombre(nombre);
        setContrasena(contrasena);
        setEmail(email);
        this.xp = 0L;

    }



    public Long getXp() {
        return xp;
    }




    public void setXp(long xp) {
       if(ValidationUtils.ISNegative(xp)){
           throw new IllegalArgumentException("El xp no puede ser negativo");
       }

        this.xp = xp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
       if (ValidationUtils.isEmpty(nombre)){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre;
    }



    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        if (ValidationUtils.isEmpty(contrasena)){
            throw new IllegalArgumentException("La contraseña es obligatoria");

        } else if (contrasena.length() < 5) {
            throw new IllegalArgumentException("La contraseña no puede ser menor a 5 caracteres.");
        }
        this.contrasena = contrasena;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (ValidationUtils.isEmpty(email)){
            throw new IllegalArgumentException("El email es obligatorio");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("El email no tiene formato válido");
        }
        this.email = email;
    }

    public void sumarXP(long cantidad) {
        if (ValidationUtils.ISNegative(cantidad)){
            throw new IllegalArgumentException("El xp no puede ser negativo");
        }

        this.xp += cantidad;
    }

    public int calcularNivel() {
        return (int) (this.xp / XP_POR_NIVEL) + 1;
    }
}
