import java.util.Random;

public class Main {

    public static int s = 1;
    public static int r = 2;
    public static double P00 = 0.7, P01 = 0.3;
    public static double P10 = 0.1, P11 = 0.9;


    public static void main(String args[]){

        int n = 7;
        for(int i = 0; i < n; i++) {
            System.out.println(i);
            double a = start(i);
            double b = 1 - a;
            //state(true, i);
            System.out.println("P Snow: " + a);
            System.out.println("P Rain: " + b);

            double teorSnow = teor(i);
            System.out.println("P Teor Snow: " + teorSnow);
            double teorRain = 1 - teorSnow;
            System.out.println("P Teor Rain: " + teorRain);
        }
    }


    public static int state(boolean print, int n){

        int curr = s;

        Random random = new Random();

        if(print) {
            System.out.println(curr);
        }

        for(int i = 0; i < n; i++){

            if(curr == s) {
                if (random.nextFloat() < P01) {
                    curr = r;
                } else {
                    curr = s;
                }
            }else if(curr == r) {
                if (random.nextFloat() < P10) {
                    curr = s;
                } else {
                    curr = r;
                }
            }

            if(print) {
                System.out.println(curr);
            }
        }

        return curr;
    }

    public static double start(int n){
        double count = 100000;
        double countS = 0;
        int countR = 0;
         for (int i = 0; i < count; i++){
             int tmp = state(false, n);
             if(tmp == s){
                 countS++;
             }else{
                 countR++;
             }
         }

         System.out.println("Count Snow: " + countS);
         System.out.println("Count Rain " + countR);

         return countS/count;
    }

    public static double teor(int n){

        double prevPSnow = 1;
        double nextPSnow;
        double prevPSRain = 0;
        double nextPRain;
        for(int i = 0; i < n; i++){
            nextPSnow = P00*prevPSnow + P10*prevPSRain;
            nextPRain = P11*prevPSRain + P01*prevPSnow;
            prevPSnow = nextPSnow;
            prevPSRain = nextPRain;
        }
        return prevPSnow;
    }


}
