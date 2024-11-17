public class Main {
    public static void main(String[] args) {

     System.out.println("Игра началась!");
     Game game = new Game();
     game.Get_Map().Draw(game.Get_Level(), game.Get_Player(), game.Get_Enemies() );

     while(!game.Get_Game_Is_Over()){
         game.Update();
     }
    }
}