public class Enemy {
    private Tank tank;
    private int armor;

    public Enemy(){
        this.tank = new Tank();
        this.armor = 0;
    }

    public Enemy(Tank tank, int armor){
        this.tank = tank;
        this.armor = armor;
    }

    public Tank Get_Tank() {
        return this.tank;
    }

    public int Get_Armor() {
        return this.armor;
    }

    public void Set_Armor(int armor) {
        this.armor = armor;
    }

    public void Set_Tank(Tank tank) {
        this.tank = tank;
    }

    public void Move(){
        //Здесь будет реализация логики движения противника
    }

    public void Shoot(){
        //Здесь будет реализация логики стрельбы противника
    }

}
