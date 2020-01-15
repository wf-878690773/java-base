package com.wf.stream;

import com.wf.bean.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * java8新特性，
 * 接口默认方法，lambda表达式，函数式接口，Date API等特性
 *
 * Stream作为java8的新特性，基于lambda表达式，是对集合对象功能的增强
 */
public class StreamDemo1 {
    /**
     * Stream的原理：将要处理的元素看做一种流，流在管道中传输，并且可以在管道的节点上处理，
     * 包括过滤筛选、去重、排序、聚合等。元素流在管道中经过中间操作的处理，
     * 最后由最终操作得到前面处理的结果。
     *
     * 中间操作主要有以下方法（此类型方法返回的都是Stream）：map (mapToInt, flatMap 等)、 filter、
     * distinct、 sorted、 peek、 limit（限制返回个数）、 skip(删除元素)、 parallel、 sequential、 unordered
     *
     * 终止操作主要有以下方法：forEach、 forEachOrdered、 toArray、 reduce(聚合)、 collect、 min(求最小值)、 max、
     * count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
     * @param args
     */
    public static void main(String[] args) {

        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        //Lambda表达式简化变量
        students.forEach(System.out::println);

        System.out.println("-------------------------");
        List<Student> students1 = testFilter(students);
        students1.forEach(System.out::println);

        System.out.println("-------------------------");
        testMap(students);

        System.out.println("-------------------------");
        testDistinct1();

        System.out.println("-------------------------");
        testDistinct2();

        System.out.println("-------------------------");
        testSort1();

        System.out.println("-------------------------");
        testSort2();

        System.out.println("-------------------------");
        testReduce();

        System.out.println("-------------------------");
        testMatch();
    }

    /**
     * 筛选
     * @param students
     * @return
     */
    private static List<Student> testFilter(List<Student> students){

//      return students.stream().filter(student -> student.getAge()>15).collect(Collectors.toList());
        return students.stream().filter(student -> "北京".equals(student.getAddress())).collect(Collectors.toList());
    }

    /**
     * 集合转换
     * map就是将对应的元素按照给定的方法进行转换。
     * @param students
     * @return
     */
    private static void testMap(List<Student> students){
        List<String> address = students.stream().map(student -> "住址:" + student.getAddress()).collect(Collectors.toList());
        address.forEach(s -> System.out.println(s));
    }


    /**
     * 集合去重（基本类型）
     */
    private static void testDistinct1(){
        List<String> list = Arrays.asList("111", "222", "111", "333");
        list.forEach(System.out::println);
        System.out.println("=============");
        list.stream().distinct().forEach(System.out::println);
    }

    /**
     * 集合去重（引用对象）
     */
    private static void testDistinct2(){
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        Student s5 = new Student(1L, "肖战", 15, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        students.add(s5);
        students.stream().distinct().forEach(System.out::println);
    }

    private static void testSort1(){
        List<String> list = Arrays.asList("333","222","111");

        list.stream().sorted().forEach(System.out::println);
    }

    private static void testSort2() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);
        //上面指定排序规则，先按照学生的id进行降序排序，再按照年龄进行降序排序
        students.stream()
                .sorted((stu1,stu2) -> Integer.compare(stu2.getAge(),stu1.getAge()))
                .forEach(System.out::println);
    }

    /**
     * reduce(聚合)
     */
    private static void testReduce(){
        List<String> list = Arrays.asList("欢","迎","你");
        String reduce = list.stream().reduce("北京", (s, s2) -> s + s2);
        System.out.println(reduce);
    }

    private static void testMatch() {
        Student s1 = new Student(1L, "肖战", 15, "浙江");
        Student s2 = new Student(2L, "王一博", 15, "湖北");
        Student s3 = new Student(3L, "杨紫", 17, "北京");
        Student s4 = new Student(4L, "李现", 17, "浙江");
        List<Student> students = new ArrayList<>();
        students.add(s1);
        students.add(s2);
        students.add(s3);
        students.add(s4);

        if (students.stream().anyMatch(student -> "北京".equals(student.getAddress()))){

            System.out.println("北京");
        }
        if (students.stream().anyMatch(student -> student.getAge()>=15)){
            System.out.println("大于15");
        }
        if (students.stream().noneMatch(student -> "小明".equals(student.getName()))){
            System.out.println("没有叫小明的");
        }


    }




}
