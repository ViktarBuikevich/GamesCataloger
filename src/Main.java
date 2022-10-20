import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        boolean flgExit=false;
        ListGames listGames = new ListGames();
        do {
            flgExit=showMainMenu(listGames);
        } while(!flgExit);
    }
    public static boolean isCanselInput(){
        System.out.println("Прервать ввод значений и вернуться в меню!(y/n)");
        char letter;
        Scanner in = new Scanner(System.in);
        do {
            letter = in.next().toLowerCase(Locale.ROOT).charAt(0);
            switch (letter){
                case 'y':
                    return true;
                case 'n':
                    return false;
                default:
                    System.out.println("Не корректный ответ! (y/n). Повторите: ");
                    //break; /// должен прервать switch (letter), а не do {} while(true)
            }
        } while (true);
    }
    public static <T> T selectEnumValue(T[] arr, String nameEnum) {
        //al- список значений Перечисления выводим его и предлагаем выбрать значение

        Scanner in = new Scanner(System.in);
        String strIn;
        int numElement; // оценка
        do {
            System.out.println("Введите номер значения из списка " + nameEnum + ": ");
            for (int i = 0; i < arr.length; i++) {
                System.out.println("" + i + " - " +arr[i].toString()+";");
            }
            strIn = in.nextLine().trim();
            /// Можно выполнить проверку по другому ???
            /// if((strIn.isEmpty() | strIn.isBlank()) & isCanselInput()) {return;} else {continue;}
            /// т.е. Если не выполняется первое условие то не переходим к вызову isCanselInput()
            if(strIn.isEmpty() | strIn.isBlank()) {
                if (isCanselInput()) {
                    return null;
                } else {
                    continue;
                }
            }
            // преобразуем в число
            try {
                numElement = Integer.parseInt(strIn);
                if (numElement<0 || numElement > (arr.length-1)){
                    throw new NumberFormatException("Число должна быть от 0 до "+ (arr.length-1));
                }
                break;
            } catch (NumberFormatException e) {
                System.err.println("Ошибка! "+e.getMessage()+ " Повторите.");
            }
        } while (true);
        if (numElement<0 || numElement >(arr.length-1)){
            return null;
        }
        return arr[numElement];
    }
    private static boolean showMainMenu(ListGames listGames ) {
        //Глвное меню
        System.out.println("\f"); //поидее очистить экран
        System.out.println("Главное меню:");
        System.out.println("  1. Добавить новую запись о игре");
        System.out.println("  2. Поиск игры по имени/уникальному номеру");
        System.out.println("  3. Редактировать информацию в записи о игре");
        System.out.println("  4. Удалить запись о игре");
        System.out.println("  5. Вывести список игр");
        System.out.println("  6. Завершить программу");
        Scanner keyb = new Scanner(System.in);
        switch (keyb.nextInt()){
            case(1): ///!!! 1. Добавить новую запись о игре !!!
                //listGames.printList(listGames.arrayGames, "Список игр:");
                listGames.Add();
                //listGames.printList(listGames.arrayGames, "Список игр:");
                break;
            case (2): ///!!! 2. Поиск игры по имени/уникальному номеру !!!
                System.out.println("  Выберите критерий поиска:");
                System.out.println("    1. Поиск игры по имени");
                System.out.println("    2. Поиск игры по Id");
                switch (keyb.nextInt()){
                    case (1):
                        System.out.println("Поиск по названию игры: ");
                        String str= keyb.next(); // .nextLine();
                        Map<Integer, Game> gameMap = new HashMap<Integer, Game>();

                        gameMap = listGames.searchByName(str);
                        //listGames.printList(arF,"По заданной строке найдены следующие игры:");
                        if (gameMap.isEmpty()){
                            System.out.println("Ничего не нашли!");
                        } else {
                            System.out.println("По заданной строке найдены следующие игры:");
                        }
                        System.out.println(gameMap.toString());
                        System.out.println("...для продолжение нажмите ENTER.");
                        keyb.next();
                        break;
                    case (2): //2. Поиск игры по имени/уникальному номеру
                        System.out.println("Поиск по идентификатору:");
                        int id= keyb.nextInt();
                        Integer index=-1;
                        Game gameF = listGames.searchById(id, index);
                        if (!(gameF == null)){
                            System.out.println("По вашему Id=" + id + " найдена игра:"+index+". "+gameF.toString());
                        }
                        System.out.println("...для продолжение нажмите ENTER.");
                        keyb.next();
                        break;
                    default:
                        System.out.println("...непонял...");
                };
                break;
            case (3): ///!!! 3. Редактировать информацию в записи о игре !!!
                listGames.printList(listGames.arrayGames, "Выберите индекс записи для редактирования:");
                int i=keyb.nextInt();
                if(i<0 && i>listGames.arrayGames.length){
                    System.out.println("... номер запределами значений индекса ...");
                    break;
                }
                Game gameE=listGames.arrayGames[i];

                do {
                    System.out.println("Редактируем элемент списка: "+gameE.toString());
                    System.out.println("Выберите поле, Enter в пустой строке - завершить.");
                    System.out.println("1 - название: "+gameE.name);
                    System.out.println("2 - оценка: "+gameE.rating);
                    System.out.println("3 - игровое время: "+gameE.gamePlayTime);
                    System.out.println("4 - цена,");
                    if (gameE instanceof Shooter) {
                        Shooter shooter = (Shooter) gameE;
// private int bloodinessLevel; //уровень жести от 1 до 10
// private TypeShooter typeShooter; //тип шутера перечисление
                        System.out.println("5 - уровень жестокости: " + shooter.bloodinessLevel);
                        System.out.println("6 - вид шутера: " + shooter.typeShooter);
                        int key=keyb.nextInt();
                        if(key>0 && ((key<7 && (!(gameE instanceof Strategy))) || (key<6 && (gameE instanceof Strategy)))){
                            //* ??? Логично использовать switch но выход из цикла тоже break
                            if (key==1) {
                                String name = Game.inputName(); // название игры
                                if (name == null) {
                                    break;
                                }
                                shooter.name=name;
                            }
                            if (key==2) {
                                Integer ir = Game.inputRating();
                                if (ir == null) {break;}
                                shooter.rating = ir.intValue(); // оценка
                            }

                            if (key==3) {
                                Integer iGPT = Game.inputGamePlayTime();
                                if (iGPT == null) {
                                    break;
                                }
                                shooter.gamePlayTime = iGPT.intValue(); // игровое время
                            }

                            if (key==4) {
                                Double iP = Game.inputPrice();
                                if (iP == null) {
                                    break;
                                }
                                shooter.price = iP.doubleValue(); // стоимость
                            }
                            if (key==5){
                                Integer iBL =Shooter.inputBloodinessLevel();
                                if (iBL==null){break;}
                                shooter.bloodinessLevel = iBL.intValue(); // кровожадность
                                //Boolean iPlayability = Shooter.
                            }

                            if (key==6){
                                TypeShooter[] arrtype=TypeShooter.values(); // тип шутера
                                TypeShooter typeShooter = Main.selectEnumValue(arrtype, "'Вид шутера'");
                                if(typeShooter == null){break;}
                                shooter.typeShooter=typeShooter;
                            }
                        }

                    }
                    if (gameE instanceof RPG) {
                        RPG rpg = (RPG) gameE;
// public boolean рlayability; //играбельность
// public TypeRPG typeRPG;
                        System.out.println("5 - играбельность: " + rpg.рlayability);
                        System.out.println("6 - вид РПГ-шки: " + rpg.typeRPG);
                        int key=keyb.nextInt();
                        if(key>0 && ((key<7 && (!(gameE instanceof Strategy))) || (key<6 && (gameE instanceof Strategy)))){
                            //* ??? Логично использовать switch но выход из цикла тоже break
                            if (key==1) {
                                String name = Game.inputName(); // название игры
                                if (name == null) {
                                    break;
                                }
                                rpg.name=name;
                            }
                            if (key==2) {
                                Integer ir = Game.inputRating();
                                if (ir == null) {break;}
                                rpg.rating = ir.intValue(); // оценка
                            }

                            if (key==3) {
                                Integer iGPT = Game.inputGamePlayTime();
                                if (iGPT == null) {
                                    break;
                                }
                                rpg.gamePlayTime = iGPT.intValue(); // игровое время
                            }

                            if (key==4) {
                                Double iP = Game.inputPrice();
                                if (iP == null) {
                                    break;
                                }
                                rpg.price = iP.doubleValue(); // стоимость
                            }
// public boolean рlayability; //играбельность
// public TypeRPG typeRPG;
                            if (key==5){
                                Boolean iPlayability=RPG.inputPlayability();
                                if (iPlayability==null){break;}
                                rpg.рlayability = iPlayability.booleanValue(); // играбельность

                            }

                            if (key==6){
                                TypeRPG[] arrtype=TypeRPG.values(); // тип шутера
                                TypeRPG typeRPG = Main.selectEnumValue(arrtype, "'Вид РПГ-шки'");
                                if(typeRPG == null){break;}
                                rpg.typeRPG=typeRPG;
                            }
                        }
                    }

                    if (gameE instanceof Strategy) {
                        Strategy strategy = (Strategy) gameE;
// public TypeSrategy typeSrategy;
                        System.out.println("5 - вид стратегии: " + strategy.typeStrategy);
                        int key=keyb.nextInt();
                        if(key>0 && ((key<7 && (!(gameE instanceof Strategy))) || (key<6 && (gameE instanceof Strategy)))){
                            //* ??? Логично использовать switch но выход из цикла тоже break
                            if (key==1) {
                                String name = Game.inputName(); // название игры
                                if (name == null) {
                                    break;
                                }
                                strategy.name=name;
                            }
                            if (key==2) {
                                Integer ir = Game.inputRating();
                                if (ir == null) {break;}
                                strategy.rating = ir.intValue(); // оценка
                            }

                            if (key==3) {
                                Integer iGPT = Game.inputGamePlayTime();
                                if (iGPT == null) {
                                    break;
                                }
                                strategy.gamePlayTime = iGPT.intValue(); // игровое время
                            }

                            if (key==4) {
                                Double iP = Game.inputPrice();
                                if (iP == null) {
                                    break;
                                }
                                strategy.price = iP.doubleValue(); // стоимость
                            }

                            if (key==5){
                                TypeStrategy[] arrtype= TypeStrategy.values(); // тип шутера
                                TypeStrategy typeSrategy = Main.selectEnumValue(arrtype, "'Вид шутера'");
                                if(typeSrategy == null){break;}
                                strategy.typeStrategy =typeSrategy;
                            }
                        }
                    }
                }while(true);
                System.out.println("");

            case (4): ///!!! 4. Удалить запись о игре !!!
                i = 0;
                listGames.printList(listGames.arrayGames, "Список игр,введите индекс для удаления");
                Scanner in = new Scanner(System.in);
                String strIn;
                i= in.nextInt();
                if (listGames.arrayGames.length-1<i){
                    System.out.println("Значене индекса за пределами списка");
                    break;
                }
                listGames.remove(i);
                break;
            case (5): ///!!! 5. Распечатать список !!!
                listGames.printList(listGames.arrayGames, "Список игр:");
                System.out.println("Продолжить нажмите ENTER...");
                String anyKеу= keyb.next(); //Line();
                break;
            case (6): ///!!! 6. Завершить программу !!!
                return true;
            //break;
            default:
                System.out.println("...непонял, повторите:");
        }
        return false;

    }

    /* Проверяет, является ли строка пустой ("") или нулевой. */
    public static boolean isEmpty(String s) {
        return s == null || s.trim().length() == 0;
    }

}
