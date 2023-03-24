package com.eomcs.basic.ex11;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exam0100 {
  public static void main(String[] args) {
    Pattern pattern = Pattern.compile("\\d+|\\D", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher("123           +2 * 9 8- 24 /19");
    ArrayList<String> items = new ArrayList<>();
    while (matcher.find()) {
      items.add(matcher.group());
    }
    System.out.println("----------------------------");

    for (String item : items) {
      System.out.println(item);
    }
  }

}
