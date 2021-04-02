package com.nikita.kut.android.simbirsoft_workshop;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 * Набор заданий по работе с классами в java.
 * <p>
 * Задания подразумевают создание класса(ов), выполняющих задачу.
 * <p>
 * Проверка осуществляется ментором.
 */
public interface ClassesBlock {

    /*
      I

      Создать класс с двумя переменными. Добавить функцию вывода на экран
      и функцию изменения этих переменных. Добавить функцию, которая находит
      сумму значений этих переменных, и функцию которая находит наибольшее
      значение из этих двух переменных.
     */

    class JustClass {
        int value1;
        int value2;

        public int getValue1() {
            return value1;
        }

        public void setValue1(int value1) {
            this.value1 = value1;
        }

        public int getValue2() {
            return value2;
        }

        public void setValue2(int value2) {
            this.value2 = value2;
        }

        public void printValue1() {
            System.out.println("Value 1 is" + getValue1());
        }

        public void printValue2() {
            System.out.println("Value 2 is" + getValue2());
        }

        public int getValuesSum() {
            return getValue1() + getValue2();
        }

        public int getMaxValue() {
            return Math.max(getValue1(), getValue2());
        }
    }

    /*
      II

      Создать класс, содержащий динамический массив и количество элементов в нем.
      Добавить конструктор, который выделяет память под заданное количество элементов.
      Добавить методы, позволяющие заполнять массив случайными числами,
      переставлять в данном массиве элементы в случайном порядке, находить количество
      различных элементов в массиве, выводить массив на экран.
     */

    class DynamicArray {
        int[] array;
        int arraySize;

        DynamicArray(int arraySize) {
            this.array = new int[arraySize];
            this.arraySize = arraySize;
        }

        public int[] getArray() {
            return array;
        }

        public int[] fillArrayRandomNumber(int[] inputArray) {
            int size = inputArray.length;
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                inputArray[i] = random.nextInt();
            }
            return inputArray;
        }

        public int[] mixArray(int[] inputArray) {
            Random random = new Random();
            List<Integer> indexes = new ArrayList<>(inputArray.length);
            int count = 0;
            while (true) {
                int randomInt = random.nextInt(inputArray.length);
                if (!indexes.contains(randomInt)) {
                    indexes.add(randomInt);
                    inputArray[randomInt] = inputArray[count++];
                }
                if (count == inputArray.length) {
                    break;
                }
            }
            return inputArray;
        }

        public int findNumberCount(int n, int[] inputArray) {
            int count = 0;
            for (int value : inputArray) {
                if (value == n) {
                    count++;
                }
            }
            return count;
        }

        public void printArray(int[] inputArray) {
            for (int value : inputArray) {
                System.out.println(value);
            }
        }
    }

    /*
      III

      Описать класс, представляющий треугольник. Предусмотреть методы для создания объектов,
      вычисления площади, периметра и точки пересечения медиан.
      Описать свойства для получения состояния объекта.
     */

    class Triangle {
        int a; //сторона 1
        int b; //сторона 2
        int c; //сторона 3
        double square;
        int perimeter;
        double x1, x2, x3, y1, y2, y3; // координаты для точки пересечения меридиан

        Triangle(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        Triangle(double x1, double x2, double x3, double y1, double y2, double y3) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
            this.y1 = y1;
            this.y2 = y2;
            this.y3 = y3;
        }

        public double getSquare() {
            int p, x;
            p = (int) (perimeter / 2);
            x = perimeter * (perimeter - a) * (perimeter - b) * (perimeter - c);
            square = Math.sqrt(x);
            return square;
        }

        public int getPerimeter() {
            return a + b + c;
        }

        public void printMedianCrossingPoint() {
            Mediana mediana1 = new Mediana(x1, y1);
            Mediana mediana2 = new Mediana(x2, y2);
            Mediana mediana3 = new Mediana(x3, y3);

            double pointX = (mediana1.getX() + mediana2.getX() + mediana3.getX()) / 3.0;
            double pointY = (mediana1.getY() + mediana2.getY() + mediana3.getY()) / 3.0;

            System.out.println("Median crossing point is: X = " + pointX + " Y = " + pointY + "");
        }

        static class Mediana {
            private final double x;
            private final double y;

            Mediana(double x, double y) {
                this.x = x;
                this.y = y;
            }

            public double getX() {
                return x;
            }

            public double getY() {
                return y;
            }
        }
    }

    /*
      IV

      Составить описание класса для представления времени.
      Предусмотреть возможности установки времени и изменения его отдельных полей
      (час, минута, секунда) с проверкой допустимости вводимых значений.
      В случае недопустимых значений полей выбрасываются исключения.
      Создать методы изменения времени на заданное количество часов, минут и секунд.
     */

    class MyTime {
        int hour;
        int minute;
        int second;

        public void setHour(int hour) {
            if (hour > 0 && hour < 23) {
                this.hour = hour;
            } else {
                try {
                    throw new Exception("Hour not in range 0-24");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void setMinute(int minute) {
            if (minute > 0 && minute < 59) {
                this.minute = minute;
            } else {
                try {
                    throw new Exception("Minute not in range 0-59");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void setSecond(int second) {
            if (second > 0 && second < 59) {
                this.second = second;
            } else {
                try {
                    throw new Exception("Second not in range 0-59");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        public void setTime(int hour, int minute, int second) {
            setHour(hour);
            setMinute(minute);
            setSecond(second);
        }
    }

    /*
      V
      Класс Абонент: Идентификационный номер, Фамилия, Имя, Отчество, Адрес,
      Номер кредитной карточки, Дебет, Кредит, Время междугородных и городских переговоров;
      Конструктор;
      Методы: установка значений атрибутов, получение значений атрибутов,
      вывод информации. Создать массив объектов данного класса.
      Вывести сведения относительно абонентов, у которых время городских переговоров
      превышает заданное.  Сведения относительно абонентов, которые пользовались
      междугородной связью. Список абонентов в алфавитном порядке.
     */

    class Abonent {
        private int id;
        private String surname;
        private String firstName;
        private String secondName;
        private String address;
        private int cardNumber;
        private int debit;
        private int credit;
        private int internationalTalksTime;
        private int innerTalksTime;

        Abonent(String surname) {
            this.id = randomNumber(1500, 2000);
            this.surname = surname;
            this.firstName = "first name";
            this.secondName = "second name";
            this.address = "address";
            this.cardNumber = randomNumber(10000000, 20000000);
            this.debit = randomNumber(100, 500);
            this.credit = randomNumber(-500, -100);
            this.internationalTalksTime = randomNumber(0, 10);
            this.innerTalksTime = randomNumber(0, 10);
        }

        int randomNumber(int minRange, int maxRange) {
            int dif = maxRange - minRange;
            Random random = new Random();
            int randomNumber = random.nextInt(dif + 1);
            randomNumber += minRange;
            return randomNumber;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void displayId() {
            System.out.println(getId());
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public void displaySurname() {
            System.out.println(getSurname());
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public void displayFirstName() {
            System.out.println(getFirstName());
        }

        public String getSecondName() {
            return secondName;
        }

        public void setSecondName(String secondName) {
            this.secondName = secondName;
        }

        public void displaySecondName() {
            System.out.println(getSecondName());
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void displayAddress() {
            System.out.println(getAddress());
        }

        public int getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(int cardNumber) {
            this.cardNumber = cardNumber;
        }

        public void displayCardNumber() {
            System.out.println(getCardNumber());
        }

        public int getDebit() {
            return debit;
        }

        public void setDebit(int debit) {
            this.debit = debit;
        }

        public void displayDebit() {
            System.out.println(getDebit());
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public void displayCredit() {
            System.out.println(getCredit());
        }

        public int getInnerTalksTime() {
            return innerTalksTime;
        }

        public void setInnerTalksTime(int innerTalksTime) {
            this.innerTalksTime = innerTalksTime;
        }

        public void displayInnerTalksTime() {
            System.out.println(getInnerTalksTime());
        }

        public int getInternationalTalksTime() {
            return internationalTalksTime;
        }

        public void setInternationalTalksTime(int internationalTalksTime) {
            this.internationalTalksTime = internationalTalksTime;
        }

        public void displayInternationalTalksTime() {
            System.out.println(getInternationalTalksTime());
        }

        public Abonent[] getArrayAbonents(int arraySize) {
            Abonent[] abonents = new Abonent[arraySize];
            if (arraySize > 0) {
                for (int i = 0; i < abonents.length; i++) {
                    abonents[i] = new Abonent("Abonent " + i + "");
                }
            }
            return abonents;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public List<Abonent> sortByAlphabet(Abonent[] abonents) {
            List<Abonent> listAbonents = new ArrayList<>(Arrays.asList(abonents));
            Collections.sort(listAbonents, Comparator.comparing(Abonent::getSurname));
            return listAbonents;
        }

        public List<Abonent> sortByCreditDebet(Abonent[] abonents) {
            List<Abonent> sortedAbonents = new ArrayList<>();
            for (Abonent currentAbonent : abonents) {
                if ((currentAbonent.debit + currentAbonent.credit) <= 0) {
                    sortedAbonents.add(currentAbonent);
                }
            }
            return sortedAbonents;
        }

        @Override
        public String toString() {
            return "Abonent{" +
                    "id=" + id +
                    ", surname='" + surname + '\'' +
                    ", firstName='" + firstName + '\'' +
                    ", secondName='" + secondName + '\'' +
                    ", address='" + address + '\'' +
                    ", cardNumber=" + cardNumber +
                    ", debit=" + debit +
                    ", credit=" + credit +
                    ", internationalTalksTime=" + internationalTalksTime +
                    ", innerTalksTime=" + innerTalksTime +
                    '}';
        }
    }


    /*
      VI

      Задача на взаимодействие между классами. Разработать систему «Вступительные экзамены».
      Абитуриент регистрируется на Факультет, сдает Экзамены. Преподаватель выставляет Оценку.
      Система подсчитывает средний бал и определяет Абитуриента, зачисленного в учебное заведение.
     */

    class Faculty {
        private List<Faculty> faculties;

        public Faculty() {
            faculties = new ArrayList<>();
        }

        public void loadList(Object obj) {
            faculties = (List<Faculty>) obj;
        }

        public List<Faculty> getList() {
            return faculties;
        }
    }

    class Abiturient {
        private final List<Answer> answers;
        private String name;
        private String facultyName;
        private double avg;
        private boolean enrolled;

        public Abiturient(String name, String facultyName) {
            this.name = name;
            this.facultyName = facultyName;
            answers = new ArrayList<>();
            enrolled = false;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getFacultyName() {
            return facultyName;
        }

        public void setFacultyName(String facultyName) {
            this.facultyName = facultyName;
        }

        public List<Answer> getAnswers() {
            return answers;
        }

        public void addAnswer(Answer answer) {
            answers.add(answer);
        }

        public void setAvg(double avg) {
            this.avg = avg;
        }

        public boolean isEnrolled() {
            return enrolled;
        }

        public void setEnrolled(boolean enrolled) {
            this.enrolled = enrolled;
        }

        public double getAvg() {
            return avg;
        }
    }

    class Answer {
        private String examName;
        private String answerText;
        private int score;

        public Answer(String examName, String answerText) {
            this.examName = examName;
            this.answerText = answerText;
            this.score = -1; //not checked by teacher
        }

        public String getExamName() {
            return examName;
        }

        public void setExamName(String examName) {
            this.examName = examName;
        }

        public String getAnswerText() {
            return answerText;
        }

        public void setAnswerText(String answerText) {
            this.answerText = answerText;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }

    class Exam {
        private String name;
        private String task;

        public Exam(String name, String task) {
            this.name = name;
            this.task = task;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        class Teacher {
            private String username;
            private String password;

            public Teacher(String username, String password) {
                this.username = username;
                this.password = password;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }
        }
    }

    /*
      VII

      Задача на взаимодействие между классами. Разработать систему «Интернет-магазин».
      Товаровед добавляет информацию о Товаре. Клиент делает и оплачивает Заказ на Товары.
      Товаровед регистрирует Продажу и может занести неплательщика в «черный список».
     */

    class Product {
        private String name;
        private int price;

        public Product (String name, int price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPrice (int price) {
            this.price = price;
        }
    }

    class Order {
        private ArrayList<Product> orderlist = new ArrayList<>();
        private boolean payment = false;
        private boolean register = false;

        public void addProdToOrder(ArrayList<Product> orderlist) {
            this.orderlist = orderlist;
        }

        public void addProdToOrder(Product product) {
            orderlist.add(product);
        }

        public void showOrder() {
            for (Product e: orderlist) {
                System.out.println (e);
            }
        }

        public boolean isPayment() {
            return payment;
        }

        public void setPayment(boolean s) {
            payment = s;
        }

        public boolean isRegister() {
            return register;
        }

        public void setRegister(boolean s) {
            register = s;
        }
    }

    class Client {
        private Order order;

        public void book(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }

        public void showOrder() {
            System.out.println("Ваш заказ: ");
            order.showOrder();
        }

        public void pay() {
            if(order.isPayment()) {
                System.out.println("Вы уже оплатили заказ");
            } else {
                order.setPayment(true);
            }
        }

        public void take() {
            if(!order.isPayment()) {
                System.out.println("Вы ещё не оплатили товар");
            } else if(!order.isRegister()) {
                System.out.println("Ваша заявка ещё не обработана");
            } else {
                System.out.println("Спасибо за покупку!");
            }
        }
    }

    class CommodityExpert {
        private final ArrayList<Product> products = new ArrayList<Product>();
        private final ArrayList<Client> blackclients = new ArrayList<Client>();

        public void registerOrder(Client client) {
            if (client.getOrder().isPayment()) {
                client.getOrder().setRegister(true);
            } else {
                blackclients.add(client);
            }
        }

        public void createNewProduct(String name, int price) {
            products.add(new Product(name,price));
        }
    }
}
