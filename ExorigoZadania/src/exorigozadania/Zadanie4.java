package exorigozadania;

/*
Zadanie 4
Celem zadania jest napisanie funkcji sprawdzającej, czy podany tekst jest kodem kreskowym EAN-13 lub EAN-8.

Na wejściu funkcji są dwa parametry:
- wejściowy kod kreskowy: parametr tekstowy
- rodzaj kodu kreskowego - parametr numeryczny: 1 dla EAN-8, 2 dla EAN-13.

Niektóre towary (np. czasopisma) mają dodatkowe kody (tzw. add-on'y) - należy mieć na uwadze, że skaner może dokleić je bezpośrednio do właściwego kodu kreskowego 
(np. dla towaru o kodzie "6920702707721" oraz add-on’ie "12" na wejściu możliwy jest ciąg "692070270772112"). 
Należy założyć, że add-on'y mogą występować zarówno dla kodów EAN-8 jaki i EAN-13.

Należy mieć na uwadze, że niektóre skanery kodów kreskowych mogą wycinać z kodu kreskowego pierwsze wiodące zero (np. zamiast kodu "0075678164125" przesyłają "075678164125").

Na wyjściu funkcja powinna zwracać prawidłowy kod kreskowy (o długości 8 lub 13 znaków) bez ewentualnego add-on'u.
Ewentualne błędy w danych wejściowych powinny być sygnalizowane wyjątkami.
Informacje na temat kodów kreskowych: http://pl.wikipedia.org/wiki/EAN
*/

class WrongBarcodeTypeException extends Exception {
    WrongBarcodeTypeException(String msg) { super(msg); }
};

class IllegalParameterException extends Exception {
    IllegalParameterException(String msg) { super(msg); }
};

public class Zadanie4 {
    
    //type = 1 - EAN-8
    //type = 2 - EAN-13
    public static String func(String barcode, int barcodeType) throws WrongBarcodeTypeException, IllegalParameterException {
        int digit;
        int controlSum = 0;
        
        if(barcode == null) throw new IllegalParameterException("Pierwszy parametr jest niepoprawny!");
        if(barcode.length() <= 0) throw new IllegalParameterException("Podano pusty ciąg znaków!");
        if(barcodeType != 1 && barcodeType != 2) throw new WrongBarcodeTypeException("Typ kodu kreskowego nie może być inny niż 1 lub 2!");
        if(barcodeType == 1 && barcode.length() < 7) throw new IllegalParameterException("Podany kod kreskowy jest zbyt krótki dla podanego typu!");
        if(barcodeType == 2 && barcode.length() < 12) throw new IllegalParameterException("Podany kod kreskowy jest zbyt krótki dla podanego typu!");
        if(!barcode.matches("\\d+")) throw new IllegalParameterException("Podany kod zawiera znaki nieliczbowe!");
        
        switch(barcode.length()) {
            case 7: return "0" + barcode; //EAN-8 bez pierwszego zera
            case 8: return barcode; //EAN-8
            case 9: return "0" + barcode.substring(0, barcode.length() - 2); //EAN-8 bez pierwszego zera i z dwucyfrowym add'onem
            case 10: return barcode.substring(0, barcode.length() - 2); //EAN-8 z dwucyfrowym add'onem
            
            case 12: return "0" + barcode; //EAN-13 bez pierwszego zera
            
            case 13: //EAN-13 lub EAN-8 z pięciocyfrowym add'onem
                
                //sprawdzenie czy kod jest poprawnym kodem EAN-8 z pięciocyfrowym add'onem
                for(int i = 0; i < barcode.length() - 6; i++) {
                    digit = Integer.parseInt("" + barcode.charAt(i));
                    if(i % 2 == 0) digit *= 3;
                    controlSum += digit;
                }
                
                //sprawdzenie sumy kontrolnej i zwrócenie poprawnego kodu EAN-8
                if(10 - (controlSum % 10) == Integer.parseInt("" + barcode.charAt(barcode.length() - 1)) ) return barcode.substring(0, barcode.length() - 5);
                else return barcode; //zwrócenie poprawnego kodu EAN-13
                          
            case 14: return "0" + barcode.substring(0, barcode.length() - 2); //EAN-13 bez pierwszego zera i z dwucyfrowym add'onem
            case 15: return barcode.substring(0, barcode.length() - 2); //EAN-13 z dwucyfrowym add'onem
            case 17: return "0" + barcode.substring(0, barcode.length() - 5); //EAN-13 bez pierwszego zera i z pięciocyfrowym add'onem
            case 18: return barcode.substring(0, barcode.length() - 5); //EAN-13 z pięciocyfrowym add'onem
            
            default: throw new IllegalParameterException("Niepoprawna ilość znaków w kodzie!");
        } //switch   
    }   
};
