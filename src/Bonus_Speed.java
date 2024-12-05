public class Bonus_Speed extends Bonus {
    public Bonus_Speed(int x, int y, int activity_time) {
        super(x, y, KinfOfBonus.SPEED, activity_time);
    }

    @Override
    public void Activate() {
        System.out.println("Бонус скорости активирован!");

    }
}