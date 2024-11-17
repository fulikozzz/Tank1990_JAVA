import java.util.Scanner;

public class Player {
    private Tank tank;
    private int lives;
    private int score;

    public Player() {
        this.tank = new Tank();
        this.lives = 3;
        this.score = 0;
    }

    public Player(Tank tank, int lives, int score) {
        this.tank = tank;
        this.lives = lives;
        this.score = score;
    }

    public Tank Get_Tank() {
        return this.tank;
    }

    public int Get_Lives() {
        return this.lives;
    }

    public int Get_Score() {
        return this.score;
    }

    public void Set_Tank(Tank tank) {
        this.tank = tank;
    }

    public void Set_Lives(int lives) {
        this.lives = lives;
    }

    public void Set_Score(int score) {
        this.score = score;
    }

    public boolean Control() {
        Scanner scanner = new Scanner(System.in); // Создаем объект Scanner для чтения ввода с клавиатуры
        String key = scanner.nextLine();

        switch (key.toLowerCase()) {
            case "w":
                this.tank.Set_Direction(Direction.UP);
                this.tank.Move();
                break;
            case "d":
                this.tank.Set_Direction(Direction.RIGHT);
                this.tank.Move();
                break;
            case "s":
                this.tank.Set_Direction(Direction.DOWN);
                this.tank.Move();
                break;
            case "a":
                this.tank.Set_Direction(Direction.LEFT);
                this.tank.Move();
                break;
            case "f":
                this.tank.Shoot();
                break;
            default:
                return false;
        }

        return true;
    }
}
