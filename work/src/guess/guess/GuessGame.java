package guess;

import java.util.Random;
import java.util.Scanner;

class GuessGame {
  public static void main(String[] args) {
    // 実行時の機能紹介
    System.out.println("数当てゲーム");

    // システムがランダムで1-10の数字を選択する。
    Random random = new Random();
    int num = random.nextInt(10) + 1;
    System.out.println(num);

    // 標準入力を受け付ける
    Scanner scanner = new Scanner(System.in);
    String str = scanner.next();
    System.out.println("標準入力で受け取った値：" + str);

    // 数値だけを受け取るための処理
    int ans = 0;
    try{
      ans = Integer.parseInt(str);
    } catch (NumberFormatException ex) {
      System.out.println("数値以外が入力されました。終了します。");
      System.exit(-1);
    }
    System.out.println(ans);
    

    // 最終処理としてクローズ。
    scanner.close();
  }
}