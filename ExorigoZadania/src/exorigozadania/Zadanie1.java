package exorigozadania;

/*
Zadanie 1
Napisz program, który wypisuje liczby od 1 do 100. Ale dla wielokrotności trójki wyświetl "Fizz" zamiast liczby oraz dla wielokrotności piątki wyświetl "Buzz". 
Dla liczb będących wielokrotnościami trójki oraz piątki wyświetl "FizzBuzz".
*/

public class Zadanie1 {
    public static void func() {
        String s;
        
        for(int i = 1; i <= 100; i++) {
            s = "";
            if(i % 3 == 0) s = "Fizz";
            if(i % 5 == 0) s += "Buzz";
            
            s = (s.length() == 0) ? "\n" + i : "\n" + s;
            System.out.print(s);
        }
    }
};
