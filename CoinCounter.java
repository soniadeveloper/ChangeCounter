import java.util.Scanner;

public class CoinCounter {
  private int cents;
  private double pennies;
  private double quarters;
  private double nickels;
  private double dimes;

  public int numQuarters(int cents) {
    int quarts;
    if (cents < 25) {
      quarts = 0;
    }
    quarts = cents / 25;
    return quarts;
  }

  public int numDimes(int cents) {
    int dim;
    if (cents < 10 || cents % 25 < 10) {
      dim = 0;
    }
    else {
      dim = (cents % 25) / 10;
    }
    return dim;
  }

  public int numNickels(int cents) {
    int nicks;
    if (cents < 5 || cents % 10 == 0 || cents % 25 == 0 || cents % 25 < 5) {
      nicks = 0;
    }
    else if (cents - ((numQuarters(cents) * 25) + (numDimes(cents) * 10)) < 5) {
      nicks = 0;
    }
    else {
      nicks = (cents % 25) / 5;
    }
    return nicks;
  }

  public int numPennies(int cents) {
    int pens;
    if (cents == 0 || cents % 5 == 0) {
      pens = 0;
    }
    else {
      pens = cents % 5;
    }
    return pens;
  }

  public CoinCounter(int cents) {
    pennies = numPennies(cents);
    nickels = numNickels(cents);
    dimes = numDimes(cents);
    quarters = numQuarters(cents);
  }

  public static String qWord(CoinCounter n) {
    String word;
    if (n.quarters == 1) {
      word = " quarter";
    }
    else {
      word = " quarters";
    }
    if (n.dimes == 0 && n.nickels == 0 && n.pennies == 0) {
      word = word + ".";
    }
    else if ((n.dimes == 0 && n.nickels == 0) || (n.dimes == 0 && n.pennies == 0) || (n.nickels == 0 && n.pennies == 0)) {
      word = word;
    }
    else {
      word = word + ",";
    }
    return word;
  }

  public static String len(String a) {
    int len = a.length();
    String inte = a.substring(0, len-2);
    return inte;
  }

  public static String qPhrase(CoinCounter n) {
    String phr;
    String numnot = "" + n.quarters;
    String inte = len(numnot);
    if (n.quarters == 0) {
      phr = "";
    }
    else {
      phr = "" + inte + qWord(n);
    }
    return phr;
  }

  public static String dWord(CoinCounter n) {
    String word;
    if (n.dimes == 1) {
      word = " dime";
    }
    else {
      word = " dimes";
    }
    if (n.nickels == 0 && n.pennies == 0) {
      word = word + ".";
    }
    else {
      word = word + ",";
    }
    return word;
  }

  public static String dPhrase(CoinCounter n) {
    String phr;
    String numnot = "" + n.dimes;
    String inte = len(numnot);
    if (n.dimes == 0) {
      phr = "";
    }
    else if (n.nickels == 0 && n.pennies == 0){
      phr = " and " + inte + dWord(n);
    }
    else {
      phr = " " + inte + dWord(n);
    }
    return phr;
  }

  public static String nWord(CoinCounter n) {
    String word;
    if (n.nickels == 1) {
      word = " nickel";
    }
    else {
      word = " nickels";
    }
    if (n.pennies == 0) {
      word += ".";
    }
    else if (n.quarters == 0 && n.dimes == 0) {
      word = word;
    }
    else {
      word += ",";
    }
    return word;
  }

  public static String nPhrase(CoinCounter n) {
    String phr;
    String numnot = "" + n.nickels;
    String inte = len(numnot);
    if (n.nickels == 0) {
      phr = "";
    }
    else if (n.pennies == 0){
      phr = " and " + inte + nWord(n);
    }
    else {
      phr = " " + inte + nWord(n);
    }
    return phr;
  }

  public static String pWord(CoinCounter n) {
    if (n.pennies != 1) {
      return " pennies.";
    }
    else {
      return " penny.";
    }
  }

  public static String pPhrase(CoinCounter n) {
    String phr;
    String numnot = "" + n.pennies;
    String inte = len(numnot);
    if (n.pennies == 0) {
      phr = "";
    }
    if (n.quarters == 0 && n.dimes == 0 && n.nickels == 0) {
      phr = "" + inte + pWord(n);
    }
    else {
      phr = " and " + inte + pWord(n);
    }
    return phr;
  }

  public static void main(String[] args) {
    System.out.println("Enter the amount of change that you must give: ");
    Scanner inp = new Scanner(System.in);
    int num = inp.nextInt();
    CoinCounter ex = new CoinCounter(num);

    System.out.println("You should give " + qPhrase(ex) + dPhrase(ex) + nPhrase(ex) + pPhrase(ex));
  }
}
