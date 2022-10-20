import customexception.ValidatingInputValuesException;

import java.util.Scanner;

public class Shooter extends Game {
    public int bloodinessLevel; //уровень жести от 1 до 10
    public TypeShooter typeShooter; //тип шутера перечисление

    public Shooter()  throws ValidatingInputValuesException { // без праметров инициируем значениями по умолчанию
        super();
        this.bloodinessLevel = -1;
        this.typeGame=TypeGame.SHOOTER;
        this.typeShooter = TypeShooter.UNKNOWN; // неопределен
    }

    public Shooter(String name, int rating, int gamePlayTime, double price, int bloodinessLevel, TypeShooter typeShooter){
        super(name, rating, gamePlayTime, price);
        this.typeGame=TypeGame.SHOOTER;
        this.bloodinessLevel=bloodinessLevel;
        this.typeShooter=typeShooter;
    }

    @Override
    public String toString() {
        return "Шутер{ Название='" + name + '\'' +
                "id=" + id +
                ", Вид шутера=" + typeShooter +
                ", Кровавый ужас=" + bloodinessLevel +
                ", Оценка=" + rating +
                ", Игровое время=" + gamePlayTime +
                ", Цена=" + price +
                '}';
    }
    public static Integer inputBloodinessLevel(){
        Scanner in = new Scanner(System.in);
        String strIn;
        Integer bloodinessLevel= null;
        do {
            System.out.println("  Выберите уровень крови от 0 до 10 (пустая строка прервать):");
            strIn = in.nextLine().trim();

            if(strIn.isEmpty() || strIn.isBlank()) {
                if (Main.isCanselInput()) {
                    return null;
                } else {
                    continue;
                }
            }
            // преобразуем в число
            try {
                bloodinessLevel = Integer.valueOf(strIn); //Integer.parseInt(strIn);
                if (bloodinessLevel<0 || bloodinessLevel>10){
                    throw new NumberFormatException("Уровнь крови должен быть от 0 до 10!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! "+e.getMessage()+ " Повторите.");
                continue;
            }
            return bloodinessLevel;
        } while (true);
    }

}
