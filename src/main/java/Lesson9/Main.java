package Lesson9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Андрей", Arrays.asList(new Course("ОАиП"), new Course("ООС"),
                new Course("Философия"))));
        students.add(new Student("Антон", Arrays.asList(new Course("ОАиП"), new Course("ООС"),
                new Course("ОМА"), new Course("Английский"))));
        students.add(new Student("Данил", Arrays.asList(new Course("Physics"), new Course("ПЭ"),
                new Course("ОТБ"), new Course("ОМА"))));
        System.out.println(students.stream()
                .map(s -> s.getCourses())
                .flatMap(f -> f.stream())
                .collect(Collectors.toSet()));
        System.out.println(students.stream()
                .sorted((s1, s2) -> s2.getCourses().size() - s1.getCourses().size())
                .limit(2)
                .collect(Collectors.toList()));
        Course course = new Course("ОМА");
        System.out.println(students.stream()
                .filter(s -> s.getCourses().contains(course))
                .collect(Collectors.toList()));
    }

}
