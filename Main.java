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


/*
*0
Count Snow: 100000.0
Count Rain 0
P Snow: 1.0
P Rain: 0.0
P Teor Snow: 1.0
P Teor Rain: 0.0
1
Count Snow: 70132.0
Count Rain 29868
P Snow: 0.70132
P Rain: 0.29867999999999995
P Teor Snow: 0.7
P Teor Rain: 0.30000000000000004
2
Count Snow: 52025.0
Count Rain 47975
P Snow: 0.52025
P Rain: 0.47975
P Teor Snow: 0.5199999999999999
P Teor Rain: 0.4800000000000001
3
Count Snow: 40855.0
Count Rain 59145
P Snow: 0.40855
P Rain: 0.59145
P Teor Snow: 0.4119999999999999
P Teor Rain: 0.5880000000000001
4
Count Snow: 35053.0
Count Rain 64947
P Snow: 0.35053
P Rain: 0.64947
P Teor Snow: 0.34719999999999995
P Teor Rain: 0.6528
5
Count Snow: 30682.0
Count Rain 69318
P Snow: 0.30682
P Rain: 0.69318
P Teor Snow: 0.3083199999999999
P Teor Rain: 0.6916800000000001
6
Count Snow: 28332.0
Count Rain 71668
P Snow: 0.28332
P Rain: 0.71668
P Teor Snow: 0.2849919999999999
P Teor Rain: 0.7150080000000001
* */