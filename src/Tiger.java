/**
 * Created by admin on 6/19/2016.
 */
public class Tiger extends Tank {
    private int armour;
    public Tiger(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af,bf,x,y,dir);
        armour = 1;
    }

    public Tiger(ActionField af, BattleField bf){
        super(af,bf);
        armour = 1;
    }

}
