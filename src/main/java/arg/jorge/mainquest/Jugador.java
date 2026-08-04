package arg.jorge.mainquest;

public class Jugador {

    private String nombre;
    private String contraseña;
    private int XP;
    public static final int XP_POR_NIVEL = 100;

    public Jugador(String nombre, String contraseña) {

        this.nombre = nombre;
        this.contraseña = contraseña;
        this.XP = 0;

    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void sumarXP(int cantidad) {
        this.XP += cantidad;
    }

     public int calcularNivel() {
        int nivelTotal = this.XP / XP_POR_NIVEL + 1;
        return nivelTotal;

     }



}
