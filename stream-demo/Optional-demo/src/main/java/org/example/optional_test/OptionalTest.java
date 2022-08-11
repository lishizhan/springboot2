package org.example.optional_test;


import org.example.optional_test.entity.Author;
import org.example.optional_test.entity.Book;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/8 11:09
 */
public class OptionalTest {
    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Author author2 = new Author(2L, "亚拉索", 15, "狂风也追逐不上他的思考速度", null);
        Author author3 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);
        Author author4 = new Author(3L, "易", 14, "是这个世界在限制他的思维", null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L, "刀的两侧是光明与黑暗", "哲学,爱情", 88, "用一把刀划分了爱恨"));
        books1.add(new Book(2L, "一个人不能死在同一把刀下", "个人成长,爱情", 99, "讲述如何从失败中明悟真理"));

        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(3L, "那风吹不到的地方", "哲学", 85, "带你用思维去领略世界的尽头"));
        books2.add(new Book(4L, "吹或不吹", "爱情,个人传记", 56, "一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L, "你的剑就是我的剑", "爱情", 56, "无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L, "风与剑", "个人传记", 100, "两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author, author2, author3, author4));
        return authorList;
    }
    public static Optional<Author> getAuthor(){
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        return Optional.ofNullable(author);
    }

    @Test
    public void test01(){
        List<Author> authors = getAuthors();
        authors.forEach(author -> System.out.println("author = " + author));
        Optional.of(authors).ifPresent(new Consumer<List<Author>>() {
            @Override
            public void accept(List<Author> authors) {

                System.out.println("authors = " + authors);
            }
        });
    }

    /**
     * 使用Optional
     *  创建对象：
     *      Optional.ofNullable(list)
     *      Optional.of(list) //不建议使用
     */
    @Test
    public void testOfNullAble(){
//        List<Author> authors = getAuthors();
        List<Author> authors = null;
        Optional<List<Author>> optional = Optional.ofNullable(authors);
        //如果数据存在，那么将会消费数据，不会出现空指针异常
        optional.ifPresent(authors1 -> {
            authors1.stream()
                    .forEach(author -> {
                        System.out.println(author.getName());
                    });
        });
    }

    /**
     * 获取Optional对象中的值
     */
    @Test
    public void testGet() throws Throwable {
        Author author = new Author(1L, "蒙多", 33, "一个从菜刀中明悟哲理的祖安人", null);
        Optional<Author> optional = Optional.ofNullable(author);
        //Author author1 = optional.get();//不建议使用get方法获取值
        //System.out.println("authors = " + author1);
        /**
         * 获取数据并设置数据为空时的默认值，如果数据不为空就获取该数据，数据为空就使用传入的参数来创建对象作为默认值返回
         */
        /*Author author2 = optional.orElseGet(new Supplier<Author>() {
            @Override
            public Author get() {
                //没有值就返回默认值
                return new Author();
            }
        });
        System.out.println("author1 = " + author2);*/
        /**
         * 获取数据，如果数据不为空就可以获取该数据，如果数据为空则会根据你传入的参数来创建异常
         * 好处：抛出异常，全局捕获
         */
        Author author1 = optional.orElseThrow(new Supplier<Throwable>() {
            @Override
            public Throwable get() {
                //如果数据为空则会抛出异常
                return new RuntimeException("数据为空");
            }
        });
        System.out.println("author1 = " + author1);

    }

    /**
     * 使用Optional过滤值
     * 可以使用filter方法对数据进行过滤，如果原泵有数据，但不符合判断，也会变成一个无数据的Optional对象
     */
    @Test
    public void testFilter(){
        Optional<Author> optional = getAuthor();
        optional.filter(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                //是否符合条件，符合才会被消费
                return author.getAge() < 100;
            }
        }).ifPresent(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println("author = " + author);
            }
        });
    }

    /**
     * 判断
     *
     * 可以使用isPresent方法进行数据是否存在的判断，但这种方式不推荐，建议使用ifPresent
     *  为空：false
     *  不为空：true
     *
     */
    @Test
    public void test(){
        Optional<Author> optional = getAuthor();
        if (optional.isPresent()){
            //数据不为空可以进行消费
            Author author = optional.get();
            System.out.println("author = " + author);
        }
        //使用ifPresent
        optional.ifPresent(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                //数据不为空可以进行消费
                System.out.println("author = " + author);
            }
        });
    }
    /**
     * 数据转换
     * Optional提供的map可以让我们对数据进行数据转换，并且转换好的数据也是Optional包装好的，保证使用安全
     */
    @Test
    public void testMap(){
        Optional<Author> optional = getAuthor();
        optional.map(new Function<Author, Integer>() {
            @Override
            public Integer apply(Author author) {
                return author.getAge();
            }
        }).ifPresent(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                System.out.println("integer = " + integer);
            }
        });


    }





}


