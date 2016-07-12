package battleField;

import Interfaces.Destroyable;

import java.awt.*;

/**
 * Created by admin on 7/12/2016.
 */
public class Rock extends AbstractObject implements Destroyable {

    public Rock(BattleField bf, int x, int y){
        super(bf,x,y);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(183, 145, 30));
        g.fillRect(x, y, 64, 64);
    }
}
