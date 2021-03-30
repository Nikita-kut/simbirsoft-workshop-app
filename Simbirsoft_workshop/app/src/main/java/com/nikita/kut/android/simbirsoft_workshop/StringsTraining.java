package com.nikita.kut.android.simbirsoft_workshop;

import java.util.ArrayList;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        String returnText = "";
        char[] chars = text.toCharArray();
        for (int i = 0; i < chars.length; i = i + 2) {
            char c = chars[i];
            returnText = returnText + c;
        }
        return returnText;
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public ArrayList<Integer> getArrayLastSymbol(String text) {
        char[] chars = text.toCharArray();
        ArrayList<Integer> returnArray = new ArrayList<>();
        char lastChar = chars[chars.length - 1];
        for (int i = 0; i < chars.length; i++) {
            if (lastChar == chars[i]) {
                returnArray.add(i + 1);
            }
        }
        return returnArray;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int countOfDigitInText = 0;
        for (int i = 0; i < text.length(); i++) {
            if (Character.isDigit(text.charAt(i))) {
                countOfDigitInText++;
            }
        }
        return countOfDigitInText;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цыфры заменены словами
     */
    public String replaceAllNumbers(String text) {
        String[] digitsText = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        for (char c = '0'; c <= '9'; ++c)
            text = text.replaceAll(String.valueOf(c), digitsText[c - '0'] + ' ');
        return text;
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        char[] chars = text.toCharArray();
        StringBuilder stringBuilder = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(chars[i])) {
                stringBuilder.append(Character.toLowerCase(chars[i]));
            } else {
                stringBuilder.append(Character.toUpperCase(chars[i]));
            }
        }
        return stringBuilder.toString();
    }

}
