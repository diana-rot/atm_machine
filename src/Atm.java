import java.util.ArrayList;
import java.util.List;

public class Atm implements Subject {

    private static Atm single_instance = null;
    List<Observer> banks = new ArrayList<>();
    int[] notes;
    int[] noteFrequency;
    int nr_notes;
    enum Stock_alert {
        WARNING_100,
        WARNING_50,
        CRITICAL_100,
        O_STOCK,
        DEFAULT
    }

    private Atm()
    {
        notes = new int[]{100, 50, 10, 5, 1 };
        noteFrequency = new int []{50, 50, 100, 100, 100};
        nr_notes = 5;

    }

    public static Atm getInstance()
    {
        if (single_instance == null)
            single_instance = new Atm();

        return single_instance;
    }

    public int[] getNoteFrequency(){
        return noteFrequency;
    }
    public int[] getNotes(){
        return notes;
    }

    public int check_stock_notes( int nr_notes, int[] noteFrequency){
        int left_nr_Notes = 0;
        for (int i = 0; i < nr_notes; i++) {
            left_nr_Notes += noteFrequency[i];
        }
        if (left_nr_Notes < 10) {
            if ( left_nr_Notes == 0)
                System.out.println("0!!!" +  left_nr_Notes);
            return  left_nr_Notes;
        }
        return  left_nr_Notes;
    }

    public static void printCurrency(int nr_notes, int[] noteCounter, int[] noteFrequency, int[] notes){
        System.out.println("Notes Count ->");
        for (int i = 0; i < nr_notes; i++) {
            if (noteCounter[i] != 0 ) {
                System.out.println(notes[i] + " : "
                        + noteCounter[i]);

                System.out.println("left cash amount:" + noteFrequency[i] +"->" + notes[i]);
            }
        }
    }
    public static void countCurrency(int amount, int nr_notes, int[] notes, int[] noteFrequency)
    {
        int[] noteCounter = new int[5];
        int verify_nr;
        // count notes using Greedy approach
        for (int i = 0; i < nr_notes; i++) {
            if (amount >= notes[i]) {
                verify_nr = amount / notes[i];
                if (noteFrequency[i] - verify_nr > 0) {
                    noteCounter[i] = amount / notes[i];
                    amount = amount - noteCounter[i] * notes[i];
                    noteFrequency[i] -= noteCounter[i];
                } else if(noteFrequency[i] > 0 ){
                    while(noteFrequency[i] != 0){
                        amount = amount -  notes[i];
                        noteFrequency[i] --;
                        noteCounter[i] ++;
                    }
                }
            }

        }
        printCurrency(nr_notes, noteCounter, noteFrequency, notes);
    }

    @Override
    public void addSubscriber(Observer bank) {
        //banca cu adresa de email ce trebuie notificata
        banks.add(bank);
    }

    @Override
    public void removeSubscriber(Observer bank) {
        banks.remove(bank);
    }

    @Override
    public void notifySubscribers() {
        //pentru a notifica emailul
        //3 tipuri stock_alert

        Stock_alert message = Stock_alert.DEFAULT;

        if( check_stock_notes( nr_notes, noteFrequency) == 0) {
            message = Stock_alert.O_STOCK;
        }else {
            if (noteFrequency[0] < 20 && noteFrequency[0] >= 10) {
                //bancnotele de 100 scad sub 20
                message = Stock_alert.WARNING_100;

            } else if (noteFrequency[0] < 10) {
                message = Stock_alert.CRITICAL_100;
            }
            if (noteFrequency[1] < 8) {
                message = Stock_alert.WARNING_50;
            }
        }
            for(Observer bank:banks) {
                bank.update(message);
            }


    }
}
