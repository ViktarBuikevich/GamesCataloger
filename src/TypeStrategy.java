public enum TypeStrategy {
    UNKNOWN("неопределено"),
    TBS("пошаговая стратегия"), //пошаговая
    RTS("стратегия реального времени"), //в реале
    RTT("тактика реального времени"); // тактика в реале

    private String value;

    TypeStrategy(String title) {
        this.value = title;
    }

    public static TypeStrategy valueOfLabel(String label){
        for(TypeStrategy e:values()){
            if (e.value.equals(label)){return e;}
        }
        return null;
    }
    public static String print(){
        //System.out.print("типы стратегий:{ ");
        String s="{ ";
        for(TypeStrategy e:values()){
            s = s + e.value + ((e == TypeStrategy.RTT) ? " }": ", ");
        }
        return s;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "вид стратежки {" +
                "title='" + value + '\'' +
                '}';
    }
}
