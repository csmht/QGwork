import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        int n = 0;
        Prin.Cread();
        boolean dl = false;
        while (!dl) {

            try {
                n = scanner.nextInt();
                if (n == 1) {
                    dl = Text.Stuin();
                } else if (n == 2) {
                    dl = Text.Guanin();
                } else if (n == 3) {
                    Text.zc();
                    System.out.println("注册成功");
                    scanner.nextLine();
                    Prin.Cread();
                } else if (n == 4) {
                    return;
                }
            } catch (Exception ignored) {
                scanner.nextLine();

            }finally {
                if(n!=3){System.out.println("输入错误，请重新输入");}


            }
        }
        scanner.nextLine();
        try {
            while (dl) {
                if (n == 1) {
                    dl = StudenWork.SW();
                } else {
                    dl = GuanLi.Guanli();
                }
                System.out.println("输入回车继续.....");
                scanner.nextLine();
            }


        }catch (Exception ignored) {

        }
    }


}


