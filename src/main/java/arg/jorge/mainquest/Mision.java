package arg.jorge.mainquest;

public class Mision {

    private String nombre;
    private int XP;

    public Mision(String nombre, int XP){

        this.nombre = nombre;
        this.XP = XP;

    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
