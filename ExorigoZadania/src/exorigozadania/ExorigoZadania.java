package exorigozadania;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ExorigoZadania {
    public static void main(String[] args) {
        try {
            System.out.print(Zadanie4.func("1234567891234567", 2));
        } catch (WrongBarcodeTypeException | IllegalParameterException ex) {
            Logger.getLogger(ExorigoZadania.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
