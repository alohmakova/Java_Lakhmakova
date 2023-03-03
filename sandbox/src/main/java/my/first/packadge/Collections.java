package my.first.packadge;

import java.util.Arrays;
import java.util.List;

public class Collections {
    public static void main(String[] args) {
//        String[] langs = new String[4];
//        langs[0] = "Java";
//        langs[1] = "С#";
//        langs[2] = "Python";
//        langs[3] = "PHP";//IndexOutOfBoundExeption - Исключение возникнет если попытаться обратиться к элементу с индексом 4 и более

//        String[] langs = {"Java", "C#", "Python", "PHP"};
//        for (int i = 0; i < langs.length; i++) {
//            System.out.println ("Я хочу выучить " + langs[i]);
//        }

        List<String> languages = Arrays.asList ("Java", "C#", "Python", "PHP");

        for (String l : languages) {
            System.out.println ("Я хочу выучить " + l);
        }
//        List languages = Arrays.asList ("Java", "C#", "Python", "PHP");
//
//        for (Object l : languages) {
//            System.out.println ("Я хочу выучить " + l);
//        }
    }
    }
