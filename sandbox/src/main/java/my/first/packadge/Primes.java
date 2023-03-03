package my.first.packadge;

public class Primes {
    public static boolean isPrime(int n) {
//просто число: делится только на себя и на единицу - true
// непростое число - false
        for (int i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
        }
    public static boolean isPrimeWhile(int n){
    int j = 2;//инициализация переменной до while
        while (j < n) {//условие
            if (n % j == 0) {//можно избавиться от конструкции if, перенеся её в условие, смотри ниже
                return false;
            }
            j++;//действие, которое двигает счётчик
        }
        return true;
    }

    public static boolean isPrimeWhile1(int n){
        int j = 2;//инициализация переменной до while
        while (j < n && n % j != 0) {//условие
            j++;//действие, которое двигает счётчик
        }
        return j == n;//за время выплнения цикла j ни на что не поделилось и стало = n, значит, число простое
    }
    public static boolean isPrime(long n) {

        for (long i = 2; i < n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static boolean isPrimeFast(int n) {
//просто число: делится только на себя и на единицу - true. Изменено условие i < n; на i < n/2; так как нацело число всё равно может делиться максимум пополам
// непростое число - false
        int m = (int) Math.sqrt (n);
//        for (int i = 2; i < n/2; i++) {
        for (int i = 2; i < m; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}