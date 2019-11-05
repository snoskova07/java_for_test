package ru.stqa.pft.sandbox;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Collections {

  public static void main (String[] args) {
    String[] langs = new String[4];
    langs[0] = "Java";
    langs[1] = "С#";
    langs[2] = "Pyton";
    langs[3] = "PHP";

    String[] langsOtherDeclaration = {"Java", "С#", "Pyton", "PHP"};

    for (int i = 0; i<langsOtherDeclaration.length; i++) {
      System.out.println("Я хочу выучить " + langsOtherDeclaration[i]);
    }

    for (String l: langsOtherDeclaration)
    {
      System.out.println("Я хочу выучить " + l);
    }

    List<String> languages = new ArrayList<String>();
    languages.add("Java");
    languages.add("C#");
    languages.add("Pyton");

    for (String len: languages)
    {
      System.out.println("Я хочу выучить " + len);
    }

    List<String> languagesList = Arrays.asList("Java", "С#", "Pyton", "PHP");
    for (int i =0; i < languagesList.size(); i++) {
      System.out.println("Я хочу выучить " + languagesList.get(i));
    }

    List languagesObject = Arrays.asList("Java", "С#", "Pyton", "PHP");
    for (Object l: languagesObject) {
      System.out.println("Я хочу выучить " + l);
    }

  }
}
