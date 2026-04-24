package part1.part1_1;

public class NumberClassifiers {

 public static String calssify(int number){

     if (number<0) return "<0";
     if (number==0) return "=0";
     if (number<=9) return "<=9";
     if (number<=99) return "<=99";
     if (number<=999) return "<=999";
     return "big";
 }
public static void main (String[] args){
     //
    int[] samples = {-5, 0, 7, 42, 100, 1000, -999};
    for (int n : samples){
        System.out.println(n+ "->"+calssify(n));
    }
}
}
