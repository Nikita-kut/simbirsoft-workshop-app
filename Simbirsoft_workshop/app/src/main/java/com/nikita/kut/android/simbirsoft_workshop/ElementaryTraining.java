package com.nikita.kut.android.simbirsoft_workshop;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {
        return (firstValue + secondValue) / 2.0;

    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        int first = firstValue * 2;
        int second = secondValue - 3;
        int third = thirdValue * thirdValue;
        return first + second + third;
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        if (value > 3) {
            return value + 10;
        } else {
            return value - 10;
        }
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) {
        String valueToText = String.valueOf(value);
        int countsDigit = String.valueOf(Math.abs(value)).length();
        if (value < 10 || countsDigit > 5) {
            return value;
        } else {
            valueToText = valueToText.substring(valueToText.length() - 1) + valueToText.substring(1, valueToText.length() - 1) + valueToText.substring(0, 1);
            return Integer.parseInt(valueToText);
        }
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) {
        int countsDigit = String.valueOf(Math.abs(value)).length();
        if (value % 10 == value || countsDigit > 5) {
            return value;
        } else {
            int lastDigit = value % 10;
            if (lastDigit % 2 == 0) {
                lastDigit = 0;
            }
            return zeroEvenNumber(value / 10) * 10 + lastDigit;
        }
    }
}
