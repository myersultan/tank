package Tanks;

import Engine.ActionField;
import Enums.Direction;
import battleField.BattleField;

/**
 * Created by admin on 7/7/2016.
 */
public class Aggressor extends AbstractTank {

    public Aggressor(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af, bf, x, y, dir);
    }



    public void updateX(int x){
        this.x += x;
    }

    public void updateY(int y){
        this.y += y;
    }
}
