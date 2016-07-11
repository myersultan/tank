import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class ActionField extends JPanel{

    private boolean COLORED_MODE = false;

    BattleField battleField;
    AbstractTank tank;
    Bullet bullet;
    Tiger tankAgr;

    public void runTheGame() throws Exception {

      tank.clean();

    }

    public void processMove(AbstractTank tank) throws Exception{
        this.tank = tank;
        Direction direction = tank.getDirection();
        int step = 1;
        int covered = 0;

        // check limits x: 0, 513; y: 0, 513
        if ((direction.getId() == 1 && tank.getY() == 0) || (direction.getId() == 2 && tank.getY() >= 512)
                || (direction.getId() == 3 && tank.getX() == 0) || (direction.getId() == 4 && tank.getX() >= 512)) {
            System.out.println("[illegal move] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            return;
        }

        tank.turn(direction);

        while (covered < 64) {
            if (direction.getId() == 1) {
                tank.updateY(-step);
                System.out.println("[move up] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else if (direction.getId() == 2) {
                tank.updateY(step);
                System.out.println("[move down] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else if (direction.getId() == 3) {
                tank.updateX(-step);
                System.out.println("[move left] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            } else {
                tank.updateX(step);
                System.out.println("[move right] direction: " + direction + " tankX: " + tank.getX() + ", tankY: " + tank.getY());
            }
            covered += step;

            repaint();
            Thread.sleep(tank.getSpeed());
        }
    }

    public void processTurn(AbstractTank tank) throws Exception{
        repaint();
    }

    public void processFire(Bullet bullet) throws Exception{

        this.bullet = bullet;
        int step = 1;

        while ((bullet.getX() > - 14 && bullet.getX()  < 590) && (bullet.getY()  > -14 && bullet.getY() < 590)) {
            if (bullet.getDirection().getId() == 1) {
                bullet.updateY(-step);
            } else if (bullet.getDirection().getId() == 2) {
                bullet.updateY(step);
            } else if (bullet.getDirection().getId() == 3) {
                bullet.updateX(-step);
            } else {
                bullet.updateY(step);
            }

            if (processInterception()) {
                bullet.destroy();
            }

            repaint();
            Thread.sleep(bullet.getSpeed());
        }
    }

    private boolean processInterception() throws Exception {
        String coordinates = getQuadrant(bullet.getX(), bullet.getY());
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (y >= 0 && y < 9 && x >= 0 && x < 9) {
            if (!battleField.scanQuadrant(y, x).trim().isEmpty()) {
                battleField.updateQuadreant(y, x, "");
                return true;
            }
            if(getQuadrant(tankAgr.getX(), tankAgr.getY()).equals(coordinates)){
                tankAgr.destroy();
                bullet.destroy();
                tank.fire();
               // Thread.sleep(3000);
                newAgr();
            }
        }
        return false;
    }

    public String getQuadrant(int x, int y){
        return y / 64 + "_" + x / 64;
    }

    public String getQuadrantXY(int v, int h){
        return (v - 1) * 64 + "_" + (h - 1) * 64;
    }

    public int randomPositionX()
    {
        int ran = 1+ (int)(Math.random()*3);
        int x = 0;
        if (ran == 1){
            x = 64;
        }
        if (ran == 2){
            x = 192;
        }
        if (ran == 3){
            x = 448;
        }
        return x;
    }

    public void newAgr(){
        tankAgr.setX(randomPositionX());
        tankAgr.setY(64);
    }
    // magic methods

    public ActionField() throws Exception{

        battleField = new BattleField();
        tank = new BT7(this, battleField);
        tankAgr = new Tiger(this, battleField, randomPositionX(), 64, Direction.DOWN);
        bullet = new Bullet(-100, -100, Direction.NONE);

        JFrame frame = new JFrame("Tanks");
        frame.setLocation(750, 150);
        frame.setMinimumSize(new Dimension(battleField.getBF_WIDTH(), battleField.getBF_HEIGHT() + 22));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        int i = 0;
        Color cc;
        for (int v = 0; v < 9; v++) {
            for (int h = 0; h < 9; h++) {
                if(COLORED_MODE){
                    if(i%2 == 0){
                        cc = new Color(252, 241, 177);
                    }
                    else{
                        cc = new Color(233, 243, 255);
                    }
                }
                else{
                    cc = new Color(180, 180, 180);
                }
                i++;
                g.setColor(cc);
                g.fillRect(h*64, v*64, 64, 64);

            }
        }


        for (int j = 0; j < battleField.getDimensionY(); j++) {
            for (int k = 0; k < battleField.getDimensionX(); k++) {
                if (battleField.scanQuadrant(j, k).equals("B")) {
                    String coordinates = getQuadrantXY(j + 1, k + 1);
                    int separator = coordinates.indexOf("_");
                    int y = Integer.parseInt(coordinates.substring(0, separator));
                    int x = Integer.parseInt(coordinates.substring(separator + 1));
                    g.setColor(new Color(0, 0, 255));
                    g.fillRect(x, y, 64, 64);
                }
            }
        }

        g.setColor(new Color(255, 0, 0));
        g.fillRect(tank.getX(), tank.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (tank.getDirection().getId() == 1) {
            g.fillRect(tank.getX() + 20, tank.getY(), 24, 34);
        } else if (tank.getDirection().getId() == 2) {
            g.fillRect(tank.getX() + 20, tank.getY() + 30, 24, 34);
        } else if (tank.getDirection().getId() == 3) {
            g.fillRect(tank.getX(), tank.getY() + 20, 34, 24);
        } else {
            g.fillRect(tank.getX() + 30, tank.getY() + 20, 34, 24);
        }


        g.setColor(new Color(255, 0, 0));
        g.fillRect(tankAgr.getX(), tankAgr.getY(), 64, 64);

        g.setColor(new Color(0, 255, 0));
        if (tankAgr.getDirection().getId() == 1) {
            g.fillRect(tankAgr.getX() + 20, tankAgr.getY(), 24, 34);
        } else if (tankAgr.getDirection().getId() == 2) {
            g.fillRect(tankAgr.getX() + 20, tankAgr.getY() + 30, 24, 34);
        } else if (tankAgr.getDirection().getId() == 3) {
            g.fillRect(tankAgr.getX(), tankAgr.getY() + 20, 34, 24);
        } else {
            g.fillRect(tankAgr.getX() + 30, tankAgr.getY() + 20, 34, 24);
        }


        g.setColor(new Color(255, 255, 0));
        g.fillRect(bullet.getX(), bullet.getY(), 14, 14);
    }
}