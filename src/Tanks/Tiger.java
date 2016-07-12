package Tanks;

import Engine.ActionField;
import Enums.Direction;
import battleField.BattleField;

import java.awt.*;

/**
 * Created by admin on 6/19/2016.
 */
public class Tiger extends AbstractTank {
    public int armor;
    public Tiger(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af,bf,x,y,dir);
        armor = 1;
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
    }

    public Tiger(ActionField af, BattleField bf){
        super(af,bf);
        armor = 1;
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
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
