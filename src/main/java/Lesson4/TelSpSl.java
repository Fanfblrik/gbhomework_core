package Lesson4;

import java.util.*;

public class TelSpSl {

    public static void main(String[] args) {

        List<String> snames = Arrays.asList("Riihimäki", "Helsinki", "Lahti", "Oulu", "Turku", "Tampere",
                "Vaasa", "Pori", "Tampere", "Moskov", "Pietari", "Rovaniemi", "Levi", "Pasila");
        System.out.println("Исходный список:");
        snames.forEach(System.out::println);

        System.out.println("\nСводка:");
        Map<String, Integer> wordsCount = new HashMap<>();
        for (String s : snames) {
            wordsCount.put(s, wordsCount.getOrDefault(s, 0) + 1);
        }
        wordsCount.entrySet().forEach(System.out::println);

        System.out.println("\nБез повторов: ");
        Set<String> unique = new HashSet<>(snames);
        unique.forEach(System.out::println);

        Phonebook pb = new Phonebook();

        pb.add("Ivanov", "123");
        pb.add("Petrov", "456");
        pb.add("Petrov", "789");

        System.out.println(pb.get("Petrov"));

    }

}
