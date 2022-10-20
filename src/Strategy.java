public class Strategy extends Game{
    public TypeStrategy typeStrategy;

    public Strategy() {
        super();
        this.typeGame=TypeGame.STRATEGY;
        this.typeStrategy = TypeStrategy.RTS;
    }

    public Strategy(String name, int rating, int gamePlayTime, double price, TypeStrategy typeStrategy) {
        super(name, rating, gamePlayTime, price);
        this.typeGame=TypeGame.STRATEGY;
        this.typeStrategy = typeStrategy;
    }

    @Override
    public String toString() {
        return "Стратегия { Название='" + name + "\'" +
                ", id=" + id +
                ", Вид стратежки=" + typeStrategy.getValue() +
                ", Оценка=" + rating +
                ", Игровое время=" + gamePlayTime +
                ", Цена=" + price +
                '}';
    }
}
