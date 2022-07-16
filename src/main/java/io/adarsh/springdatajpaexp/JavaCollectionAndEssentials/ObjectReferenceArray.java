package io.adarsh.springdatajpaexp.JavaCollectionAndEssentials;

import io.adarsh.springdatajpaexp.model.Project;


import java.util.*;


public class ObjectReferenceArray implements Comparable<ObjectReferenceArray>, Iterable<String> {

     int rollNumber;
    private String name;

    public ObjectReferenceArray(int rollNumber, String name) {
        this.rollNumber = rollNumber;
        this.name = name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // By Java 7
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectReferenceArray strings = (ObjectReferenceArray) o;
        return rollNumber == strings.rollNumber && name.equals(strings.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rollNumber, name);
    }

    //  Intellij Default
    //    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ObjectReferenceArray strings = (ObjectReferenceArray) o;
//
//        if (rollNumber != strings.rollNumber) return false;
//        return name.equals(strings.name);
//    }
//
//    @Override
//    public int hashCode() {
//        int result = rollNumber;
//        result = 31 * result + name.hashCode();
//        return result;
//    }

    @Override
    public Iterator<String> iterator() {
        return new Iterator(){

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Object next() {
                return "Adarsh";
            }
        };
    }

    class InnerClass {
        private int b;

        public void perform() {
            Object o = new Object();
        }
    }
    public static void main(String[] args) {

        Object[] objectReferenceArray = new Object[5];
        objectReferenceArray[0] = new Object();
        objectReferenceArray[1] = new Object();
        objectReferenceArray[2] = new Object();
        objectReferenceArray[3] = new Object();
        objectReferenceArray[4] = new ObjectReferenceArray(10, "");



//        new ArrayList<String>().forEach();
//        Iterator<String> iterator = new LinkedList<String>().forEach();
//        System.out.println(iterator.getClass().getName());
        Object object1 = new Object();
        System.out.println("Passing normal object in println" + object1);
        System.out.println("Printing hashcode: " + object1.hashCode());
        System.out.println("Converting hashcode into hexString: " + Integer.toHexString(object1.hashCode()));
//        for (Object o : objectReferenceArray)
//            System.out.println(o.hashCode());
        int []intArray1 = new int[]{10, 20, 140, 160, 180, 300};
        int []intArray2 = new int[]{10, 20, 140, 160, 180, 300};
        int mismatchCount = Arrays.mismatch(intArray1, intArray2);
        System.out.println(mismatchCount);

        List<Integer> numberList = Arrays.asList(100, 1, 21, 2, 200, 9);
        numberList.forEach(value -> System.out.print(value + " "));
        System.out.println("");
        System.out.println("Sorting numberList");
        Iterator<Integer> iterator = numberList.iterator();
//        iterator.next();
//        numberList.sort(null);
//        System.out.println("NumberList after calling sort(null)");
        numberList.sort((o1, o2) -> o2 - o1);
        numberList.forEach(value -> System.out.print(value + " "));

        System.out.println();
        List<String> stringList = Arrays.asList("a", "", "aa", "aaaaaa", "aaaa");
        stringList.forEach(value -> System.out.print(value + ", "));
        System.out.println("");
//        System.out.println("Normal sorting the stringList by calling sort(null)");
//        stringList.sort(null);
        stringList.sort((o1, o2) -> o2.compareTo(o1));
        // can also be written as
//        stringList.sort(Comparator.reverseOrder());
        // can also be written as
//        stringList.sort(Collections.reverseOrder());
        System.out.println("After sorting");
        stringList.forEach(value -> System.out.print(value + ", "));

        List<Project> projectList = new ArrayList<>();
        projectList.sort((project1, project2) -> project1.getT() - project2.getT());

        ObjectReferenceArray obj = new ObjectReferenceArray(10, "A");
        int count = 0;
        for (String element: obj) {
            if (count == 9)
                break;
            System.out.println(element);
            count++;
        }
        List<ObjectReferenceArray> objectArrayList = new ArrayList<>();
        objectArrayList.add(new ObjectReferenceArray(10, "Adarsh"));
        objectArrayList.add(new ObjectReferenceArray(1, "Chicky"));
        System.out.println("Before sorting");
        objectArrayList.forEach(System.out::println);
//        Arrays.sort(objectArrayList.toArray());
//        objectArrayList.sort(null);
        objectArrayList.sort((o1, o2) -> o1.getRollNumber() - o2.getRollNumber());
        System.out.println("After sorting");
        objectArrayList.forEach(System.out::println);

        List<String> stringLinkedList = new LinkedList<>();
//        stringLinkedList.add(3, "");

        Set<String> stringSet = new HashSet<String>();
        stringSet.add("");

        System.out.println("Trying to throw concurrent modification exception");
        numberList.forEach(number -> {
            System.out.println("Deleting number: " + number);
//            numberList.remove(number);
        });

        System.out.println("IntegerList is an object of type: " + numberList.getClass().getName());
        System.out.println("Iterator is of class: " + iterator.getClass().getName());
        List<Integer> integerList = new ArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        integerList.add(3);
        System.out.println("IntegerList is an object of type: " + integerList.getClass().getName());
        Iterator<Integer> iterator1 = integerList.iterator();
        System.out.println("Iterator1 is of class: " + iterator.getClass().getName());
        System.out.println("StringSet is an object of type: " + stringSet.getClass().getName());
        Iterator<String> iterator2 = stringSet.iterator();
        System.out.println("Iterator2 is of class: " + iterator2.getClass().getName());

        Map<String, String> hashMap = new HashMap<String, String>();
        Collection<String> values = hashMap.values();
        System.out.println("Values is of class type: " + values.getClass().getName());
        System.out.println("Values iterator is of class type: " + values.iterator().getClass().getName());
        System.out.println("Hashmap keys iterator is of class type: " + hashMap.keySet().iterator().getClass().getName());
//        iterator.remove();

        System.out.println("String Linked list iterator is of class type: " + stringLinkedList.iterator().getClass().getName());
        while (iterator.hasNext()) {
            iterator.next();
//            iterator.remove();
            break;
        }

        ObjectReferenceArray objectReferenceArray1 = new ObjectReferenceArray(10, "");

        System.out.println(new ObjectReferenceArray(10, "").hashCode());
        System.out.println(new ObjectReferenceArray(10, "").hashCode());

        List<ObjectReferenceArray> list = new ArrayList<>();
        list.add(new ObjectReferenceArray(10, "Adarsh"));
        list.add(new ObjectReferenceArray(10, "Adars"));

        System.out.println(list.contains(new ObjectReferenceArray(10, "Adar")));

        Set<ObjectReferenceArray> set = new HashSet<>();
        set.add(new ObjectReferenceArray(10, "Adarsh"));
        set.add(new ObjectReferenceArray(10, "Adarsh"));
        System.out.println("Set size is: " + set.size());
        System.out.println(set);
        System.out.println(set.contains(new ObjectReferenceArray(10, "Adarsh")));
    }

    @Override
    public int compareTo(ObjectReferenceArray o) {
        return this.getRollNumber() - o.getRollNumber();
    }
   /* @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer action) {
        Iterable.super.forEach(action);
    }

    @Override
    public Spliterator spliterator() {
        return Iterable.super.spliterator();
    }*/

    @Override
    public String toString() {
        return "ObjectReferenceArray{" +
                "rollNumber=" + rollNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
