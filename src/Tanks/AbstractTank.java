package Tanks;

import battleField.BattleField;
import Engine.ActionField;
import Engine.Bullet;
import Enums.Direction;
import Interfaces.Destroyable;
import Interfaces.Drawable;

import java.awt.*;
import java.util.Random;

public abstract class AbstractTank implements Drawable, Destroyable {



    protected int speed = 10;

    //1-up, 2-down, 3-left, 4-right
    private Direction direction;

    //current position on BF
    protected int x;
    protected int y;


    private ActionField af;
    private BattleField bf;

    protected Color tankColor;
    protected Color towerColor;

    public AbstractTank(ActionField af, BattleField bf){
        this(af, bf, 128, 512, Direction.UP);
    }

    public AbstractTank(ActionField af, BattleField bf, int x, int y, Direction direction){
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void updateX(int x){
        this.x += x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void updateY(int y){
        this.y += y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void turn(Direction direction) throws Exception{
        this.direction = direction;
        af.processTurn(this);
    }

    public void move() throws Exception{
        af.processMove(this);
    }

    public void fire() throws Exception{
        Bullet bullet = new Bullet((x+25), (y+25), direction);
        af.processFire(bullet);
    }

    public void moveRandom() throws Exception{
        Random r = new Random();
        while (true)
        {
            direction.setId(r.nextInt(5));
            if (direction.getId() > 0){
                fire();
                move();

            }
        }
    }

    public void moveToQuadrant(int v, int h) throws Exception{
        String coordinates = af.getQuadrantXY(v,h);
        int y = Integer.parseInt(coordinates.split("_")[0]);
        int x = Integer.parseInt(coordinates.split("_")[1]);

        if (this.x < x) {
            while (this.x != x){
                turn(Direction.RIGHT);
                fire();
                move();
            }

        } else {
            while (this.x != x){
                turn(Direction.LEFT);
                fire();
                move();
            }
        }

        if(this.y < y) {
            while (this.y != y){
                turn(Direction.DOWN);
                fire();
                move();
            }
        } else {
            while (this.y != y){
                turn(Direction.UP);
                fire();
                move();
            }
        }

    }

    public void clean() throws Exception {

       moveToQuadrant(1,1);
        for (int i = 2; i <= 9; i++) {
            moveToQuadrant(1,i);
            turn(Direction.DOWN);
            for (int j = 1; j <= 8; j++) {
                if (bf.scanQuadrant(j, i-1) == "B")
                    fire();
            }
        }
            fire();
            turn(Direction.LEFT);

    }

    public void destroy() {
        this.x = -1000;
        this.y = -1000;
    }

    public void draw(Graphics g) {

        g.setColor(tankColor);//new Color(255, 0, 0)
        g.fillRect(this.getX(), this.getY(), 64, 64);

        g.setColor(towerColor);
        if (this.getDirection().getId() == 1) {
            g.fillRect(this.getX() + 20, this.getY(), 24, 34);
        } else if (this.getDirection().getId() == 2) {
            g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
        } else if (this.getDirection().getId() == 3) {
            g.fillRect(this.getX(), this.getY() + 20, 34, 24);
        } else {
            g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
        }

    }



}
