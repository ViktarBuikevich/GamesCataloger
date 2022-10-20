public enum TypeShooter {
    UNKNOWN("неопределено"),
    FIRST_PERSON("от первого лица"),
    THERD_PERSON("от третьего лица");
    private final String value;

    TypeShooter(String title) {
        this.value = title;
    }

    public static TypeShooter valueOfLabel(String label){
        for(TypeShooter e:values()){
            if (e.value.equals(label)){return e;}
        }
        return null;
    }
    public static String print(){
        //System.out.print("типы шутера:{ ");
        String s="{ ";
        for(TypeShooter e:values()){
            s = s + e.value + ((e == TypeShooter.THERD_PERSON) ? " }": ", ");
        }
        return s;
    }
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "вид шутера {" +
                "title='" + value + '\'' +
                '}';
    }
}
