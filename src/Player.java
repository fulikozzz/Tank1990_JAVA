import java.util.Scanner;

public class Player extends Tank implements Cloneable {
    private int lives;
    private int score;

    public Player() {
        super();  // Вызов конструктора базового класса Tank
        this.lives = 3;
        this.score = 0;
    }

    public Player(Tank tank, int lives, int score) {
        super(tank.Get_Pos().Get_PosX(), tank.Get_Pos().Get_PosY(), tank.Get_Direction(), tank.Get_Speed(), tank.Get_Armor());
        this.lives = lives;
        this.score = score;
    }

    public int Get_Lives() {
        return this.lives;
    }

    public int Get_Score() {
        return this.score;
    }

    public void Set_Lives(int lives) {
        this.lives = lives;
    }

    public void Set_Score(int score) {
        this.score = score;
    }

    public boolean Control() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите команду (W - вверх, S - вниз, A - влево, D - вправо, F - выстрел): ");
        String key = scanner.nextLine();

        switch (key.toLowerCase()) {
            case "w":
                this.Set_Direction(Direction.UP);
                this.Move();
                break;
            case "d":
                this.Set_Direction(Direction.RIGHT);
                this.Move();
                break;
            case "s":
                this.Set_Direction(Direction.DOWN);
                this.Move();
                break;
            case "a":
                this.Set_Direction(Direction.LEFT);
                this.Move();
                break;
            case "f":
                this.Shoot();
                break;
            default:
                return false;
        }

        return true;
    }

    @Override
    public Player clone() {

            Player clonedPlayer = (Player) super.clone();
            clonedPlayer.Set_Lives(this.lives);  // Клонируем примитивные поля
            clonedPlayer.Set_Score(this.score);  // Клонируем примитивные поля
            return clonedPlayer;

    }
}
