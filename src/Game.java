import java.util.Scanner;

public abstract class Game {
    private static int nextId = 1;
    protected int id;
    protected TypeGame typeGame; //перечисление Тип Игры
    protected String name; // название
    protected int rating; // оценка
    protected int gamePlayTime; // игровое время
    protected double price; // стоимость

    public Game() {

        this.name = "";
        this.rating = -1;
        this.gamePlayTime = -1;
        this.price = -1;
    }

    public Game(String name, int rating, int gamePlayTime, double price) {
        this.id = nextId;// (int) ( Math.random() * 2147483647 ); //2147483647 -максимальный инт
        nextId++;
        //this.typeGame = typeGame;
        this.name = name;
        this.rating = rating;
        this.gamePlayTime = gamePlayTime;
        this.price = price;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", Название='" + name + '\'' +
                ", Оценка=" + rating +
                ", Время прохождения=" + gamePlayTime +
                ", Цена=" + price +
                '}';
    }
    public static String inputName(){
        Scanner in = new Scanner(System.in);
        String strIn;
        String name; // название игры
        do {
            System.out.println("  Название игры (Пустая строка прервать)");
            name = in.nextLine().trim();
            if(name.isEmpty() || name.isBlank()) {
                if (Main.isCanselInput()) {
                    return null;
                }else{continue;}
            }
            return name;
        } while (true);
    }

    public static Integer inputRating(){
        //ReturnInput ri = new ReturnInput();
        Scanner in = new Scanner(System.in);
        String strIn;
        Integer rating=null;
        do {
            System.out.println("  Введите оценку игры от 0 до 10 (Пустая строка прервать):");
            strIn = in.nextLine().trim();
            /// Можно выполнить проверку по другому ???
            /// if((strIn.isEmpty() | strIn.isBlank()) & isCanselInput()) {return;} else {continue;}
            /// т.е. Если не выполняется первое условие то не переходим к вызову isCanselInput()
            if(strIn.isEmpty() || strIn.isBlank()) {
                if (Main.isCanselInput()) {
                    return null;
                } else {
                    System.out.println("Повторите ввод оценки игры!");
                    continue;
                }
            }
            try {
                rating = Integer.valueOf(strIn);
                if (rating == null || rating<0 || rating >10){
                    throw new NumberFormatException("Оценка должна быть от 0 до 10 !");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! "+e.getMessage()+ " Повторите ввод оценки игры!");
                continue;
            }
            return rating;
        } while (true);
    }

    public static Integer inputGamePlayTime(){
        Scanner in = new Scanner(System.in);
        String strIn;
        Integer gamePlayTime= null;
        do {
            System.out.println("  Игровое время (Пустая строка прервать):");
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
                gamePlayTime = Integer.valueOf(strIn); //Integer.parseInt(strIn);
                if (gamePlayTime<0){
                    throw new NumberFormatException("Игровое время должна быть положительным числом!");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! "+e.getMessage()+ " Повторите.");
                continue;
            }
            return gamePlayTime;
        } while (true);
    }

    public static Double inputPrice() {
        Scanner in = new Scanner(System.in);
        String strIn;
        Double price = null;
        do {
            System.out.println("  Стоимость игры (Пустая строка прервать):");
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
                price = Double.valueOf(strIn);//Double.parseDouble(strIn);
                if (price<0){
                    throw new NumberFormatException("Стоимость игры должна быть больше 0 !");
                }
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! "+e.getMessage()+ " Повторите.");
                continue;
            }
            return price;
        } while (true);
    }

}

