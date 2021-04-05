package com.nikita.kut.android.simbirsoft_workshop;

import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see CollectionsBlockTest.
 */
public class CollectionsBlock<T extends Comparable> {

    /**
     * Даны два упорядоченных по убыванию списка.
     * Объедините их в новый упорядоченный по убыванию список.
     * Исходные данные не проверяются на упорядоченность в рамках данного задания
     *
     * @param firstList  первый упорядоченный по убыванию список
     * @param secondList второй упорядоченный по убыванию список
     * @return объединенный упорядоченный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask0(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        List<T> unionList = new ArrayList<T>();
        unionList.addAll(firstList);
        unionList.addAll(secondList);
        return unionList;
    }

    /**
     * Дан список. После каждого элемента добавьте предшествующую ему часть списка.
     *
     * @param inputList с исходными данными
     * @return измененный список
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask1(@NonNull List<T> inputList) {
        List<T> outputList = new ArrayList<>();
        for (int i = 0; i < inputList.size(); i++) {
            outputList.add(inputList.get(i));
            outputList.addAll(inputList.subList(0, i));
        }
        return outputList;
    }

    /**
     * Даны два списка. Определите, совпадают ли множества их элементов.
     *
     * @param firstList  первый список элементов
     * @param secondList второй список элементов
     * @return <tt>true</tt> если множества списков совпадают
     * @throws NullPointerException если один из параметров null
     */
    public boolean collectionTask2(@NonNull List<T> firstList, @NonNull List<T> secondList) {
        return firstList.retainAll(secondList) && secondList.retainAll(firstList);
    }

    /**
     * Создать список из заданного количества элементов.
     * Выполнить циклический сдвиг этого списка на N элементов вправо или влево.
     * Если N > 0 циклический сдвиг вправо.
     * Если N < 0 циклический сдвиг влево.
     *
     * @param inputList список, для которого выполняется циклический сдвиг влево
     * @param n         количество шагов циклического сдвига N
     * @return список inputList после циклического сдвига
     * @throws NullPointerException если один из параметров null
     */
    public List<T> collectionTask3(@NonNull List<T> inputList, int n) {
        int size = inputList.size();
        if (size == 0) return inputList;

        T element = null;
        for (int i = 0; i < n; i++) {
            element = inputList.remove(size - 1);
            inputList.add(0, element);
        }
        return inputList;
    }

    /**
     * Элементы списка хранят слова предложения.
     * Замените каждое вхождение слова A на B.
     *
     * @param inputList список со словами предложения и пробелами для разделения слов
     * @param a         слово, которое нужно заменить
     * @param b         слово, на которое нужно заменить
     * @return список после замены каждого вхождения слова A на слово В
     * @throws NullPointerException если один из параметров null
     */
    public List<String> collectionTask4(@NonNull List<String> inputList, @NonNull String a,
                                        @NonNull String b) {
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) == a) {
                inputList.set(i, b);
            }
        }
        return inputList;
    }


    /*
      Задание подразумевает создание класса(ов) для выполнения задачи.

      Дан список студентов. Элемент списка содержит фамилию, имя, отчество, год рождения,
      курс, номер группы, оценки по пяти предметам. Заполните список и выполните задание.
      1. Упорядочите студентов по курсу, причем студенты одного курса располагались
      в алфавитном порядке.
      2. Найдите средний балл каждой группы по каждому предмету.
      3. Определите самого старшего студента и самого младшего студентов.
      4. Для каждой группы найдите лучшего с точки зрения успеваемости студента.
     */
    static class Student {
        private String surname;
        private String firstName;
        private String secondName;
        private int yearOfBirth;
        private int course;
        private int groupNumber;
        private int mathGrade;
        private int engGrade;
        private int physicGrade;
        private int programmingGrade;
        private int chemistryGrade;

        Student() {
        }

        Student(
                String surname,
                String firstName,
                String secondName
        ) {
            this.surname = surname;
            this.firstName = firstName;
            this.secondName = secondName;
            this.yearOfBirth = randomNumber(1996, 2001);
            this.course = randomNumber(1, 5);
            this.groupNumber = randomNumber(10, 15);
            this.mathGrade = randomNumber(2, 5);
            this.engGrade = randomNumber(2, 5);
            this.physicGrade = randomNumber(2, 5);
            this.programmingGrade = randomNumber(2, 5);
            this.chemistryGrade = randomNumber(2, 5);
        }

        int randomNumber(int minRange, int maxRange) {
            int dif = maxRange - minRange;
            Random random = new Random();
            int randomNumber = random.nextInt(dif + 1);
            randomNumber += minRange;
            return randomNumber;
        }

        public List<Student> getListStudent() {
            Student student1 = new Student("Ivanov", "Ivan", "Ivanov");
            Student student2 = new Student("Petrov", "Petr", "Petrovich");
            Student student3 = new Student("Sidorov", "Sidor", "Sidorovich");
            Student student4 = new Student("Murzikov", "Murzik", "Murzikovich");
            Student student5 = new Student("Maximov", "Maxim", "Maximovich");
            List<Student> students = new ArrayList<>();
            students.add(student1);
            students.add(student2);
            students.add(student3);
            students.add(student4);
            students.add(student5);
            return students;
        }

        public int getCourse() {
            return course;
        }

        public String getSurname() {
            return surname;
        }

        public int getYearOfBirth() {
            return yearOfBirth;
        }

        public int getGroupNumber() {
            return groupNumber;
        }

        public int getChemistryGrade() {
            return chemistryGrade;
        }

        public int getEngGrade() {
            return engGrade;
        }

        public int getPhysicGrade() {
            return physicGrade;
        }

        public int getMathGrade() {
            return mathGrade;
        }

        public int getProgrammingGrade() {
            return programmingGrade;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public List<Student> sortByCourseAndAlphabet(List<Student> students) {
            Collections.sort(students, Comparator.comparing(Student::getCourse)
                    .thenComparing(Student::getSurname));
            return students;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public Map<Integer, Double> averageGrade(List<Student> students, ToIntFunction<? super Student> mapper) {
            return students.stream().collect(Collectors.groupingBy(Student::getCourse, Collectors.averagingInt(mapper)));
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public Student getOldestStudent(List<Student> students) {
            return Collections.min(students, Comparator.comparing(Student::getYearOfBirth));
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public Student getYoungestStudent(List<Student> students) {
            return Collections.max(students, Comparator.comparing(Student::getYearOfBirth));
        }

        @Override
        public String toString() {
            return "Student{" +
                    "surname='" + surname + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", secondName='" + secondName + '\'' +
                    ", yearOfBirth=" + yearOfBirth +
                    ", course=" + course +
                    ", groupNumber=" + groupNumber +
                    ", mathGrade=" + mathGrade +
                    ", engGrade=" + engGrade +
                    ", physicGrade=" + physicGrade +
                    ", programmingGrade=" + programmingGrade +
                    ", chemistryGrade=" + chemistryGrade +
                    '}';
        }
    }
}

