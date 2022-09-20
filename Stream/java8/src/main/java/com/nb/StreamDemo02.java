package com.nb;

import com.nb.entity.Author;
import com.nb.entity.Book;

import java.sql.SQLOutput;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author yunfu.ye
 * @date 2022/9/14 17:02
 * @desciption: stream流
 */
public class StreamDemo02 {
    public static void main(String[] args) {
        test25();
        // test24();
        // test23();
        // test22();
        // test21();
        // test20();
        // test19();
        // test18();
        // test17();
        // test16();
        // test15();
        // test14();
        // test13();
        // test12();
        // test11();
        // test10();
        // test09();
         // test08();
        // test07();
        // test05();
        // test04();
        // test03();

    }
    /**
     * 使用reduce求所有作者年龄最小值
     */
    private static void test25(){
        List<Author> authors = getAuthors();
        // Integer max = authors.stream()
        //         .map(author -> author.getAge())
        //         .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        // System.out.println(max);
        Optional<Integer> optionalMax = authors.stream()
                .map(author -> author.getAge())
                .reduce(new BinaryOperator<Integer>() {
                    @Override
                    public Integer apply(Integer result, Integer element) {
                        return result > element ? element : result;
                    }
                });
        optionalMax.ifPresent(age -> System.out.println(age));

    }
    /**
     * 使用reduce求所有作者年龄最大值
     */
    private static void test24(){
        List<Author> authors = getAuthors();
        Integer max = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);
        //  .reduce(Integer.MIN_VALUE, new BinaryOperator<Integer>() {
        //     @Override
        //     public Integer apply(Integer result, Integer element) {
        //         return result < element ? element : result;
        //     }
        // });
        System.out.println(max);
    }
    /**
     * 使用reduce求所有作者年龄的和
     */
    private static void test23(){
        List<Author> authors = getAuthors();
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (result, element) -> result + element);
        System.out.println(sum);
    }
    /**
     * 判断流中倒序第一个元素作家是否都没有超过18,存在就输出他的姓名
     */
    private static void test22(){
        List<Author> authors = getAuthors();
        Optional<Author> anyFirst = authors.stream()
                .sorted()
                .filter(author -> author.getAge() >= 18)
                .findFirst();

        anyFirst.ifPresent((author) -> System.out.println(author.getName()));

    }
    /**
     * 判断作家是否都没有超过18,存在就输出他的姓名
     */
    private static void test21(){
        List<Author> authors = getAuthors();
        Optional<Author> any = authors.stream()
                .filter(author -> author.getAge() >= 18)
                .findAny();
        // System.out.println(any);  Optional[Author(id=1, name=吴亦凡, introduction=my introduction 1, age=18, bookList=[Book{id=1, category='类别,分类啊', name='书名1', score=45.0, introduction='这是简介哦'},

        any.ifPresent((author) -> System.out.println(author.getName()));

    }
    /**
     * 判断作家是否都没有超过100
     */
    private static void test20(){
        List<Author> authors = getAuthors();
        boolean noneMatch = authors.stream()
                .noneMatch(author -> author.getAge()>=100);
        System.out.println(noneMatch);

    }
    /**
     * 判断所有作家是否成年
     */
    private static void test19(){
        List<Author> authors = getAuthors();
        boolean allMatch = authors.stream()
                .allMatch(author -> author.getAge()>=18);
        System.out.println(allMatch);

    }
    /**
     * 判断是否有年龄在29岁以上的作家,查找匹配判断
     */
    private static void test18(){
        List<Author> authors = getAuthors();
        boolean anyMatch = authors.stream()
                .anyMatch(author -> author.getAge() > 29);
        System.out.println(anyMatch);

    }
    /**
     * 获取一个集合map，map的key为作者名，value为List<Book>
     */
    private static void test17(){
        // List<Author> authors = getAuthors();
        //          authors.stream()
        //                  .collect(Collectors.toMap(new Function<Author, String>() {
        //                      @Override
        //                      public String apply(Author author) {
        //                          return author.getName();
        //                      }
        //                  },new Function<Author,List<Book>>() {
        //                      @Override
        //                      public List<Book> apply(Author author) {
        //                          return author.getBookList();
        //                      }
        //                  }));
        List<Author> authors = getAuthors();
        Map<String, List<Book>> collect = authors.stream()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBookList()));
        System.out.println("作者名及书籍"+collect);
    }

    /**
     * 获取这些书籍名的set集合
     * 书籍集合[Book{id=7, category='久啊', name='书名6', score=45.0, introduction='这是简介哦'}, Book{id=8, category='高效', name='书名7', score=44.0, introduction='这是简介哦'}, Book{id=2, category='高效', name='书名2', score=84.0, introduction='这是简介哦'}, Book{id=3, category='喜剧', name='书名3', score=83.0, introduction='这是简介哦'}, Book{id=5, category='天啊', name='书名4', score=65.0, introduction='这是简介哦'}, Book{id=9, category='喜剧', name='书名8', score=81.0, introduction='这是简介哦'}, Book{id=1, category='类别,分类啊', name='书名1', score=45.0, introduction='这是简介哦'}, Book{id=6, category='高效', name='书名5', score=89.0, introduction='这是简介哦'}]
     */
    private static void test16(){
        List<Author> authors = getAuthors();
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .collect(Collectors.toSet());
        System.out.println("书籍集合"+books);
    }
    /**
     * 获取这些作家名字集合
     */
    private static void test15(){
        List<Author> authors = getAuthors();
        List<String> collect = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println("作家名字集合"+collect);

    }
    /**
     * 获取这些作家所出书籍的最高分和最低分并打印
     */
    private static void test14(){
        List<Author> authors = getAuthors();
        Optional<Double> max = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore())
                .max((o1, o2) -> (int) (o1 - o2));
        System.out.println("最高分"+max);


        Optional<Double> min = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .map(book -> book.getScore())
                .min((o1, o2) -> (int) (o1 - o2));
        System.out.println("最低分"+max);
    }

    /**
     * 打印这些作家的所出书籍的总数目，注意删除重复元素
     */
    private static void test13(){
        List<Author> authors = getAuthors();
        // long count = authors.stream()
        //         .flatMap(new Function<Author, Stream<?>>() {
        //             @Override
        //             public Stream<?> apply(Author author) {
        //                 return author.getBookList().stream();
        //             }
        //         })
        //         .distinct()
        //         .count();
        long count = authors.stream()
                .flatMap(author -> author.getBookList().stream())
                .distinct()
                .count();
        System.out.println("书籍数量"+count);

    }
    /**
     * 打印所有作者名字
     */
    private static void test12(){
        List<Author> authors = getAuthors();
        authors.stream()
                .map(author -> author.getName())
                .distinct()
                .forEach(n -> System.out.println(n));
    }
    /**
     * 打印现有数据的所有分类，要求对分类进行去重，不能出现这种格式：哲学 ,爱情
     */
    private static void test11(){
        List<Author> authors = getAuthors();
        authors.stream()
                .flatMap( author -> author.getBookList().stream())
                .distinct()
                .flatMap( book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(category -> System.out.println(category));
    }
    /**
     * 打印所有书籍的名字
     */
    private static void test10(){
        List<Author> authors = getAuthors();
        // authors.stream()
        //         .flatMap(new Function<Author, Stream<Book>>() {
        //             @Override
        //             public Stream<Book> apply(Author author) {
        //                 return author.getBookList().stream();
        //             }
        //         })
        //         .distinct()
        //         .forEach(new Consumer<Book>() {
        //             @Override
        //             public void accept(Book book) {
        //                 System.out.println(book.getName());
        //             }
        //         });
        authors.stream()
                .flatMap((Function<Author, Stream<Book>>) author -> author.getBookList().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));
    }
    /**
     * 跳过年龄最大的作家的姓名，打印其他
     */
    private static void test09(){
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));
    }
    /**
     * 设置流长度，年龄降序排序，不能有重复，打印年龄最大的两个作家的姓名
     */
    private static void test08(){
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(author.getAge()));
    }
    /**
     * 去重并排序
     */
    private static void test07(){
        List<Author> authors = getAuthors();
        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o1.getAge()-o2.getAge())   //.sorted倒序
                .forEach(author -> System.out.println(author.getAge()));
    }

    /**
     * 打印不重复的作家姓名
     */
    private static void test06(){
            List<Author> authors = getAuthors();
            authors.stream()
                    .distinct()
                    .forEach(author -> System.out.println(author.getName()));
    }
    /**
     * 打印所有作家年龄加10后的年龄
     */
    private static void test05() {
        List<Author> authors = getAuthors();
        // authors.stream()
        //         .map(author -> author.getName())
        //         .forEach(s -> System.out.println(s));
        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age + 10)
                .forEach(integer -> System.out.println(integer));
        

    }

    /**
     * 打印所有姓名长度大于1的作家姓名
     */
    private static void test04(){
        List<Author> authors = getAuthors();
        authors.stream()
                .filter(author -> author.getName().length()>1)
                .forEach(author -> System.out.println(author.getName()));

    }

    /**
     * 双列集合转换流
     */
    private static void test03(){
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1,"小李");
        map.put(2,"小张");
        map.put(3,"小何");
        map.entrySet().stream().filter(entry -> entry.getKey()>2)
        .forEach(integerStringEntry -> System.out.println(integerStringEntry.getValue()+"/"+integerStringEntry.getKey()));
    }

    /**
     * 数组转化流，alt+enter转换lambda表达式
     */
    private static void test02(){
        Integer[] arr={1,2,3,4,5};
        Arrays.stream(arr)
                .distinct()
                .filter(integer -> integer>2)
                .forEach(integer -> System.out.println(integer));
    }
    /**
     * 年龄降序，不能重复，打印年龄最大的两个作家名字
     */
    private static void test2() {
        List<Author> authors = getAuthors();
        authors.stream().distinct().sorted().limit(2).forEach(author -> System.out.println(author.getName()));
    }

    private static void test1() {
        List<Author> authors = getAuthors();
        // System.out.println(authors);
        authors.stream()
                .distinct()
                .filter(author -> author.getAge()<18)
                .forEach(author -> System.out.println(author.getName()));
    }


    // 初始化一些数据
    private static List<Author> getAuthors() {
        Author author1 = new Author(1L, "吴亦凡", "my introduction 1", 18, null);
        Author author2 = new Author(2L, "刘亦菲", "my introduction 2", 19, null);
        Author author3 = new Author(3L, "王源", "my introduction 3", 14, null);
        Author author4 = new Author(4L, "李", "my introduction 4", 29, null);
        Author author5 = new Author(5L, "刘亦", "my introduction 5", 12, null);

        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        // 上面是作者和书
        books1.add(new Book(1L, "类别,分类啊", "书名1", 45D, "这是简介哦"));
        books1.add(new Book(2L, "高效", "书名2", 84D, "这是简介哦"));
        books1.add(new Book(3L, "喜剧", "书名3", 83D, "这是简介哦"));

        books2.add(new Book(5L, "天啊", "书名4", 65D, "这是简介哦"));
        books2.add(new Book(6L, "高效", "书名5", 89D, "这是简介哦"));

        books3.add(new Book(7L, "久啊", "书名6", 45D, "这是简介哦"));
        books3.add(new Book(8L, "高效", "书名7", 44D, "这是简介哦"));
        books3.add(new Book(9L, "喜剧", "书名8", 81D, "这是简介哦"));

        author1.setBookList(books1);
        author2.setBookList(books2);
        author3.setBookList(books3);
        author4.setBookList(books3);
        author5.setBookList(books2);

        return new ArrayList<>(Arrays.asList(author1, author2, author3, author4, author5));
    }
}
