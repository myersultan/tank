/**
 * Created by admin on 6/19/2016.
 */
public class Tiger extends AbstractTank {
    public int armor;
    public Tiger(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af,bf,x,y,dir);
        armor = 1;
    }

    public Tiger(ActionField af, BattleField bf){
        super(af,bf);
        armor = 1;
    }

    @Override
    public void destroy() {
        if (armor >0){
            armor = 0;
        } else {
            super.destroy();
        }
    }
}
