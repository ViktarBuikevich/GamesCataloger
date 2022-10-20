import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ListGames {
    public Game[] arrayGames;

//    public Integer inputBloodinessLevel(){
//        Scanner in = new Scanner(System.in);
//        String strIn;
//        Integer bloodinessLevel= null;
//        do {
//            System.out.println("  Выберите уровень крови от 0 до 10 (пустая строка прервать):");
//            strIn = in.nextLine().trim();
//
//            if(strIn.isEmpty() || strIn.isBlank()) {
//                if (Main.isCanselInput()) {
//                    return null;
//                } else {
//                    continue;
//                }
//            }
//            // преобразуем в число
//            try {
//                bloodinessLevel = Integer.valueOf(strIn); //Integer.parseInt(strIn);
//                if (bloodinessLevel<0 || bloodinessLevel>10){
//                    throw new NumberFormatException("Уровнь крови должен быть от 0 до 10!");
//                }
//            } catch (NumberFormatException e) {
//                System.err.println("Ошибка! "+e.getMessage()+ " Повторите.");
//                continue;
//            }
//            return bloodinessLevel;
//        } while (true);
//    }

    public void Add(){
        System.out.println("Добавляем запись в список каталога:");
        Scanner in = new Scanner(System.in);
        String strIn;

        TypeGame[] arrTypeGame = TypeGame.values();
        TypeGame typeGame = Main.selectEnumValue(arrTypeGame, "'Тип игры'");
        if(typeGame == null){return;}
        //////

        String name = Game.inputName(); // название игры
        if(name==null){return;}

        Integer ir=Game.inputRating();
        if (ir == null) {return;}
        int rating = ir.intValue(); // оценка

        Integer iGPT = Game.inputGamePlayTime();
        if (iGPT == null) {return;}
        int gamePlayTime = iGPT.intValue(); // игровое время

        Double iP = Game.inputPrice();
        if (iP == null) {return;}
        double price= iP.doubleValue(); // стоимость

        /// Определяем дальнейшие реквизиты в зависимости от типа игры
        switch (typeGame){
            case RPG:

                boolean playability = RPG.inputPlayability(); //играбельность
                TypeRPG[] arrtypeRPG=TypeRPG.values(); // тип РПГ-шки
                TypeRPG typeRPG = Main.selectEnumValue(arrtypeRPG, "'Вид РПГ-шки'");
                if(typeRPG == null){return;}
                RPG elRPG=new RPG(name, rating, gamePlayTime, price, playability, typeRPG);
                addNewElement(elRPG);
                break;
            case SHOOTER:
                //Shooter(TypeGame typeGame, String name, int rating, int gamePlayTime, double price, int bloodinessLevel, TypeShooter typeShooter)
                Integer iBL = Shooter.inputBloodinessLevel();
                if (iBL==null){return;}
                int bloodinessLevel = iBL.intValue(); // кровожадность

                //TypeShooter typeShooter
                TypeShooter[] arrtypeSooter=TypeShooter.values(); // тип шутера
                TypeShooter typeShooter = Main.selectEnumValue(arrtypeSooter, "'Вид Стрелялки'");
                if(typeShooter == null){return;}
                Shooter elShooter=new Shooter(name, rating, gamePlayTime, price, bloodinessLevel, typeShooter);
                addNewElement(elShooter);
                break;
            case STRATEGY:
                //TypeSrategy typeSrategy
                TypeStrategy[] arrtypeStrategy= TypeStrategy.values(); // тип РПГ-шки
                TypeStrategy typeSrategy = Main.selectEnumValue(arrtypeStrategy, "'Вид стратежки'");
                if(typeSrategy == null){return;}
                Strategy elStrategy=new Strategy(name, rating, gamePlayTime, price, typeSrategy);
                addNewElement(elStrategy);
                break;

        } // switch (typeGame)

    }
    public void printList(Game[] arrayGames, String title){
        if (arrayGames == null){
            System.out.println("Пусто!!!");
            return;
        }
        System.out.println(title);
        for (int i = 0; i < arrayGames.length; i++) {
            System.out.println(""+i+". "+arrayGames[i].toString());
        }
    }
    public  boolean remove(int index){
        int length=arrayGames.length;
        if (length==1){
            System.out.println("Удалять единственный элемент в списке не буду!!!");
            return false;
        }
        int numMoved = length - index - 1;
        System.arraycopy(arrayGames, index + 1, arrayGames, index, numMoved);
        Game[] arTmp;
        arTmp = new Game[length - 1];
        System.arraycopy(arTmp, 0, arrayGames, 0, length-1);
        arrayGames= arTmp; // ??? arTmp.clone();
        return true;
    }
    private <T extends Game> void addNewElement(T element){
        Game[] arTmp;
        if (arrayGames==null){
            arrayGames = new Game[1];
            arrayGames[0]= element;

        } else {
            int len = arrayGames.length;
            arTmp = new Game[len + 1];
            System.arraycopy(arrayGames,0,arTmp,0,len);
            arTmp[len]=element;
            len++;
            arrayGames =arTmp;
//            arrayGames = new Game[len];
//            System.arraycopy(arTmp,0,arrayGames,0, len);
        }
        //this.printList(arrayGames, "Список игр:");
    }
    public Map<Integer, Game> searchByName(String findName){
        Map<Integer, Game> gameMap = new HashMap<Integer, Game>();
        if (Main.isEmpty(findName)) {return null;}
        for (int i = 0; i < arrayGames.length; i++) {
            Game el = this.arrayGames[i];
            if (Main.isEmpty(el.name)) {
                continue;
            }
            // поиск без учета регистра символов
            if (el.name.toLowerCase().contains(findName.toLowerCase())) {
                gameMap.put(Integer.valueOf(i), el);
            }
        }
        if (gameMap.isEmpty()){
            return null;
        }
        return gameMap;
    }

    public Game searchById(Integer findId, Integer index){
        if (findId < 1) {return null;}
        for (int i = 0; i < this.arrayGames.length; i++) {
        Game el = this.arrayGames[i];
            if (findId == el.id){
                index=i;
                return el;}
        }

        return null;
    }
}
