import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private boolean game_is_over;
    private static int level;
    private int amount_of_enemies;
    private Map map;
    private Player player;
    private ArrayList<Enemy> enemies;


    public static void SelectLevel() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите уровень (1 или 2):");
        try {
            level = scanner.nextInt();
            if (level < 1 || level > 2) {
                throw new Exception();
            }
        }
        catch (Exception ex){
            System.out.println("Неверный ввод! Выбран уровень 1.");
            level = 1;
        }

        System.out.println("Выбран уровень: " + level);
    }


    public Game() {
        this.game_is_over = false;
        this.amount_of_enemies = 2;
        this.map = new Map();
        this.player = new Player();
        this.enemies = new ArrayList<>();
        InitializeEnemies(amount_of_enemies);
    }


    public boolean Get_Game_Is_Over() {
        return this.game_is_over;
    }

    public static int Get_Level() {
        return level;
    }

    public int Get_Amount_Of_Enemies() {
        return this.amount_of_enemies;
    }

    public Player Get_Player() {
        return this.player;
    }

    public ArrayList<Enemy> Get_Enemies() {
        return this.enemies;
    }

    public Map Get_Map() {
        return this.map;
    }

    public void Set_Game_Is_Over(boolean game_is_over) {
        this.game_is_over = game_is_over;
    }

    public void Set_Amount_Of_Enemies(int amount_of_enemies) {
        this.amount_of_enemies = amount_of_enemies;
    }

    public void Set_Enemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }


    public void InitializeEnemies(int amount_of_enemies) {
        enemies.clear();
        for (int i = 0; i < amount_of_enemies; i++) {
            Tank tank = new Tank();
            Position pos = new Position((int) (Math.random() * 20), (int) (Math.random() * 20));
            tank.Set_Pos(pos);
            tank.Set_Direction(Direction.UP);
            tank.Set_Speed(1);
            Enemy enemy = new Enemy(tank, 1);
            Enemy enemy_clone = new Enemy(tank,enemy.Get_Armor());
            enemies.add(enemy_clone);
            enemies.add(enemy);

        }
    }

    public boolean Victory_Check() {
        if (this.enemies.isEmpty()) {
            this.game_is_over = true;
            return true;
        }
        return false;
    }

      public boolean bulletHit() {
        boolean hitDetected = false;

        for (int i = 0; i < amount_of_enemies; i++) {
            for (Bullet bullet : player.Get_Bullets()) {
                if (bullet.Get_IsActive() && bullet.Get_Pos().Get_PosX() == enemies.get(i).Get_Pos().Get_PosX() &&
                        bullet.Get_Pos().Get_PosY() == enemies.get(i).Get_Pos().Get_PosY()) {

                    bullet.Set_IsActive(false);

                    enemies.remove(i);
                    amount_of_enemies--;
                    hitDetected = true;

                    Bonus bonus = new Bonus_Speed(5, 5, 10);
                    bonus.Activate();

                    break;
                }
            }
            if (hitDetected) break;
        }
        return hitDetected;
    }

    public void Update() {
        if (this.player.Control()) {
            this.map.Draw(level, this.player, this.enemies);

            for (int i = 0; i < this.amount_of_enemies; i++) {
                Enemy enemy = enemies.get(i);
                System.out.println("Противник " + (i + 1) + " находится на координатах (" +
                        enemy.Get_Pos().Get_PosX() + ";" + enemy.Get_Pos().Get_PosY() +
                        ") с направлением " + enemy.Get_Direction());
            }
        }
        for (Bullet bullet : player.Get_Bullets()) {
            if (bullet.Get_IsActive()) {
                if (bulletHit()) {
                    System.out.println("Снаряд попал в противника на позиции (" + bullet.Get_Pos().Get_PosX() + ";" +
                            bullet.Get_Pos().Get_PosY() + ") и уничтожил его.");
                }
                bullet.Move();
                System.out.println("Координаты снаряда (" + bullet.Get_Pos().Get_PosX() + ";" +
                        bullet.Get_Pos().Get_PosY() + ") в направлении " + bullet.Get_Direction());

            }
        }
        if (Victory_Check()) {
            System.out.println("\nВы победили!");
        }
    }
}
