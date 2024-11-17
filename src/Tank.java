import java.util.ArrayList;

public class Tank {
    private Position pos;
    private Direction direction;
    private int speed;
    private int armor;
    private ArrayList<Bullet> bullets;
    public static final int MAX_BULLETS_ON_SCREEN = 2;

    public Tank() {
        this.pos = new Position(1, 1);
        this.direction = Direction.LEFT;
        this.speed = 1;
        this.armor = 1;
        this.bullets = new ArrayList<>(MAX_BULLETS_ON_SCREEN);

        for (int i = 0; i < MAX_BULLETS_ON_SCREEN; i++) {
            Bullet bullet = new Bullet();
            bullet.Set_Pos(this.Get_Pos());
            bullet.Set_Direction(this.Get_Direction());
            bullet.Set_Speed(1);
            bullet.Set_IsActive(false);
            bullet.Set_BulletType(0);
            this.bullets.add(bullet);
        }
    }

    public Tank(int x, int y, Direction dir, int speed, int armor) {
        this.pos = new Position(x, y);
        this.direction = dir;
        this.speed = speed;
        this.armor = armor;
        this.bullets = new ArrayList<>(MAX_BULLETS_ON_SCREEN);

        for (int i = 0; i < MAX_BULLETS_ON_SCREEN; i++) {
            Bullet bullet = new Bullet();
            bullet.Set_Pos(this.Get_Pos());
            bullet.Set_Direction(this.Get_Direction());
            bullet.Set_Speed(1);
            bullet.Set_IsActive(false);
            bullet.Set_BulletType(0);
            this.bullets.add(bullet);
        }
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

    public int Get_Armor() {
        return this.armor;
    }

    public ArrayList<Bullet> Get_Bullets() {
        return this.bullets;
    }

    public void Set_Pos(Position position) {
        this.pos = position;
    }

    public void Set_Direction(Direction dir) {
        this.direction = dir;
    }

    public void Set_Speed(int speed) {
        this.speed = speed;
    }

    public void Set_Armor(int armor) {
        this.armor = armor;
    }

    public void Set_Bullet(int index, Bullet bullet) {
        if (index >= 0 && index < MAX_BULLETS_ON_SCREEN) {
            this.bullets.set(index, bullet);
        }
    }

    public boolean Check_Border() {
        Position pos = this.Get_Pos();
        Direction direction = this.Get_Direction();

        if (direction == Direction.UP && pos.Get_PosY() - this.Get_Speed() < 0 ||
                direction == Direction.RIGHT && pos.Get_PosX() + this.Get_Speed() >= 20 ||
                direction == Direction.DOWN && pos.Get_PosY() + this.Get_Speed() >= 20 ||
                direction == Direction.LEFT && pos.Get_PosX() - this.Get_Speed() < 0) {
            this.pos.Set_PosX(pos.Get_PosX());
            this.pos.Set_PosY(pos.Get_PosY());
            return true;
        }
        return false;
    }

    public void Move() {
        Direction dir = this.Get_Direction();
        switch (dir) {
            case UP:
                if (!this.Check_Border()) {
                    this.pos.Set_PosY(this.pos.Get_PosY() - this.Get_Speed());
                }
                break;
            case RIGHT:
                if (!this.Check_Border()) {
                    this.pos.Set_PosX(this.pos.Get_PosX() + this.Get_Speed());
                }
                break;
            case DOWN:
                if (!this.Check_Border()) {
                    this.pos.Set_PosY(this.pos.Get_PosY() + this.Get_Speed());
                }
                break;
            case LEFT:
                if (!this.Check_Border()) {
                    this.pos.Set_PosX(this.pos.Get_PosX() - this.Get_Speed());
                }
                break;
        }
    }

    public void Shoot() {
        for (int i = 0; i < MAX_BULLETS_ON_SCREEN; i++) {
            if (!this.bullets.get(i).Get_IsActive()) {
                Bullet bullet = this.bullets.get(i);
                bullet.Set_Pos(this.pos);
                bullet.Set_Direction(this.direction);
                bullet.Set_IsActive(true);
                bullet.Move();
                return;
            }
        }
    }
}
