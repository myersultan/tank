package battleField;

import Interfaces.Destroyable;

import java.awt.*;

/**
 * Created by admin on 7/12/2016.
 */
public class Brick extends AbstractObject implements Destroyable {

    public Brick(BattleField bf, int x, int y){
        super(bf,x,y);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(60, 0, 0));
        g.fillRect(x, y, 64, 64);
    }
}
