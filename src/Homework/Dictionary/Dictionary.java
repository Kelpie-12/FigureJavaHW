package Homework.Dictionary;

import java.util.*;

public class Dictionary {
    static Map<String, Word> dictionary = new HashMap<>();
    static Scanner scanner = new Scanner(System.in);

    static class Word {

        String word;
        List<String> translations = new ArrayList<>();
        int accessCount = 0;

        Word(String word) {
            this.word = word;
        }

        void addTranslation(String translation) {
            this.translations.add(translation);
        }

        void removeTranslation(String translation) {
            this.translations.remove(translation);
        }

        void setTranslations(List<String> newTranslations) {
            this.translations = new ArrayList<>(newTranslations);
        }

        @Override
        public String toString() {
            return "Слово: " + this.word + ", Переводы: " + this.translations + ", Обращений: " + this.accessCount;
        }
    }

     static void printMenu() {
        System.out.println("\nМеню:");
        System.out.println("1. Инициализация словаря");
        System.out.println("2. Отобразить слово и его переводы");
        System.out.println("3. Добавить перевод");
        System.out.println("4. Заменить перевод");
        System.out.println("5. Удалить перевод");
        System.out.println("6. Добавить слово");
        System.out.println("7. Заменить слово");
        System.out.println("8. Удалить слово");
        System.out.println("9. Топ-10 популярных слов");
        System.out.println("10. Топ-10 непопулярных слов");
        System.out.println("0. Выход");
    }

     static int readInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Пожалуйста, введите число: ");
        }
        return scanner.nextInt();
    }

    static String readString(String message) {
        System.out.print(message);
        return scanner.next();
    }

    static void initializeDictionary() {
        dictionary.clear();
        System.out.println("Введите количество слов для добавления:");
        int count = readInt("");
        for (int i = 0; i < count; i++) {
            String word = readString("Слово: ");
            Word w = new Word(word);
            int transCount = readInt("Количество переводов: ");
            for (int j = 0; j < transCount; j++) {
                String trans = readString("Перевод: ");
                w.addTranslation(trans);
            }
            dictionary.put(word, w);
        }
        System.out.println("Словарь инициализирован.");
    }

    static void displayWord() {
        String wordStr = readString("Введите слово для отображения: ");
        Word w = dictionary.get(wordStr);
        if (w != null) {
            w.accessCount++;
            System.out.println(w);
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void addTranslation() {
        String wordStr = readString("Введите слово: ");
        Word w = dictionary.get(wordStr);
        if (w != null) {
            String trans = readString("Введите перевод для добавления: ");
            w.addTranslation(trans);
            System.out.println("Перевод добавлен.");
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void replaceTranslation() {
        String wordStr = readString("Введите слово: ");
        Word w = dictionary.get(wordStr);
        if (w != null) {
            String oldTrans = readString("Введите перевод, который хотите заменить: ");
            if (w.translations.contains(oldTrans)) {
                String newTrans = readString("Введите новый перевод: ");
                int index = w.translations.indexOf(oldTrans);
                w.translations.set(index, newTrans);
                System.out.println("Перевод заменен.");
            } else {
                System.out.println("Перевод не найден.");
            }
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void deleteTranslation() {
        String wordStr = readString("Введите слово: ");
        Word w = dictionary.get(wordStr);
        if (w != null) {
            String trans = readString("Введите перевод для удаления: ");
            if (w.translations.remove(trans)) {
                System.out.println("Перевод удален.");
            } else {
                System.out.println("Перевод не найден.");
            }
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void addWord() {
        String wordStr = readString("Введите новое слово: ");
        if (dictionary.containsKey(wordStr)) {
            System.out.println("Слоо уже есть в словаре.");
        } else {
            Word w = new Word(wordStr);
            int transCount = readInt("Количество переводов: ");
            for (int j = 0; j < transCount; j++) {
                String trans = readString("Перевод: ");
                w.addTranslation(trans);
            }
            dictionary.put(wordStr, w);
            System.out.println("Слово добавлено.");
        }
    }

    static void replaceWord() {
        String oldWord = readString("Введите слово для замены: ");
        if (dictionary.containsKey(oldWord)) {
            String newWord = readString("Введите новое слово: ");
            Word w = dictionary.remove(oldWord);
            w.word = newWord;
            dictionary.put(newWord, w);
            System.out.println("Слово заменено.");
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void deleteWord() {
        String wordStr = readString("Введите слово для удаления: ");
        if (dictionary.containsKey(wordStr)) {
            dictionary.remove(wordStr);
            System.out.println("Слово удалено.");
        } else {
            System.out.println("Слово не найдено.");
        }
    }

    static void displayTopPopularWords() {
        List<Word> sorted = new ArrayList<>(dictionary.values());
        sorted.sort((w1, w2) -> Integer.compare(w2.accessCount, w1.accessCount));
        System.out.println("ТОП-10 самых популярных слов:");
        for (int i = 0; i < Math.min(10, sorted.size()); i++) {
            System.out.println(sorted.get(i));
        }
    }

    static void displayTopUnpopularWords() {
        List<Word> sorted = new ArrayList<>(dictionary.values());
        sorted.sort(Comparator.comparingInt(w -> w.accessCount));
        System.out.println("ТОП-10 самых непопулярных слов:");
        for (int i = 0; i < Math.min(10, sorted.size()); i++) {
            System.out.println(sorted.get(i));
        }

    }

    public static void createDictionary() {
        boolean exit = false;
        while (!exit) {
            printMenu();
            int choice = readInt("Выберите команду: ");
            switch (choice) {
                case 1:
                    initializeDictionary();
                    break;
                case 2:
                    displayWord();
                    break;
                case 3:
                    addTranslation();
                    break;
                case 4:
                    replaceTranslation();
                    break;
                case 5:
                    deleteTranslation();
                    break;
                case 6:
                    addWord();
                    break;
                case 7:
                    replaceWord();
                    break;
                case 8:
                    deleteWord();
                    break;
                case 9:
                    displayTopPopularWords();
                    break;
                case 10:
                    displayTopUnpopularWords();
                    break;
                case 0:
                    exit = true;
                    System.out.println("Выход из программы.");
                    break;
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}

