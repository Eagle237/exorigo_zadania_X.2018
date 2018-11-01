package exorigozadania;

/*
Zadanie 2
Napisz funkcję sprawdzającą poprawność daty w latach 2001-2099 (daty spoza tego okresu uznaj za niepoprawne).
Wejście – trzy parametry liczbowe (dzień, miesiąc, rok).
Wyjście – parametr logiczny (true – data poprawna, false – data niepoprawna) .
Proszę zaimplementować własny algorytm kontroli – nie wolno korzystać z gotowych rozwiązań, np. LocalDate, Calendar, itp.
*/

public class Zadanie2 {
    public static boolean func(int day, int month, int year) {
        if(year < 2001 || year > 2099) return false;
        if(month < 1 || month > 12) return false;
        if(day < 1) return false;
        
        switch(month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                if(day > 31) return false;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                if(day > 30) return false;
                break;
            case 2:
                if(year % 4 == 0) {
                    if(day > 29) return false;
                } else {
                    if(day > 28) return false;
                }
                break;
        } //switch
        
        return true;
    }
};
