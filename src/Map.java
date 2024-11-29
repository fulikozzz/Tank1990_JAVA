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

    public List<WallInfo> LoadFromFile(int level) {
        List<WallInfo> wallInfoList = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("map_" + level + ".txt");
        if (inputStream == null) {
            System.err.println("Ошибка: Файл не найден!");
        } else {
            String filename = "src/map_" + level + ".txt";
            File file = new File(filename);

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                for (int i = 0; i < 20; i++) {
                    if ((line = reader.readLine()) != null) {
                        for (int j = 0; j < 20; j++) {
                            int symbol = line.charAt(j) - '0';
                            Type wallType = Type.EMPTY;
                            switch (symbol) {
                                case 1:
                                    wallType = Type.IRON;
                                    break;
                                case 2:
                                    wallType = Type.WOOD;
                                    break;
                                case 3:
                                    wallType = Type.BRIC_FULL;
                                    break;
                                case 4:
                                    wallType = Type.BRICK_HALF;
                                    break;
                                case 5:
                                    wallType = Type.BRICK_LOW;
                                    break;
                                case 6:
                                    wallType = Type.WATER;
                                    break;
                                case 7:
                                    wallType = Type.ICE;
                                    break;
                                case 8:
                                    wallType = Type.BUSH;
                                    break;
                                case 9:
                                    wallType = Type.PBASE;
                                    break;
                                default:
                                    break;
                            }
                            WallInfo wallInfo = new WallInfo(wallType, new Position(j, i));
                            wallInfoList.add(wallInfo); // Добавляем информацию о стене в список
                        }

                    } else {
                        System.err.println("Ошибка чтения строки " + (i + 1));
                        break;
                    }
                }

            } catch (Exception e) {
                System.err.println("Ошибка открытия файла: " + filename);
            }
        }
        return wallInfoList; // Возвращаем список с информацией о стенах
    }

    public void Draw(int level, Player player, ArrayList<Enemy> enemies) {
        // Получаем список WallInfo
        List<WallInfo> wallInfoList = LoadFromFile(level);

        Wall[][] tempMap = new Wall[20][20];

        // Проходим по списку и заполняем временную карту
        for (WallInfo wallInfo : wallInfoList) {
            Position pos = wallInfo.getPosition();
            Type type = wallInfo.getType();
            tempMap[pos.Get_PosX()][pos.Get_PosY()] = new Wall(pos.Get_PosX(), pos.Get_PosY(), type);
        }

        // Обрабатываем позиции игрока
        int playerX = player.Get_Tank().Get_Pos().Get_PosX();
        int playerY = player.Get_Tank().Get_Pos().Get_PosY();
        tempMap[playerX][playerY] = new Wall(playerX, playerY, Type.PLAYER);

        // Обрабатываем позиции врагов
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

        // Выводим карту
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                switch (tempMap[j][i].Get_Type()) {
                    case EMPTY:
                        System.out.print("  ");
                        break;
                    case IRON:
                        System.out.print("# ");
                        break;
                    case WOOD:
                        System.out.print("W ");
                        break;
                    case BRIC_FULL:
                        System.out.print("B ");
                        break;
                    case BRICK_HALF:
                        System.out.print("b ");
                        break;
                    case BRICK_LOW:
                        System.out.print("l ");
                        break;
                    case WATER:
                        System.out.print("~ ");
                        break;
                    case ICE:
                        System.out.print("I ");
                        break;
                    case BUSH:
                        System.out.print("b ");
                        break;
                    case PLAYER:
                        System.out.print("P ");
                        break;
                    case ENEMY:
                        System.out.print("E ");
                        break;
                    default:
                        break;
                }
            }
            System.out.println();
        }

        // Выводим позиции баз
        System.out.println("P_Base на позиции: (" + this.playerBase.Get_Pos().Get_PosX() + ", "
                + playerBase.Get_Pos().Get_PosY() + ")");
        for (int i = 0; i < 3; i++) {
            if (!enemyBases[i].Get_Is_Destroyed()) {
                System.out.println("E_base " + i + " на позиции: (" + enemyBases[i].Get_Pos().Get_PosX() + ", "
                        + this.enemyBases[i].Get_Pos().Get_PosY() + ")");
            }
        }
        System.out.println();
    }
}