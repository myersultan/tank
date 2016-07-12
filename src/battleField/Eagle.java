package battleField;

import Interfaces.Destroyable;

import java.awt.*;

/**
 * Created by admin on 7/12/2016.
 */
public class Eagle extends AbstractObject implements Destroyable {

    public Eagle(BattleField bf, int x, int y){
        super(bf, x, y);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(106, 143, 22));
        g.fillRect(x, y, 64, 64);
    }
}
