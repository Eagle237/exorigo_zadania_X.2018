package exorigozadania;

import java.util.Random;

/*
Zadanie 3
Napisz program symulujący grę w kości. W grze bierze udział dwóch graczy o nazwach: pierwszy, drugi.
Każdemu graczowi przypada 5 tur. Tury graczy odbywają się naprzemiennie tzn. grę zaczyna gracz pierwszy, po jego turze następuje tura gracza drugiego, następna jest tura gracza pierwszego itd. 
Grę zawsze rozpoczyna gracz pierwszy. Podczas każdej tury gracz zbiera punkty karne według zasad opisanych poniżej. Suma punktów każdego gracza przed rozpoczęciem gry wynosi zero. 
Wygrywa gracz, który zbierze mniejszą liczbę punktów.

W każdej turze, gracz wykonuje maksymalnie 10 rzutów dwoma kośćmi jednocześnie. Możliwe wyniki rzutu jedną kością to jedna z następujących cyfr {1, 2, 3, 4, 5, 6}.

Zasady gry:
1. Jeżeli w pierwszym rzucie tury gracz uzyska sumę oczek z obu kości równą 7 lub 11, wygrywa turę przed czasem (rozpoczyna się tura przeciwnika).
2. Jeżeli gracz w pierwszym rzucie tury uzyska sumę oczek z obu kości równą 2 lub 12, przegrywa turę przed czasem (rozpoczyna się tura przeciwnika). Do punktów gracza doliczana jest maksymalna możliwa liczba punktów karnych za turę (tyle, ile by uzyskał w najbardziej pesymistycznym przebiegu swojej tury).
3. Jeżeli gracz uzyska sumę oczek z obu kości równą 5, wygrywa turę przed czasem (rozpoczyna się tura przeciwnika).
4. Jeżeli gracz uzyska sumę oczek z obu kości inną niż opisane powyżej, do jego punktów doliczona zostaje suma oczek uzyskanych w danym rzucie podzielona przez numer rzutu w danej turze.

Po zakończeniu wszystkich tur obydwu graczy na standardowe wyjście zostaje wypisany komunikat o sumie punktów zdobytych przez każdego z graczy oraz informacja o zwycięzcy.
*/

public class Zadanie3 {
    Random rand = new Random();
    boolean turn = true; //true = pierwszy, false = drugi
    float scorePlayer1 = 0.0f; //punkty karne gracza pierwszego
    float scorePlayer2 = 0.0f; //punkty karne gracza drugiego
    int turnCounter = 0; //licznik tur
    int tossCounter = 0; //licznik rzutów
    int dice1 = 0; //ilość oczek na kostce pierwszej
    int dice2 = 0; //ilość oczek na kostce drugiej
    
    void toss() { //metoda wykonująca rzut dwiema kostkami        
        dice1 = Math.abs(rand.nextInt()) % 6 + 1;
        dice2 = Math.abs(rand.nextInt()) % 6 + 1;
    }
    
    void turn() { //metoda wykonująca turę
        float score = 0;
        
        do {
            toss();
            tossCounter++;
            
            if(tossCounter == 1 && (dice1 + dice2 == 7 || dice1 + dice2 == 11)) break; //wygrana w pierwszym rzucie
            
            if(tossCounter == 1 && (dice1 + dice2 == 2 || dice1 + dice2 == 12)) { //przegrana w pierwszym rzucie i doliczenie maksymalnej liczby punktów karnych za wszystkie 10 rzutów
                score += 10; //10 karnych punktów za pierwszy rzut
                for(int i = 2; i <= 10; i++) score += (float) (12 / i); //punkty karne za kolejne 9 rzutów

                tossCounter = 10;
            }
            
            if(dice1 + dice2 == 5) break; //wygrana
            else { //doliczenie punktów za rzut
                score += (float) (dice1 + dice2) / tossCounter;
            } 
        } while(tossCounter < 10);
        
        //doliczenie punktów za wszystkie rzuty w turze
        if(turn) scorePlayer1 += score;
        else scorePlayer2 += score;
        
        turn = !turn;
        tossCounter = 0;
    }
    
    public static void main(String[] args) {
        Zadanie3 z3 = new Zadanie3();
        
        do {
            z3.turnCounter++;
            z3.turn();
        } while(z3.turnCounter < 10);
        
        System.out.println("Suma punktów karnych gracza 1:\t" + z3.scorePlayer1);
        System.out.println("Suma punktów karnych gracza 2:\t" + z3.scorePlayer2);
        if(z3.scorePlayer1 < z3.scorePlayer2) System.out.println("Zwyciężył gracz 1");
        else if(z3.scorePlayer1 == z3.scorePlayer2) System.out.println("Remis");
        else System.out.println("Zwyciężył gracz 2");
    }
};
