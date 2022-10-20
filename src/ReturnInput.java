import java.util.Scanner;

public class ReturnInput {
    public boolean isCansel;
    public String valueInput;

    public ReturnInput() {
        this.isCansel=true;
        this.valueInput="";
    }

    public ReturnInput(boolean isCansel, String valueInput) {
        this.isCansel=isCansel;
        this.valueInput=valueInput;
    }
    public ReturnInput inputValue(String title){
        Scanner in = new Scanner(System.in);
        String strIn;
        do {
            System.out.println(title);
            strIn = in.nextLine().trim();
            /// Можно выполнить проверку по другому ???
            /// if((strIn.isEmpty() | strIn.isBlank()) & isCanselInput()) {return;} else {continue;}
            /// т.е. Если не выполняется первое условие то не переходим к вызову isCanselInput()
            if(strIn.isEmpty() || strIn.isBlank()) {
                if (Main.isCanselInput()) {
                    return new ReturnInput(true,"");
                } else {
                    System.out.println("Повторите ввод!");
                    continue;
                }
            }
            return new ReturnInput(false,strIn);
        }while(true);


    }
}
