public class Enemy extends Tank {
    private int armor;

    public Enemy() {
        super();  // Вызов конструктора базового класса Tank
        this.armor = 0;
    }

    public Enemy(Tank tank, int armor) {
        super(tank.Get_Pos().Get_PosX(), tank.Get_Pos().Get_PosY(), tank.Get_Direction(), tank.Get_Speed(), tank.Get_Armor());
        this.armor = armor;
    }

    public int Get_Armor() {
        return this.armor;
    }

    public void Set_Armor(int armor) {
        this.armor = armor;
    }
    // Перегрузка метода без вызова метода базового класса
    @Override
    public void Move() {
        // Здесь логика, уникальная для этого класса
    }

    // Перегрузка метода с вызовом метода базового класса
    public void Move(int speed, Direction direction) {
        super.Move();
    }

    public void Shoot() {

    }
}
