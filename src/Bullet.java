public class Bullet {
    private Position pos;
    private Direction direction;
    private int speed;
    private boolean isActive;
    private int bulletType;

    public Bullet(){
        this.pos = new Position(0,0);
        this.direction = Direction.RIGHT;
        this.speed = 0;
        this.isActive = false;
        this.bulletType = 0;
    }

    public Bullet(int x, int y, Direction dir, int speed, boolean isActive, int bulletType){
        this.pos = new Position(x, y);
        this.direction = dir;
        this.speed = speed;
        this.isActive = isActive;
        this.bulletType = bulletType;
    }

    public Position Get_Pos() {
        return this.pos;
    }

    public Direction Get_Direction() {
        return this.direction;
    }

    public int Get_Speed() {
        return this.speed;
    }

    public boolean Get_IsActive() {
        return this.isActive;
    }

    public int Get_BulletType() {
        return this.bulletType;
    }

    public void Set_Pos(Position pos){
        this.pos = pos;
    }

    public void Set_Direction(Direction dir){
        this.direction = dir;
    }

    public void Set_Speed(int speed){
        this.speed = speed;
    }

    public void Set_IsActive(boolean isActive){
        this.isActive = isActive;
    }

    public void Set_BulletType(int bulletType){
        this.bulletType = bulletType;
    }

    public boolean Check_Border(){
        return this.pos.Get_PosX() < 0 || this.pos.Get_PosY() < 0 || this.pos.Get_PosX() >= 20 || this.pos.Get_PosY() >= 20;
    }

    public void Move(){
        if (!this.isActive) return;

        switch (this.direction){
            case LEFT -> this.pos.Set_PosX(this.pos.Get_PosX() - this.speed);

            case RIGHT -> this.pos.Set_PosX(this.pos.Get_PosX() + this.speed);

            case UP -> this.pos.Set_PosY(this.pos.Get_PosY() - this.speed);

            case DOWN -> this.pos.Set_PosY(this.pos.Get_PosY() + this.speed);
        }

        if(this.Check_Border()) this.isActive = false;
    }
}