public class Main {
    public static void main(String[] args) {
        System.out.println("Игра началась!");
        Game game = new Game();
        while (!game.Get_Game_Is_Over()) {
            game.Update();
        }
    }
}
