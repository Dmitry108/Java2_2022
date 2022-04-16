package collections;

import java.util.Map;
import java.util.TreeMap;

public class TextAnalyser {
    private static final String TASK = "1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся). " +
            "Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем). " +
            "Посчитать сколько раз встречается каждое слово.\n" +
            "2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров. " +
            "В этот телефонный справочник с помощью метода add() можно добавлять записи. " +
            "С помощью метода get() искать номер телефона по фамилии. Следует учесть, " +
            "что под одной фамилией может быть несколько телефонов (в случае однофамильцев), " +
            "тогда при запросе такой фамилии должны выводиться все телефоны.\n" +
            "за использование в качестве ключа для Map не фамилии человека будет снижена оценка\n" +
            "Желательно как можно меньше добавлять своего, чего нет в задании " +
            "(т.е. не надо в телефонную запись добавлять еще дополнительные поля (имя, отчество, адрес), " +
            "делать взаимодействие с пользователем через консоль и т.д.). Консоль желательно не использовать " +
            "(в том числе Scanner), тестировать просто из метода main() прописывая add() и get().";

    public static void main(String[] args) {
        String[] words = TASK.toLowerCase().split("[^а-яa-z]+");
        Map<String, Integer> map = new TreeMap<>();

        for (String word : words) {
            if (!word.equals("")) {
                map.put(word.toLowerCase(), map.getOrDefault(word, 0) + 1);
            }
        }
        map.forEach((s, n) -> System.out.printf("Слово \"%s\" встречается %d раз%n", s, n));
        System.out.println(map.size() + " уникальных слов");
    }
}
