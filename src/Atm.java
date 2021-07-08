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

    private Atm() {
        notes = new int[]{100, 50, 10, 5, 1};
        noteFrequency = new int[]{50, 50, 100, 100, 100};
        nr_notes = 5;

    }

    public static Atm getInstance() {
        if (single_instance == null)
            single_instance = new Atm();

        return single_instance;
    }

    public int[] getNoteFrequency() {
        return noteFrequency;
    }

    public int[] getNotes() {
        return notes;
    }

    public int check_stock_notes(int nr_notes, int[] noteFrequency) {
        int left_nr_Notes = 0;
        for (int i = 0; i < nr_notes; i++) {
            left_nr_Notes += noteFrequency[i];
        }
        return left_nr_Notes;
    }

    public static void printCurrency(int nr_notes, int[] noteCounter, int[] noteFrequency, int[] notes) {
        System.out.println("Notes Count ->");
        for (int i = 0; i < nr_notes; i++) {
            if (noteCounter[i] != 0) {
                System.out.println(notes[i] + " : "
                        + noteCounter[i]);

                System.out.println("left cash amount:" + noteFrequency[i] + "->" + notes[i]);
            }
        }
    }

    public static void countCurrency(int amount, int nr_notes, int[] notes, int[] noteFrequency) {
        int[] noteCounter = new int[5];
        int verify_nr;
        for (int i = 0; i < nr_notes; i++) {
            if (amount >= notes[i]) {
                verify_nr = amount / notes[i];
                if (noteFrequency[i] - verify_nr > 0) {
                    noteCounter[i] = amount / notes[i];
                    amount = amount - noteCounter[i] * notes[i];
                    noteFrequency[i] -= noteCounter[i];
                } else if (noteFrequency[i] > 0) {
                    while (noteFrequency[i] != 0) {
                        amount = amount - notes[i];
                        noteFrequency[i]--;
                        noteCounter[i]++;
                    }
                }
            }

        }
        printCurrency(nr_notes, noteCounter, noteFrequency, notes);
    }

    @Override
    public void addSubscriber(Observer bank) {
        //the bank that need to do the refill (the email)
        banks.add(bank);
    }

    @Override
    public void removeSubscriber(Observer bank) {
        banks.remove(bank);
    }

    @Override
    public void notifySubscribers() {
        //for notifying the banks
        int index_note_100 = 0;
        int index_note_50 = 1;
        int percent_20 = 20;
        int percent_10 = 10;
        int percent_15 = 8;

        Stock_alert message = Stock_alert.DEFAULT;

        if (check_stock_notes(nr_notes, noteFrequency) == 0) {
            message = Stock_alert.O_STOCK;
        } else {
            if (noteFrequency[index_note_100] < percent_20 && noteFrequency[index_note_100] >= percent_10) {
                //the notes of 100 drop under 20 percent
                message = Stock_alert.WARNING_100;

            } else if (noteFrequency[index_note_100] < percent_10) {
                message = Stock_alert.CRITICAL_100;
            }
            if (noteFrequency[index_note_50] < percent_15) {
                message = Stock_alert.WARNING_50;
            }
        }
        for (Observer bank : banks) {
            bank.update(message);
        }


    }
}
