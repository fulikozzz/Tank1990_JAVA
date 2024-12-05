public class Main {
    public static void main(String[] args) {
        Player player2;
        Game.SelectLevel();
        System.out.println("Игра началась!");
        Game game = new Game();
        player2 = game.Get_Player().clone();
        game.Get_Player().Set_Armor(5);
        System.out.println(player2.Get_Armor());
        System.out.println(game.Get_Player().Get_Armor());
        game.Get_Map().Draw(game.Get_Level(), game.Get_Player(), game.Get_Enemies());

        while (!game.Get_Game_Is_Over()) {
            game.Update();
        }
    }
}