public enum TypeRPG {
    UNKNOWN("неопределено"),
    NARRATIVE("повествование"), //повествование
    SANDBOX("песочниц"), //ПЕСОЧНИЦА
    DUNGEON_CRAWLER("зачистка подземелий"); //зачистка подземелий
    private final String value;

    TypeRPG(String title) {
        this.value = title;
    }
    public static TypeRPG valueOfLabel(String label){
        for(TypeRPG e:values()){
            if (e.value.equals(label)){return e;}
        }
        return null;
    }
    public static String print(){
        //System.out.print("типы игр:{ ");
        String s="{ ";
        for(TypeRPG e:values()){
            s = s + e.value + ((e == TypeRPG.DUNGEON_CRAWLER) ? " }": ", ");
        }
        return s;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "вид РПГ-шки {" +
                "title='" + value + '\'' +
                '}';
    }
}
