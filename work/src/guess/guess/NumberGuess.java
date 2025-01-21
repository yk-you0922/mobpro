package guess;

import java.util.Random;
import java.util.Scanner;

/**
 * 数字当てゲームクラス
 */
class NumberGuess {

  // 回答回数
  private static int ansCnt = 0;
  // 最小選択可能外数値
  private static final int MIN_NUM = 1;

  public static void main(String args[]) {

    // ゲームの説明
    System.out.println("############################");
    System.out.println("このゲームは数値を当てるゲームです。");
    System.out.println("システムが1からユーザーが入力した数値までの数字をランダムで選択します。");
    System.out.println("プレイヤーはユーザーが選択した数字からランダムで決定された数値を何回で当てられるかを競います。");
    System.out.println("############################");

    // 標準入力を受け取るクラス
    Scanner rangeScan = new Scanner(System.in);
    Scanner ansScan = new Scanner(System.in);

    // 数値の範囲を入力する
    int maxRange = inputRange(rangeScan);
    System.out.println(maxRange);

    // システムがランダムな数値を選択する
    Random rand = new Random();
    int answer = rand.nextInt(maxRange) + 1;

    // 標準入力を受け取る
    int guessNum = inputAnswer(ansScan, maxRange);

    // 判定:正解するまで入力と判定を繰り返す。
    while (true) {
      ansCnt++;
      if (answer < guessNum) {
        System.out.println("残念！あなたが選んだ数字はシステムが選んだ数値よりも大きいです。");
        // System.out.println("デバッグ用回答" + String.valueOf(answer));
        System.out.println("あなたが選んだ数字：" + String.valueOf(guessNum));
        System.out.println("回答回数：" + String.valueOf(ansCnt));
        guessNum = inputAnswer(ansScan, maxRange);
      } else if (answer > guessNum) {
        System.out.println("残念！あなたが選んだ数字はシステムが選んだ数値よりも小さいです。");
        // System.out.println("デバッグ用回答" + String.valueOf(answer));
        System.out.println("あなたが選んだ数字：" + String.valueOf(guessNum));
        System.out.println("回答回数：" + String.valueOf(ansCnt));
        guessNum = inputAnswer(ansScan, maxRange);
      } else {
        System.out.println("正解です！");
        System.out.println("あなたが選んだ数字：" + String.valueOf(guessNum));
        System.out.println("正解までにかかった回数：" + String.valueOf(ansCnt));
        break;
      }
    }

    // 終了処理
    rangeScan.close();
    ansScan.close();

    System.out.println("----------------------------------------");
    System.out.println("ゲームを終了します。");
  }

  /**
   * ユーザー選択の回答を標準入力で受け取る
   * 
   * @param scan Scanクラス
   * @return 入力を変換した数値。もしくは再帰処理。
   */
  private static int inputRange(Scanner scan) {
    System.out.println("1以上の数値を半角で入力してください。");

    String selectNumStr = scan.nextLine();

    // 入力判定：空文字・Nullを許容しない
    if (selectNumStr.isEmpty()) {
      // 再度入力を求める
      return inputRange(scan);
    }

    try {
      int selectNum = Integer.parseInt(selectNumStr);
      if (selectNum < MIN_NUM) {
        System.out.println("1以上の数値を設定してください");
        return inputRange(scan);
      }
      return selectNum;
    } catch (NumberFormatException ex) {
      // 数値以外の場合は再度入力を求める
      return inputRange(scan);
    }
  }

  /**
   * ユーザー選択の回答を標準入力で受け取る
   * 
   * @param scan    Scanクラス
   * @param MAX_NUM 開始時に選択した数値範囲
   * @return 入力を変換した数値。もしくは再帰処理。
   */
  private static int inputAnswer(Scanner scan, final int MAX_NUM) {
    System.out.println("1から" + String.valueOf(MAX_NUM) + "までの数値を半角で入力してください。");

    String guessStr = scan.nextLine();

    // 入力判定：空文字・Nullを許容しない
    if (guessStr.isEmpty()) {
      // 再度入力を求める
      return inputAnswer(scan, MAX_NUM);
    }

    try {
      int guessNum = Integer.parseInt(guessStr);
      // 入力判定：指定数値範囲以上の数値を許容しない
      if (guessNum > MAX_NUM) {
        // 再度入力を求める
        return inputAnswer(scan, MAX_NUM);
      }
      return guessNum;
    } catch (NumberFormatException ex) {
      // 数値以外の場合は再度入力を求める
      return inputAnswer(scan, MAX_NUM);
    }
  }
}