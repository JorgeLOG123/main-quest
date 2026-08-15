package arg.jorge.mainquest;

public class Jugador {

    private String nombre;
    private String contraseña;
    private String email;

    private int XP;
    public static final int XP_POR_NIVEL = 100;

    public Jugador(String nombre, String contraseña, String email) {

        this.nombre = nombre;
        this.contraseña = contraseña;
        this.XP = 0;
        this.email = email;
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

    public String getCorreo() {
        return email;
    }


    /**
     * Calcula el nivel actual del jugador según su XP acumulada.
     * @return el nivel del jugador (arranca en 1)
     */
     public int calcularNivel() {
        int nivelTotal = this.XP / XP_POR_NIVEL + 1;
        return nivelTotal;

     }



}
