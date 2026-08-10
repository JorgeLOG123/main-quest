package arg.jorge.mainquest;

public class MisionGeneral extends Mision {

    public MisionGeneral(String nombre, int XP){
        super(nombre, XP);
    }

    @Override
    public int calcularXpFinal() {
        return getXP();   // la XP tal cual, sin bonus
    }

}
