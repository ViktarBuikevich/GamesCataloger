import java.util.Locale;
import java.util.Scanner;

public class RPG extends Game{
    public boolean рlayability; //играбельность
    public TypeRPG typeRPG;

    public RPG() {
        super();
        this.рlayability = false;
        this.typeGame=TypeGame.RPG;
        this.typeRPG = TypeRPG.UNKNOWN;
    }

    public RPG(String name, int rating, int gamePlayTime, double price, boolean рlayability, TypeRPG typeRPG) {
        super(name, rating, gamePlayTime, price);
        this.typeGame=TypeGame.RPG;
        this.рlayability=рlayability;
        this.typeRPG=typeRPG;
    }

    @Override
    public String toString() {
        return "РПГ-шка{ Название='" + name + "\'" +
                ", id=" + id +
                ", Вид РПГ-шки=" + typeRPG +
                ", Играбельность=" + ((рlayability)?"Да":"Нет") +
                ", Оценка=" + rating +
                ", Игровое время=" + gamePlayTime +
                ", Цена=" + price +
                '}';
    }
    public static Boolean inputPlayability(){
        char letter;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println(" Играбельность!Да - 1 /Нет - 0)");
            String str = in.next().trim();
            if (str.isBlank()  || str.isEmpty()){
                //Пустая строка навыход
                return null;
            }
            letter =str.charAt(0);
            if ("01".indexOf(letter) >= 0) {
                return  (letter=='1');
            }
            else {
                System.out.println("Не корректный ответ! Повторите");
            }
        } while (true);
    }
}
