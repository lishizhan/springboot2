package org.example.lambda;

import lombok.extern.slf4j.Slf4j;
import org.example.lambda.entity.Author;
import org.example.lambda.entity.Book;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lishizhan
 * @version 1.0
 * @description: TODO
 * @date 2022/8/3 10:34
 */
public class StreamTest {
    @Test
    public void stream01Test() {
        this.authorList.forEach(author -> System.out.println("author = " + author));
    }

    private List<Author> authorList = null;
    private Map<String, List<Book>> authorMap = new HashMap<>();

    @Before
    public void init() {
        authorList = getAuthors();
    }

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

    private static Map<String, List<Book>> getAuthorsMap() {

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
        HashMap<String, List<Book>> map = new HashMap<>();
        map.put("book1", books1);
        map.put("book2", books2);
        map.put("book3", books3);

        return map;
    }

    /**
     * 我们可以调用getAuthors方法获取到作家的集合
     * 需求答应所有年龄小于18的作家民资，并且去重
     */
    @Test
    public void test01() {
        this.authorList
                .stream() //把集合转化为流
                .distinct() //去重
                .filter(new Predicate<Author>() {  // 筛选年龄小于18
                    @Override
                    public boolean test(Author author) {
                        return author.getAge() > 18;
                    }
                })
                .forEach(new Consumer<Author>() { //遍历打印
                    @Override
                    public void accept(Author author) {
                        System.out.println("author = " + author);
                    }
                });
    }

    /**
     * 流从常规操作
     * 1，如何流
     * > 单列集合 集合对象.Stream
     * > 数组    Arrays.Stream(数组) or Stream.of
     * > 双列集合 转化为单列集合后再创建
     */
    //单列集合
    @Test
    public void listStreamTest() {
        List<Author> authors = this.authorList;
        Stream<Author> stream = authors.stream();
        stream.distinct().forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println("author = " + author);
            }
        });
    }

    //数组
    @Test
    public void arrStreamTest() {
        String[] arr = {"java", "hello", "c++", "python"};
        Stream<String> stream = Arrays.stream(arr);
        //Stream<String> stream1 = Stream.of(arr);
        stream.filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        }).forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println("s = " + s);
            }
        });
    }

    //双列集合
    @Test
    public void mapStreamTest01() {
        Map<String, String> map = new HashMap<>();
        map.put("001", "1231");
        map.put("002", "hello");
        map.put("003", "java");
        map.put("004", "c++");

        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> stream = entries.stream();
        stream.filter(new Predicate<Map.Entry<String, String>>() {
            @Override
            public boolean test(Map.Entry<String, String> stringStringEntry) {
                return stringStringEntry.getValue().equals("java");
            }
        }).forEach(new Consumer<Map.Entry<String, String>>() {
            @Override
            public void accept(Map.Entry<String, String> stringStringEntry) {
                System.out.println("stringStringEntry = " + stringStringEntry);
            }
        });
    }

    /**
     * Stream流中的中间操作
     * filter：可以对流中的元素进行条件过滤，满足条件的才可以继续保留在流中
     * map：可以对流中的袁术进行计算或转换
     * distinct: 去重
     * sorted：排序，
     * limit：限制流的长度
     * skip：跳过流中的几个
     */
    @Test
    public void filterTest() {
        List<Author> authors = this.authorList;
        authors.stream().filter(
                new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getName().length() > 1;
                    }
                }
        ).forEach(new Consumer<Author>() {
            @Override
            public void accept(Author author) {
                System.out.println("author = " + author);
            }
        });

    }

    @Test
    public void mapTest() {
        //打印所有作家的姓名
        List<Author> authors = this.authorList;
        authors.stream().map(new Function<Author, Object>() {
            @Override
            public Object apply(Author author) {
                return author.getName();
            }
        }).forEach(new Consumer<Object>() {
            @Override
            public void accept(Object o) {
                System.out.println(o);
            }
        });

    }

    @Test
    public void sortedTest() {
        List<Author> authors = this.authorList;

        authors.stream()
                //.sorted() 注意需要实现comparable 如何比较两个对象
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o1.getAge() - o2.getAge();
                    }
                })
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println("author = " + author);
                    }
                });
    }

    /**
     * 获取年龄最大的两个2作家
     */
    @Test
    public void limitTest() {

        List<Author> authors = this.authorList;
        authors.stream().sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                }).limit(2)
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println("author = " + author);
                    }
                });


    }

    @Test
    public void skipTest() {
        List<Author> authors = this.authorList;
        authors.stream()
                .sorted(new Comparator<Author>() {
                    @Override
                    public int compare(Author o1, Author o2) {
                        return o2.getAge() - o1.getAge();
                    }
                })
                .skip(1)
                .forEach(new Consumer<Author>() {
                    @Override
                    public void accept(Author author) {
                        System.out.println("author = " + author);
                    }
                });
    }
    @Test
    public void anyMatch(){
        //判断是否有28岁以上的作家
        List<Author> authors = this.authorList;
        boolean anyMatch = authors.stream().anyMatch(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() > 28;
            }
        });
        System.out.println("是否有28岁以上的作家 = " + anyMatch);
        //判断所有的作家都是陈年人
        boolean allMatch = authors.stream().allMatch(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() >= 18;
            }
        });
        System.out.println("是否所有的作家都是陈年人 = " + allMatch);
        //判断所有的作家都没有超过100岁
        boolean noneMatch = authors.stream().noneMatch(new Predicate<Author>() {
            @Override
            public boolean test(Author author) {
                return author.getAge() >= 100;
            }
        });
        System.out.println("判断所有的作家都没有超过100岁 = " + noneMatch);
    }

    @Test
    public void findAny(){
        //获取流中的任意一个元素，该方法无法保证获取的元素一定是流中的第一个
        //获取任意一个年龄大于18的作家并且输出名字
        Optional<Author> any = this.authorList
                .stream()
                .filter(new Predicate<Author>() {
                    @Override
                    public boolean test(Author author) {
                        return author.getAge()>18;
                    }
                })
                .findAny();
        any.ifPresent(new Consumer<Author>() {
            //如果有数据就慢慢消费，没有就不会执行该方法
            @Override
            public void accept(Author author) {

                System.out.println("获取流中的任意一个元素 = " + author.getName());
            }
        });
    }

    /**
     * Optional：可以避免空指针异常
     * */
    @Test
    public void optionalTest() throws Throwable {
        List<Author> authors =this.authorList;
        Optional<List<Author>> authors1 = Optional.ofNullable(authors);
        //这种获取值不可取
        //List<Author> authors2 = authors1.get();
        //安全获取值
        List<Author> list = authors1.orElseGet(new Supplier<List<Author>>() {
            //判断是否有值，没有则使用默认值
            @Override
            public List<Author> get() {
                return new ArrayList<>();
            }
        });
        System.out.println("list = " + list);

        List<Author> authors2 = authors1.orElseThrow(new Supplier<Throwable>() {
            //如果没有内部值就抛出异常
            @Override
            public Throwable get() {
                return new RuntimeException("集合为空！");
            }
        });
        System.out.println("authors2 = " + authors2);

    }

}
