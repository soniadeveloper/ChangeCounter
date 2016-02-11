import java.util.Scanner;
import java.text.DecimalFormat;

public class CoinCounter {
  private int dollars;
  private int cents;
  private int ones;
  private int fives;
  private int tens;
  private int twenties;
  private double pennies;
  private double quarters;
  private double nickels;
  private double dimes;

  public int numTwenties(int dollars) {
    int d;
    if (dollars < 20) {
      d = 0;
    }
    else {
      d = dollars / 20;
    }
    return d;
  }

  public int numTens(int dollars) {
    int d;
    if (dollars < 10 || dollars % 20 == 0) {
      d = 0;
    }
    else {
      d = (dollars % 20) / 10;
    }
    return d;
  }

  public int numFives(int dollars) {
    int d;
    if (dollars < 5 || dollars % 10 == 0) {
      d = 0;
    }
    d = (dollars % 20) / 15;

    return d;
  }

  public int numOnes(int dollars) {
    int d;
    if (dollars < 1 || dollars % 5 == 0) {
      d = 0;
    }
    else {
      d = dollars % 5;
    }
    return d;
  }

  public int numQuarters(int cents) {
    int quarts;
    if (cents < 25) {
      quarts = 0;
    }
    else {
      quarts = cents / 25;
    }
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

  public CoinCounter(float c) {
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    String cstring = df.format(c);
    int len = cstring.length();
    String dstring;
    String cestring;
    if (cstring.length() > 1) {
      dstring = cstring.substring(0, len-3);
      cestring = cstring.substring(len-2, len);
    }
    else {
      dstring = cstring;
      cestring = "00";
    }
    dollars = Integer.parseInt(dstring);
    cents = Integer.parseInt(cestring);
    ones = numOnes(dollars);
    fives = numFives(dollars);
    tens = numTens(dollars);
    twenties = numTwenties(dollars);
    pennies = numPennies(cents);
    nickels = numNickels(cents);
    dimes = numDimes(cents);
    quarters = numQuarters(cents);
  }

  public static String oWord(CoinCounter n) {
    String word;
    if (n.ones == 1) {
      word = " $1 bill ";
    }
    else {
      word = " $1 bills ";
    }

    return word;
  }

  public static String oPhrase(CoinCounter n) {
    String phr;
    if (n.ones == 0) {
      phr = "";
    }
    else {
      phr = n.ones + oWord(n);
    }

    return phr;
  }

  public static String fWord(CoinCounter n) {
    String word;
    if (n.fives == 1) {
      word = " $5 bill ";
    }
    else {
      word = " $5 bills ";
    }

    return word;
  }

  public static String fPhrase(CoinCounter n) {
    String phr;
    if (n.fives == 0) {
      phr = "";
    }
    else {
      phr = n.fives + fWord(n);
    }

    return phr;
  }

  public static String tWord(CoinCounter n) {
    String word;
    if (n.tens == 1) {
      word = " $10 bill ";
    }
    else {
      word = " $10 bills ";
    }

    return word;
  }

  public static String tPhrase(CoinCounter n) {
    String phr;
    if (n.tens == 0) {
      phr = "";
    }
    else {
      phr = n.tens + tWord(n);
    }

    return phr;
  }

  public static String twWord(CoinCounter n) {
    String word;
    if (n.twenties == 1) {
      word = " $20 bill ";
    }
    else {
      word = " $20 bills ";
    }

    return word;
  }

  public static String twPhrase(CoinCounter n) {
    String phr;
    if (n.twenties == 0) {
      phr = "";
    }
    else {
      phr = n.twenties + twWord(n);
    }

    return phr;
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
      phr = "and " + inte + nWord(n);
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
    else if (n.twenties == 0 && n.tens == 0 && n.fives == 0 && n.ones == 0 && n.quarters == 0 && n.dimes == 0 && n.nickels == 0) {
      phr = "" + inte + pWord(n);
    }
    else {
      phr = " and " + inte + pWord(n);
    }
    return phr;
  }

  public static void main(String[] args) {
    DecimalFormat df = new DecimalFormat();
    df.setMaximumFractionDigits(2);
    System.out.println("Enter the total price:");
    Scanner inpprice = new Scanner(System.in);
    float price = inpprice.nextFloat();
    System.out.println("Enter the amount of money that was given:");
    Scanner ingiven = new Scanner(System.in);
    float given = ingiven.nextFloat();
    float dif = given - price;
    String formdif = df.format(dif);
    float diff = Float.parseFloat(formdif);
    CoinCounter ex = new CoinCounter(diff);

    if (diff < 0) {
      System.out.println("That customer needs to pay more money!");
    }
    else if (diff == 0) {
      System.out.println("Change due: $" + diff + "\n" + "They gave the exact amount that was needed! They just made your job easier!");
    }
    else {
      System.out.println("Change due: $" + diff + "\n" + "Give " + twPhrase(ex) + tPhrase(ex) + fPhrase(ex) + oPhrase(ex) +  "\n" + "     " + qPhrase(ex) + dPhrase(ex) + nPhrase(ex) + pPhrase(ex));
    }
  }
}
