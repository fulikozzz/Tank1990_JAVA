import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Map {
    private Wall[][] walls;
    private Base playerBase;
    private Base[] enemyBases;

    public Map() {
        this.walls = new Wall[20][20];
        this.enemyBases = new Base[3];

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                this.walls[i][j] = new Wall(i, j, Type.EMPTY);
            }
        }

        this.playerBase = new Base();
        Position pos = new Position(10, 20);
        this.playerBase.Set_Pos(pos);
        this.playerBase.Set_Is_Destroyed(false);

        for (int i = 0; i < 3; i++) {
            pos = new Position(1, 5 + 5 * i);
            this.enemyBases[i] = new Base();
            this.enemyBases[i].Set_Pos(pos);
            this.enemyBases[i].Set_Is_Destroyed(false);
        }
    }

    public void LoadFromFile(int level) {
        String directoryPath = Paths.get("").toAbsolutePath().toString();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("map_" + level + ".txt");
        if (inputStream == null) {
            System.err.println("Ошибка: Файл не найден!");}
        else{
            String filename = "src/map_" + level + ".txt";
            File file = new File(filename);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (int i = 0; i < 20; i++) {
                    if ((line = reader.readLine()) != null) {
                        for (int j = 0; j < 20; j++) {
                            int symbol = line.charAt(j) - '0';
                            switch (symbol) {
                                case 0:
                                    this.walls[j][i].Set_Type(Type.EMPTY);
                                    break;
                                case 1:
                                    this.walls[j][i].Set_Type(Type.IRON);
                                    break;
                                case 2:
                                    this.walls[j][i].Set_Type(Type.WOOD);
                                    break;
                                case 3:
                                    this.walls[j][i].Set_Type(Type.BRIC_FULL);
                                    break;
                                case 4:
                                    this.walls[j][i].Set_Type(Type.BRICK_HALF);
                                    break;
                                case 5:
                                    this.walls[j][i].Set_Type(Type.BRICK_LOW);
                                    break;
                                case 6:
                                    this.walls[j][i].Set_Type(Type.WATER);
                                    break;
                                case 7:
                                    this.walls[j][i].Set_Type(Type.ICE);
                                    break;
                                case 8:
                                    this.walls[j][i].Set_Type(Type.BUSH);
                                    break;
                                case 9:
                                    this.walls[j][i].Set_Type(Type.PBASE);
                                    this.playerBase.Get_Pos().Set_PosX(j);
                                    this.playerBase.Get_Pos().Set_PosY(i);
                                    this.playerBase.Set_Is_Destroyed(false);
                                    break;
                                default:
                                    break;
                            }
                        }
                    } else {
                        System.err.println("Ошибка чтения строки " + (i + 1));
                        break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка открытия файла: " + filename);

            }
        }
    }

    public void Draw(int level, Player player, ArrayList<Enemy> enemies) {
        Wall[][] tempMap = new Wall[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                if (player.Get_Tank().Get_Pos().Get_PosX() == i && player.Get_Tank().Get_Pos().Get_PosY() == j) {
                    tempMap[i][j] = new Wall(i, j, Type.PLAYER);
                } else {
                    tempMap[i][j] = this.walls[i][j];
                }
            }
        }


        for (Enemy enemy : enemies) {
            int enemyX = enemy.Get_Tank().Get_Pos().Get_PosX();
            int enemyY = enemy.Get_Tank().Get_Pos().Get_PosY();
            if (enemyX >= 0 && enemyX < 20 && enemyY >= 0 && enemyY < 20) {
                tempMap[enemyX][enemyY] = new Wall(enemyX, enemyY, Type.ENEMY);
            }
        }

        // Очищаем консоль
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();

        this.LoadFromFile(level);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                switch (tempMap[j][i].Get_Type()) {
                    case EMPTY:
                        System.out.print("  "); // Пустое поле
                        break;
                    case IRON:
                        System.out.print("# "); // Железная стена
                        break;
                    case WOOD:
                        System.out.print("W "); // Деревянная стена
                        break;
                    case BRIC_FULL:
                        System.out.print("B "); // Целая кирпичная стена
                        break;
                    case BRICK_HALF:
                        System.out.print("b "); // Наполовину убитая кирпичная стена
                        break;
                    case BRICK_LOW:
                        System.out.print("l "); // Почти убитая кирпичная стена
                        break;
                    case WATER:
                        System.out.print("~ "); // Вода
                        break;
                    case ICE:
                        System.out.print("I "); // Лёд
                        break;
                    case BUSH:
                        System.out.print("b "); // Куст
                        break;
                    case PLAYER:
                        System.out.print("P "); // Игрок
                        break;
                    case ENEMY:
                        System.out.print("E "); // Противник
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }


        System.out.println("P_Base на позиции: (" + this.playerBase.Get_Pos().Get_PosX() + ", "
                + playerBase.Get_Pos().Get_PosY() + ")");

        for (int i = 0; i < 3; i++) {
            if (!enemyBases[i].Get_Is_Destroyed()) {
                System.out.println("E_base " + i + " на позиции: (" + this.enemyBases[i].Get_Pos().Get_PosX() + ", "
                        + this.enemyBases[i].Get_Pos().Get_PosY() + ")");
            }
        }
        System.out.println();
    }
}
