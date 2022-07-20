package io.adarsh.springdatajpaexp.JavaCollectionAndEssentials;

import io.adarsh.springdatajpaexp.model.Project;


import java.util.*;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ObjectReferenceArray extends Project implements Comparable<ObjectReferenceArray>, Iterable<String> {

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
        System.out.println("Called");
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
        return new Iterator() {

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
        int[] intArray1 = new int[]{10, 20, 140, 160, 180, 300};
        int[] intArray2 = new int[]{10, 20, 140, 160, 180, 300};
        int mismatchCount = Arrays.mismatch(intArray1, intArray2);
        System.out.println(mismatchCount);
        // Sorting----
        List<Integer> numberList = Arrays.asList(100, 1, 21, 2, 200, 9);
        numberList.forEach(value -> System.out.print(value + " "));
        System.out.println("");
        System.out.println("Sorting numberList");
        Iterator<Integer> iterator = numberList.iterator();
//        iterator.next();
        numberList.sort(null);
//        System.out.println("NumberList after calling sort(null)");
//        numberList.sort((o1, o2) -> o2 - o1);
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

        // Converting our object into type Iterable so that it can work with for-each loop
        // and also providing the Iterator object so that for-each loop can call hasNext() and next() method for performing for-each loop
        ObjectReferenceArray obj = new ObjectReferenceArray(10, "A");
        int count = 0;
        for (String element : obj) {
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
// Iterator ----
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

        List<ObjectReferenceArray> objectList = Arrays.asList(
                new ObjectReferenceArray(100, "Ankit"),
                new ObjectReferenceArray(10, "Adarsh"),
                new ObjectReferenceArray(101, "Shubham"),
                new ObjectReferenceArray(90, "Prashant")
        );

        System.out.println("Printing object list: ");
        objectList.forEach(System.out::println);

        System.out.println("Sorting object list using sort()");
        objectList.sort(null);
        System.out.println("Printing object list after sorting");
        objectList.forEach(System.out::println);

        Set<Integer> numberSet = new HashSet<>();
        numberSet.add(10);
        numberSet.add(100);
        numberSet.add(11);
        numberSet.add(1111);
        numberSet.add(121);
        System.out.println("Printing numberList before sorting");
        numberSet.forEach(value -> System.out.print(value + ", "));
        Stream<Integer> sorted = numberSet.stream().sorted((val1, val2) -> val1 - val2);
        System.out.println("Printing numberList after sorting");
        sorted.forEach(value -> System.out.print(value + ", "));
        // now if we print the numberSet then it will print the values in the normal sequence without sorting
        // because when we collect Stream<Integer> to Set then it follows the Set adding mechanism
        // and add up the values in the set according the hashing and hashcode mechanism and that's why
        // now printing of set after collecting into set through sorted Stream<Integer> will print the set in normal format as it was earlier unordered(original)
//        numberSet = sorted.collect(Collectors.toSet());

        Set<ObjectReferenceArray> objectSet = new HashSet<>();
        objectSet.add(new ObjectReferenceArray(10, "Zakir"));
        objectSet.add(new ObjectReferenceArray(101, "Adarsh"));
        objectSet.add(new ObjectReferenceArray(1, "Shubham"));
        objectSet.add(new ObjectReferenceArray(110, "Tushar"));
        objectSet.add(new ObjectReferenceArray(190, "Rahul"));
        objectSet.add(new ObjectReferenceArray(0, "Zakir"));

        System.out.println("Printing object set before sorting");
        objectSet.forEach(System.out::println);
        // *** If we are using Comparator.reverseOrder() or Comparator.naturalOrder() then we have to implement the Comparable Interface
        // internally calls Comparator.naturalOrder() which have a syntax like
//        compare(Comparable obj1, Comparable obj2) {
//            return obj1.compareTo(obj2);
//        }
//        Stream<ObjectReferenceArray> sortedSetStream = objectSet.stream().sorted();
        Stream<ObjectReferenceArray> sortedSetStream = objectSet
                .stream()
                .sorted((obj1, obj2) -> { // If we provide our custom comparator then it's not compulsory to implement Comparable
                    System.out.println("Comparator called");
                    return obj1.getRollNumber() - obj2.getRollNumber();
                });
//                .sorted(Comparator.reverseOrder()); // internally calls compareTo ->
//        compare(Comparable obj1, Comparable obj2) {
//            return obj2.compareTo(obj1);
//        }
//                .sorted((obj1, obj2) -> obj1.getRollNumber() - obj2.getRollNumber());
        System.out.println(sortedSetStream.getClass().getName());
        System.out.println("Printing values of set after sorting");

        // *** Stream<ObjectReferenceArray> sortedSetStream; as this point this hasn't called the comparator
        // and the Stream<ObjectReferenceArray> sortedSetStream object contains which comparator to call and all that
        sortedSetStream.forEach(System.out::println); // when we access the data from the Stream<ObjectReferenceArray> sortedSetStream
        // at this point it will call the comparator and sort the values and then provide them.
        System.out.println("Printing original set");
        objectSet.forEach(System.out::println);

        // immutable objects before java 9
        List<String> mutableList = new ArrayList<>();
        mutableList.add("Adarsh");
        List<String> immutableList = Collections.unmodifiableList(mutableList);
        // other immutable objects can also be created using Collections.unmodifiableSet(new HashSet<>());
        System.out.println(immutableList.getClass().getName());
        // List which we have passed for immutability, add and other update methods can be called over that list
        // because Collections.unmodifiableList returns a new instance of type UnmodifiableRandomAccessList
        mutableList.add("Chicky");
        // calling add and other update operation on immutableList will throw UnsupportedOperationException
//        immutuableList.add("Chicky");

        // immutable objects from java 9
        List<String> immutableList1 = List.of("Adarsh");
        // will throw an exception UnsupportedOperationException on add and update operations on immutableList
//        immutableList1.add("Verma");

        System.out.println("Size of the mutable list: " + mutableList.size());
        System.out.println("Getting iterator of mutableList:");
        Iterator<String> mutableListIterator = mutableList.iterator();
        System.out.println("Adding an element after creating the iterator so that the mutuableList gets modified");
        mutableList.add("Shubham");
        System.out.println("Now accessing the iterator after differing the state of mutableList with mutableListIterator");
        // As Iterator maintains their own state, so it will throw an exception ConcurrentModificationException
        // if in between the state of the original list gets changed.
//        mutableListIterator.next();

        collectionTest();
        listTest();
    }

    @Override
    public int compareTo(ObjectReferenceArray o) {
//        return this.name.compareTo(o.name);
        return o.getRollNumber() - this.getRollNumber();
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

    public static void collectionTest() {
        System.out.println("Collection interface methods:");
        List<String> collection1 = new ArrayList<>();
        collection1.add("Adarsh");
        collection1.add("Verma");
        List<String> collection2 = new ArrayList<>();
        collection2.addAll(collection1);
//        System.out.println("Contents in collection2 after calling addAll(collection1)");
        collection2.add("Verma");
        collection2.add("Chicky");
//        collection2.removeAll(collection1);
//        System.out.println("Contents in collection2 after calling removeAll(collection1)");
        System.out.println("Contents in collection2 will be same as in contents of collection1 after calling retainAll(collection1)");
        collection2.retainAll(collection1);
        System.out.println("Contents in collection2 gets cleared out, there will be no elements in the collection2 after calling clear()");
        System.out.println("collection2 size before calling clear(): " + collection2.size());
        System.out.println(collection2);
        List<String> immutableList = List.of("Verma");
//        collection2.remove("Verma"); // remove the first element it found, not all duplicates
        collection2.removeAll(immutableList); // remove all the duplicates
        System.out.println(collection2);
        collection2.clear();
        System.out.println("collection2 size after calling clear(): " + collection2.size());
        System.out.println(collection2);

        HashSet<String> stringHashSet = new HashSet<>();
        stringHashSet.addAll(collection1);
        collection2.addAll(stringHashSet);

        // removing all duplicates from a list  using a set
        List<Integer> integerList = Arrays.asList(1, 1, 1, 2, 2, 7, 7, 89, 90, 89, 91, 90, 91)
                .stream()
                .collect(Collectors.toSet())
                .stream().collect(Collectors.toList());

        // can also be written as
        List<Integer> integers = new ArrayList<>(
                new HashSet<>(Arrays.asList(1, 1, 1, 2, 2, 7, 7, 89, 90, 89, 91, 90, 91)));

        System.out.println(integerList);
    }

    public static void listTest() {
        System.out.println("List interface methods");
        List<String> nullCheckList = new ArrayList<>();
        nullCheckList.add(null);
        nullCheckList.add(null);
        nullCheckList.add("Test");
        System.out.println(nullCheckList);
        nullCheckList.replaceAll((value) -> "Constant");
        System.out.println("Replaced");
        System.out.println(nullCheckList);
        // initialized with an empty array instance of size = 0
        Object[] objectArray = {};
        System.out.println(objectArray.length);
        ObjectReferenceArray object = new ObjectReferenceArray(10, "");
//        Project project = object;

        Project project = object;
        // throw run time exception Class cast exception
        // If we don't provide the cast here then the compiler will throw an error but by providing cast
        // So the compiler is forced to trust the casting
//        ObjectReferenceArray objectReferenceArray = (ObjectReferenceArray) new Project();
        // compile time error
//        ObjectReferenceArray objectReferenceArray = new Project();
        ObjectReferenceArray objectReferenceArray1 = (ObjectReferenceArray) project;
        // compiler will throw an error because at compile time compiler can only
        // that we are casting parent object to child object, but we are parsing the child object can be sure at run-time only
//        ObjectReferenceArray objectReferenceArray = getProject();
        ObjectReferenceArray objectReferenceArray = (ObjectReferenceArray) getProject();
        Project project1 = (Project) object;
        // by default instacneOf equals check of string will return false
        System.out.println(nullCheckList.contains(new ObjectReferenceArray(1, "")));

        List<ObjectReferenceArray> list = new ArrayList<>();
        list.add(new ObjectReferenceArray(10, ""));
        System.out.println("Checking for different type in contains");
        System.out.println(list.contains(""));
        List<ObjectReferenceArray> objectReferenceArrayList = list.subList(0, 1);
        objectReferenceArrayList.add(new ObjectReferenceArray(1, ""));
        System.out.println(objectReferenceArrayList);

//        Iterator<ObjectReferenceArray> iterator = objectReferenceArrayList.iterator();
//        while (iterator.hasNext()) {
//            ObjectReferenceArray obj = iterator.next();
//            obj.setRollNumber(100000);
//        }
        System.out.println(objectReferenceArrayList);

        List<Integer> integers = new ArrayList<>(
                new HashSet<>(Arrays.asList(1, 1, 1, 2, 2, 7, 7, 89, 90, 89, 91, 90, 91)));
        System.out.println(integers);
        integers.addAll(1, Arrays.asList(0, 0, 0));
        System.out.println(integers);
        integers.add(0, 100);
        System.out.println(integers);
        Stream<Integer> removing_value = integers.stream()
                .filter(value -> {
                    System.out.println("removing value");
                    integers.remove(value);
                    return false;
                });
        System.out.println(removing_value);
        // will throw error because stream gets activated when it comes to an end.
//        removing_value.forEach(System.out::println);
        System.out.println("Values: " + integers);

        Project[] projectArray = new Project[10];
        listAdd(0, projectArray, new ObjectReferenceArray(1, "Adarsh"));
        listAdd(1, projectArray, new ObjectReferenceArray(2, "Chicky"));
        System.out.println(getList(0, projectArray));
        System.out.println(getList(1, projectArray));
        final Project[] finalProjectArray =projectArray;
        finalProjectArray[1] = null;
        System.out.println(getList(0, finalProjectArray));
        System.out.println(getList(1, finalProjectArray));
        // updation over the final array  can be done but the reference variable can not be replace with other array instance
//        finalProjectArray = new Project[4];
    }

    public static void listAdd(int index, Project[] projectArray, ObjectReferenceArray objectReferenceArray) {
        projectArray[index] = objectReferenceArray;
    }

    public static ObjectReferenceArray getList(int index, Project[] projectArray) {
        return (ObjectReferenceArray) projectArray[index];
    }

    public static Project getProject() {
        return new ObjectReferenceArray(1, "");
    }

    public void ClassThisTest() {
        ObjectReferenceArray.this.setRollNumber(10);
    }


}
