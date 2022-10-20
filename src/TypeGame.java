public enum TypeGame {
    SHOOTER("шутер"),
    RPG("РПГ"),
    STRATEGY("стратегия");
    private final String value;

    TypeGame(String title) {
        this.value = title;
    }

    public static TypeGame valueOfLabel(String label){
        for(TypeGame e:values()){
            if (e.value.equals(label)){return e;}
        }
        return null;
    }
    public static String print(){
        String s="{ ";
        for(TypeGame e:values()){
            s = s + e.value + ((e == TypeGame.STRATEGY) ? " }": ", ");
        }
        return s;
    }
    public String getValue() {
        return value;
    }


    @Override
    public String toString() {
        return "Тип игры {" +
                "title='" + value + '\'' +
                '}';
    }

}
