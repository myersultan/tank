/**
 * Created by admin on 6/19/2016.
 */
public class BT7 extends AbstractTank {
    public BT7(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af,bf,x,y,dir);
        speed = super.speed*2;
    }

    public BT7(ActionField af, BattleField bf){
        super(af,bf);
        speed = super.speed*2;
    }
}
