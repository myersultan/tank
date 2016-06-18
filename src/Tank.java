import java.util.Random;

public class Tank {

    final int UP = 1;
    final int DOWN = 2;
    final int LEFT = 3;
    final int RIGHT = 4;

    private int speed = 10;

    //1-up, 2-down, 3-left, 4-right
    private int direction;

    //current position on BF
    private int x;
    private int y;


    private ActionField af;
    private BattleField bf;

    public Tank(ActionField af, BattleField bf){
        this(af, bf, 128, 512, 1);
    }

    public Tank(ActionField af, BattleField bf, int x, int y, int direction){
        this.af = af;
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    public void updateX(int x){
        this.x += x;
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

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void turn(int direction) throws Exception{
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
            direction = r.nextInt(5);
            if (direction > 0){
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
                turn(RIGHT);
                fire();
                move();
            }

        } else {
            while (this.x != x){
                turn(LEFT);
                fire();
                move();
            }
        }

        if(this.y < y) {
            while (this.y != y){
                turn(DOWN);
                fire();
                move();
            }
        } else {
            while (this.y != y){
                turn(UP);
                fire();
                move();
            }
        }

    }

    void clean() throws Exception {

       moveToQuadrant(1,1);
        for (int i = 2; i <= 9; i++) {
            moveToQuadrant(1,i);
            turn(DOWN);
            for (int j = 1; j <= 8; j++) {
                if (bf.scanQuadrant(j, i-1) == "B")
                    fire();
            }
        }
            fire();
            turn(LEFT);

    }

    void destroy() throws Exception {
        this.x = -1000;
        this.y = -1000;
    }



}
