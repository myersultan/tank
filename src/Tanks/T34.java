package Tanks;

import Engine.ActionField;
import Enums.Direction;
import battleField.BattleField;

import java.awt.*;

/**
 * Created by admin on 7/11/2016.
 */
public class T34 extends AbstractTank {

    public T34(ActionField af, BattleField bf, int x, int y, Direction dir){
        super(af,bf,x,y,dir);
        tankColor = new Color(0, 255, 0);
        towerColor = new Color(255, 0, 0);
    }
}
