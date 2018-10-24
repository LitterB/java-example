package example;

/**
 * @Author: Sunshine
 * @Description:
 * @Date: Create in 2018/10/24 14:25
 */
public class ThreadDemo{

    private String str;

    public static void main(String[] args) {
        ThreadDemo threadDemo = new ThreadDemo();
        try {
            threadDemo.str = "Hello 1024 !";
            subThread(threadDemo);
        }catch (Exception e){
            System.out.println("主线程出现异常了");
        }finally{
            System.out.println("主线程 ----》" + threadDemo.str );
        }

    }

    private static void subThread(ThreadDemo threadDemo){
        System.out.println("subThread start");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    int a = 10;
                    int b = 0;
                    int c = a / b;
                    System.out.println(c);
                }catch (Exception e){
                    threadDemo.str = "Hello world";
                    System.out.println("子线程出异常了----");
                    e.printStackTrace();
                }finally {
                    System.out.println("子线程 ---- 》 " + threadDemo.str);
                }
            }
        }).start();
        System.out.println("subThread end");
    }
}
