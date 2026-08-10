package arg.jorge.mainquest;

public class MisionSemanal extends Mision{

    public MisionSemanal(String nombre, int XP){
        super(nombre, XP);
    }

    @Override
    public int calcularXpFinal(){
        return getXP() * 2;
    }
}
