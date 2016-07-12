package battleField;

import Interfaces.Drawable;

import java.awt.*;

/**
 * Created by admin on 7/12/2016.
 */
public abstract class AbstractObject implements Drawable {

    private BattleField bf;

    protected int x;
    protected int y;
    protected Color bfColor;


    public AbstractObject(BattleField bf, int x, int y){
        this.bf = bf;
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g){
        g.setColor(bfColor);
        g.fillRect(x, y, 64, 64);
    }
}
