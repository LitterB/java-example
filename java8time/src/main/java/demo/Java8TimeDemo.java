package demo;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/11/1 10:05
 */
public class Java8TimeDemo {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    private static void test1(){
        long sum = 0L;
        long start = Instant.now().getEpochSecond();
        stdout(start);
        stdout(System.currentTimeMillis());
        for (int i = 0; i < 10000; i++){
            sum += i;
        }
        long end = Instant.now().getEpochSecond();
        stdout("--------------->" + sum + "-------------------------->" + (end - start));
    }

    private static void test2(){
        int start = Instant.now().getNano();
        stdout("Instant now getNano---->" + start);
    }

    private static void test3(){
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = LocalDate.now();
        stdout(now.getSecond());
        stdout(today.getDayOfYear());
    }

    private static void test4(){
        LocalDate today = LocalDate.now();
        LocalDate nextDay = today.plusDays(1L);
        LocalDate nextYear = today.withYear(2019);
        LocalDate nextMonth = today.withMonth(12);
        stdout("test4 1-----> " + today.getDayOfYear());
        stdout("test4 2-----> " + today.getMonth());
        stdout("test4 3-----> " + nextDay.getDayOfYear());
        stdout("test4 4-----> " + nextYear.getYear());
        stdout("test4 5-----> " + nextMonth.getYear());
        stdout("test4 6-----> " + nextYear.getMonth());
    }


    private static void stdout(String message){
        System.out.println(message);
    }

    private static void stdout(long message){
        System.out.println(message);
    }
}
