import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args){
        Atm atm_count = Atm.getInstance();
        Observer bank1 = new Bank();
        atm_count.addSubscriber(bank1);
        int sum_desired;

        while(true) {
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                sum_desired = Integer.parseInt(br.readLine());
                Atm.countCurrency(sum_desired,5, atm_count.getNotes(), atm_count.getNoteFrequency());
            } catch (Exception e){
                e.printStackTrace();
            }
            atm_count.notifySubscribers();
            if( atm_count.check_stock_notes( atm_count.nr_notes, atm_count.noteFrequency) == 0) {
                System.out.println("stock 0 of notes, I am waiting for a refill");
              break;
            }

        }

    }
}

