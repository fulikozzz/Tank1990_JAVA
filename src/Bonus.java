public class Bonus {
    private Position pos;
    private KinfOfBonus kind;
    private int activity_time;

    public Bonus(){
        this.pos = new Position(0,0);
        this.kind = KinfOfBonus.LIFE;
        this.activity_time = 0;
    }

    public Bonus (int x, int y, KinfOfBonus kind, int activity_time){
        this.pos = new Position(x,y);
        this.kind = kind;
        this.activity_time = activity_time;
    }

    public Position Get_Pos() {
        return this.pos;
    }

    public KinfOfBonus Get_Kind(){
        return this.kind;
    }

    public int Get_Activity_Time(){
        return this.activity_time;
    }

    public void Set_Pos(Position pos){
        this.pos = pos;
    }

    public void Set_Kind(KinfOfBonus kind){
        this.kind = kind;
    }

    public void Set_Activity_Time (int time){
        this.activity_time = time;
    }
}
