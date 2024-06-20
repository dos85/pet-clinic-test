package pettype;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TestStreamClass {
    @Test
    public void testStream1() {
        System.out.println("Test buildStream start");

        // Создание стрима из значений
        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        System.out.println("streamFromValues = " + streamFromValues.collect(Collectors.toList()));
        // напечатает streamFromValues = [a1, a2, a3]
    }
    @Test
    public void testStream2() {
        // Создание стрима из массива
        String[] array = {"a1","a2","a3"};
        Stream<String> streamFromArrays = Arrays.stream(array);
        System.out.println("streamFromArrays = " + streamFromArrays.collect(Collectors.toList()));
        // напечатает streamFromArrays = [a1, a2, a3]

        Stream<String> streamFromArrays1 = Stream.of(array);
        System.out.println("streamFromArrays1 = " + streamFromArrays1.collect(Collectors.toList()));
        // напечатает streamFromArrays = [a1, a2, a3]

    }
    @Test
    public void testStream3() {
        // Создание стрима из файла (каждая запись в файле будет отдельной строкой в стриме)
        File file = new File("1.tmp");
        file.deleteOnExit();
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        out.println("a1");
        out.println("a2");
        out.println("a3");
        out.close();

        Stream<String> streamFromFiles = null;
        try {
            streamFromFiles = Files.lines(Paths.get(file.getAbsolutePath()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("streamFromFiles = " + streamFromFiles.collect(Collectors.toList()));
        // напечатает streamFromFiles = [a1, a2, a3]

    }
    @Test
    public void testStream4() {
        // Создание стрима из коллекции
        Collection<String> collection = Arrays.asList("a1", "a2", "a3");
        Stream<String> streamFromCollection = collection.stream();
        System.out.println("streamFromCollection = " + streamFromCollection.collect(Collectors.toList()));
        // напечатает streamFromCollection = [a1, a2, a3]

        // Создать параллельный стрим из коллекции
        Stream<String> parallelStream = collection.parallelStream();
        System.out.println("parallelStream = " + parallelStream.collect(Collectors.toList()));
        // напечатает parallelStream = [a1, a2, a3]
    }
    @Test
    public void testStream5() {

        // Создание числового стрима из строки
        IntStream streamFromString = "123".chars();
        System.out.print("streamFromString = ");
        streamFromString.forEach((e)->System.out.print(e + " , ")); // напечатает streamFromString = 49 , 50 , 51 ,
        System.out.println();

    }
    @Test
    public void testStream6() {
        // С помощью Stream.builder
        Stream.Builder<String> builder = Stream.builder();
        Stream<String> streamFromBuilder = builder.add("a1").add("a2").add("a3").build();
        System.out.println("streamFromBuilder = " + streamFromBuilder.map(String::toUpperCase).collect((Collectors.toList())));
        // напечатает streamFromFiles = [A1, A2, A3]
    }
    @Test
    public void testStream7() {
        // Создание бесконечных стримов
        // С помощью Stream.iterate
        Stream<Integer> streamFromIterate2 = Stream.iterate(1, n -> n + 2);
        System.out.println("streamFromIterate count %4 = " + streamFromIterate2.skip(2).filter(n -> n % 3 == 0).map(n -> n -1).limit(10).peek((e) -> System.out.print(e+ ", " )).filter(n -> n % 4 == 0).count());
        Stream<Integer> streamFromIterate3 = Stream.iterate(1, n -> n + 3);
        System.out.println("streamFromIterate sum = " + streamFromIterate3.skip(2).filter(n -> n % 7 == 0).map(n -> n -1).limit(4).peek((e) -> System.out.print(e+ ", " )).reduce((n1, n2) -> n1+n2).get());
        streamFromIterate2 = Stream.iterate(1, n -> n + 2);
        System.out.println("streamFromIterate sum = " + streamFromIterate2.skip(2).filter(n -> n % 3 == 0).mapToInt(n -> n -2).limit(4).peek((e) -> System.out.print(e+ ", " )).sum());

    }
    @Test
    public void testStream8() {
        // Создание бесконечных стримов
        // С помощью Stream.generate
        Stream<String> streamFromGenerate = Stream.generate(() -> "a1");
        System.out.println("streamFromGenerate = " + streamFromGenerate.limit(5).distinct().collect(Collectors.toList()));
        // напечатает streamFromGenerate = [a1, a1, a1]

    }
    @Test
    public void testStream9() {
        // Создать пустой стрим
        Stream<String> streamEmpty = Stream.empty();
        System.out.println("streamEmpty = " + streamEmpty.collect(Collectors.toList()));
        // напечатает streamEmpty = []

    }
    @Test
    public void testStream10() {
        // Создать пустой стрим
        Stream<String> streamEmpty = Stream.empty();
        System.out.println("streamEmpty = " + streamEmpty.collect(Collectors.toList()));
        // напечатает streamEmpty = []

    }
    @Test
    public void testStreamDistinct() {
        System.out.println();
        System.out.println("Test distinct start");
        Collection<String> ordered = Arrays.asList("a1", "a2", "a2", "a3", "a1", "a2", "a2");
        Collection<String> nonOrdered = new HashSet<>(ordered);

        // Получение коллекции без дубликатов
        List<String> distinct = nonOrdered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinct = " + distinct); // напечатает distinct = [a1, a2, a3] - порядок не гарантируется

        List<String> distinctOrdered = ordered.stream().distinct().collect(Collectors.toList());
        System.out.println("distinctOrdered = " + distinctOrdered); // напечатает distinct = [a1, a2, a3] - порядок гарантируется   }
    }
    @Test
    public void testStreamMatch() {
        System.out.println();
        System.out.println("Test anyMatch, allMatch, noneMatch  start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1", "a4", "a2");

        // найти существуют ли хоть одно совпадение с шаблоном в коллекции
        boolean isAnyOneTrue = collection.stream().anyMatch("a1"::equals);
        System.out.println("anyOneTrue " + isAnyOneTrue); // напечатает true
        boolean isAnyOneFalse = collection.stream().anyMatch("a8"::equals);
        System.out.println("anyOneFlase " + isAnyOneFalse); // напечатает false

        // найти существуют ли все совпадения с шаблоном в коллекции
        boolean isAll = collection.stream().allMatch((s) -> s.contains("1"));
        System.out.println("isAll " + isAll); // напечатает false

        // сравнение на неравенство
        boolean isNotEquals = collection.stream().noneMatch("a7"::equals);
        System.out.println("isNotEquals " + isNotEquals); // напечатает true
    }
    @Test
    public void testStreamFlatMap() {
        System.out.println();
        System.out.println("Test flat map start");
        Collection<String> collection = Arrays.asList("1,2,0", "4,5");
        // получить все числовые значения, которые хранятся через запятую в collection
        String[] number = collection.stream().flatMap((p) -> Arrays.asList(p.split(",")).stream()).toArray(String[]::new);
        System.out.println("number = " + Arrays.toString(number)); // напечатает number = [1, 2, 0, 4, 5]

        // получить сумму всех числовые значения, которые хранятся через запятую в collection
        int sum = collection.stream().flatMapToInt((p) -> Arrays.asList(p.split(",")).stream().mapToInt(Integer::parseInt)).sum();
        System.out.println("sum = " + sum); // напечатает sum = 12
    }
    @Test
    public void testStreamSorted() {
        System.out.println();
        System.out.println("Test anyMatch, allMatch, noneMatch  start");
        Collection<String> collection = Arrays.asList("a1", "a2", "a3", "a1", "a4", "a2");

        System.out.println("sorted = " + collection.stream().sorted().collect(Collectors.toList()));
        System.out.println("sorted distinct = " + collection.stream().sorted().distinct().collect(Collectors.toList()));
        System.out.println("sorted reverse = " + collection.stream().sorted((c1, c2) -> -c1.compareTo(c2)).collect(Collectors.toList()));

        Collection<People> peoples = Arrays.asList(
                new People("Вася", 16, Sex.MAN),
                new People("Петя", 23, Sex.MAN),
                new People("Елена", 42, Sex.WOMEN),
                new People("Иван Иванович", 69, Sex.MAN)
        );

        // Отсортировать по имени в обратном алфавитном порядке
        Collection<People> byName = peoples.stream().sorted((o1,o2) -> -o1.getName().compareTo(o2.getName())).collect(Collectors.toList());
        System.out.println("byName = " + byName);
        // byName = [{name='Петя', age=23, sex=MAN}, {name='Иван Иванович', age=69, sex=MAN}, {name='Елена', age=42, sex=WOMEN}, {name='Вася', age=16, sex=MAN}]

        // Отсортировать сначала по полу, а потом по возрасту
        Collection<People> bySexAndAge = peoples.stream().sorted((o1, o2) -> o1.getSex() != o2.getSex() ? o1.getSex().
                compareTo(o2.getSex()) : o1.getAge().compareTo(o2.getAge())).collect(Collectors.toList());
        System.out.println("bySexAndAge = " + bySexAndAge); // bySexAndAge = [{name='Вася', age=16, sex=MAN}, {name='Петя', age=23, sex=MAN}, {name='Иван Иванович', age=69, sex=MAN}, {name='Елена', age=42, sex=WOMEN}]
    }
}

