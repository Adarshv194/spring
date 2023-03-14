package io.adarsh.springdatajpaexp.code;

import io.adarsh.springdatajpaexp.model.Employee;

import java.nio.ByteBuffer;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class Test {

    public static void main(String[] args) {
        collectionTest();
//        System.out.println(hackerrankInString("hereiamstackerrank"));
//        printSeries(8);
//        int start = 8;
//        int end = 50;
//        System.out.println("Total number of prime numbers in between: " + start + " and " + end + " are: " + numberOfPrimeNumbers(start, end));
//        primeNumbers(7);
//        System.out.println("Entered number is palindrome or not: " + palindromeOrNot(3663));
//        System.out.println("Entered inputs are anagram or not result: " + anagramOrNot("Clint Eastwood", "old west action"));
//        System.out.println("Entered inputs are anagram or not result: " + anagramOrNot("adarsh", "daarsh"));
    }

    public static void collectionTest() {
        List<Employee> employees = Arrays.asList(
                new Employee("Adarsh", Arrays.asList(1, 2, 3, 4, 5), 85000),
                new Employee("Ankit", Arrays.asList(6, 7, 8, 9, 10), 815000),
                new Employee("Adarsh", Arrays.asList(60, 70, 80, 90, 100), 820000),
                new Employee("Ankit", Arrays.asList(6, 7, 8, 9, 10), 800001)
        );

        Comparator<Employee> comparator = Comparator.comparing((employee) -> employee.getSalary());

        Map<String, Optional<Employee>> collect4 = employees.stream()
                .collect(
                        Collectors.groupingBy((employee -> employee.getName()),
                                Collectors.reducing(BinaryOperator.maxBy(comparator))
                        )
                );

        System.out.println(collect4);

        Map<String, Employee> collect5 = employees.stream()
                .collect(
                        Collectors.groupingBy(
                                (employee -> employee.getName()),
                                Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)),
                                        Optional::get)
                        )
                );
        System.out.println(collect5);

        String input = "Adarsh";

        Map<String, Long> collect3 = Arrays.stream(input.split(""))
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                );

        Map<Employee, Long> collect2 = employees.stream()
                .collect(
                        Collectors.groupingBy(Function.identity(), Collectors.counting())
//                        Collectors.groupingBy(Function.identity(), TreeMap::new,Collectors.counting())
//                        Collectors.groupingBy(employee -> employee.getSalary())
                );
        System.out.println(collect2);

        Map<Employee, Integer> collect1 = employees.stream().collect(Collectors.toMap(Function.identity(), (employee) -> employee.getSalary()));

        employees.stream()
                .filter(employee -> {
                    System.out.println("Filter Intermediate method for: " + employee);
                    return employee.getSalary() == 80000? true: false;
                })
                .sorted((e1, e2) -> {
                    System.out.println("Sorted Called");
                    return e1.getSalary() - e2.getSalary();
                })
                .map(employee -> {
                    System.out.println("Map intermediate method for: " + employee);
                    return employee.getName();
                })
                .filter(name -> {
                    System.out.println("Filter Intermediate method for: " + name);
                    return name!=null? true: false;
                })
                .collect(Collectors.toList());



        Spliterator<Employee> employeeSpliterator = employees.spliterator();
        System.out.println(employeeSpliterator.estimateSize());
        Spliterator<Employee> employeeSpliteratorTrySplit = employeeSpliterator.trySplit();
        System.out.println(employeeSpliteratorTrySplit.estimateSize());
        Spliterator<Employee> employeeSpliterator1 = employeeSpliteratorTrySplit.trySplit();
        System.out.println(employeeSpliterator1.estimateSize());
        Spliterator<Employee> employeeSpliterator2 = employeeSpliterator1.trySplit();
        // if there is no element to split it will return null
        System.out.println(employeeSpliterator2);

        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Adarsh");
        stringList.add("Adarsh");
        stringList.add("Chicky");
        System.out.println(stringList.stream().getClass().getName());
        System.out.println(stringList.spliterator().getClass().getName());
        System.out.println(stringList.stream().spliterator().getClass().getName());
        Stream<String> distinct = stringList.stream().distinct();
        distinct.forEach(System.out::println);

        List<String> collect = stringList
                .stream()
                .collect(Collectors.toList());

        Stream<String> stream = null;
        stream.collect(Collectors.toList());

//        Collector<? extends T, A, R>
        Collector<Object, ?, List<Object>> objectListCollector = Collectors.toList();
        Collector<Object, ?, Set<Object>> objectSetCollector = Collectors.toSet();

        Function<String, Integer> function = (value) -> {
          return 0;
        };




        Stream<Employee> employeeStream = employees.stream();
        Spliterator<Employee> spliterator1 = employees.spliterator();
        System.out.println(spliterator1.getClass().getName());
        for (Spliterator<Employee> spliterator = employees.spliterator();
             spliterator.tryAdvance((value) -> System.out.println(value)) != false;
        )
            continue;
    }
    
    public static void test() {
        List<Employee> employees = Arrays.asList(
                new Employee("Tushar", Arrays.asList(1, 2, 3, 4, 5), 35000),
                new Employee("Suraj", Arrays.asList(6, 7, 8, 9, 10), 40000),
                new Employee("Chicky", Arrays.asList(60, 70, 80, 90, 100), 400000),
                new Employee("Ankit", Arrays.asList(6, 7, 8, 9, 10), 4000000)
        );

        Stream<Employee> employeeStream = employees.stream();

        // takes a value and convert it into another value
        Stream<String> nameStream = employeeStream.map(employee -> employee.getName());

        // takes a value and expects a stream<Value>
        // internally it will iterate over the stream which we are returning and
        // collect all the elements into a single stream
        Stream<Integer> numberStream = employeeStream.flatMap(employee -> employee.getNumbers().stream());
        List<Integer> integerList = numberStream.collect(Collectors.toList());

        // to do the same work using Map
        List<Integer> number = new ArrayList<>();
        employeeStream
                .map(employee -> employee.getNumbers())
                .forEach(numbers -> number.addAll(numbers));
    }

    public static List<Integer> circularArrayRotation(List<Integer> a, int k, List<Integer> queries) {
        List<Integer> resultList = new ArrayList<>();
        Collections.rotate(a, k);
        queries.forEach(value -> resultList.add(a.get(value)));
        return resultList;
    }

    public static int viralAdvertising(int n) {
        int day = 1;
        int numberOfPeopleReached = 5;
        int counter = 0;
        while (day < n+1) {
            int numberOfPeopleWhoLiked = numberOfPeopleReached / 2;
            counter = counter + numberOfPeopleWhoLiked;
            numberOfPeopleReached = numberOfPeopleWhoLiked * 3;
            System.out.println("Day: " + day + ", numberOfPeopleWhoLiked: " + numberOfPeopleWhoLiked + ", numberOfPeopleReached: " + numberOfPeopleReached);
            day++;
        }
        return counter;
    }

    public static int beautifulDays(int i, int j, int k) {
        int counter = 0;
        while (i < j + 1) {
            if (Math.abs(i - reverseOfANumber(i)) % k == 0) {
                counter++;
            }
            i++;
        }
        return counter;
    }

    public static int reverseOfANumber(int number) {
        int reverse = 0;
        while (number != 0) {
            int reminder = number % 10;
            reverse = reverse * 10 + reminder;
            number = number / 10;
        }
        return reverse;
    }

    public static String angryProfessor(int k, List<Integer> a) {
        long count = a.stream()
                .filter(value -> value > 0)
                .count();
        return (a.size() - count) < k? "YES": "NO";
    }

    public static int theLoveLetterMystery(String s) {
        int modifiedCounter = 0;
        for (int i=0; i<s.length()/2; i++) {
            int value1 = (int) s.charAt(i);
            int value2 = (int) s.charAt(s.length()-i-1);
            while (value1 < value2) {
                value2 = value2 - 1;
                modifiedCounter++;
            }
            while (value1 > value2) {
                value1 = value1 - 1;
                modifiedCounter++;
            }
        }
        return modifiedCounter;
    }

    public static int alternatingCharacters(String s) {
        int deletionCounter = 0;
        for (int i=0; i<s.length(); i++) {
            char ch = s.charAt(i);
            if (i+1 != s.length()) {
                while(i+1 != s.length() && ch == s.charAt(i + 1)) {
                    deletionCounter++;
                    i = i + 1;
                }
            }
        }
        return deletionCounter;
    }

    public static String funnyString(String s) {
        List<Integer> originalCode = new ArrayList<>();
        List<Integer> reverseCode = new ArrayList<>();
        String result = "";
        boolean flag = true;
        for (int i=0; i<s.length(); i++) {
            originalCode.add((int) s.charAt(i));
            reverseCode.add((int) s.charAt(s.length() - 1 - i));
        }
        for (int i=0; i<originalCode.size()-1; i++) {
            int original = Math.abs(originalCode.get(i) - originalCode.get(i+1));
            int reverse = Math.abs(reverseCode.get(i) - reverseCode.get(i+1));
            if (original != reverse) {
                flag = false;
                break;
            }
        }
        if (flag) {
            result += "Funny";
        } else {
            result += "Not Funny";
        }
        return result;
    }

    public static int gemstone(List<String> arr) {
        int gem = 0;
        int count = 1;
        boolean flag = true;
        List<String> alreadyProcessed = new ArrayList<>();
        for (int i=0; i<arr.size(); i++) {
            String input = arr.get(i);
            for (int j=0; j<input.length(); j++) {
                flag = true;
                count = 1;
                String checkForGem = Character.toString(input.charAt(j));
                if (!alreadyProcessed.contains(checkForGem)) {
                    while (count != arr.size()) {
                        if (!arr.get(count).contains(checkForGem)) {
                            flag = false;
                            alreadyProcessed.add(checkForGem);
                            break;
                        }
                        count++;
                    }
                    if (flag) {
                        gem++;
                        alreadyProcessed.add(checkForGem);
                    }
                }
            }
            break;
        }
        return gem;
    }

    public static int gemstones(List<String> arr) {
        int length = arr.size();
        int counter = 0;
        int gem = 0;
        boolean flag = true;
        List<Map<Character, Integer>> listMap = new ArrayList<>();
        while (counter != length) {
            listMap.add(new HashMap<Character, Integer>());
        }
        for (int i=0; i<arr.size(); i++) {
            String input = arr.get(i);
            for (int j=0; j<input.length(); j++) {
                if (listMap.get(i).containsKey(input.charAt(j))) {
                    listMap.get(i).put(input.charAt(j), listMap.get(i).get(input.charAt(j)) +1);
                } else {
                    listMap.get(i).put(input.charAt(j), 1);
                }
            }
        }
        int count = 1;
        Set<Character> keySet = listMap.get(0).keySet();
        for (Character ch: keySet) {
            flag = true;
            while (count != listMap.size()) {
                if (!listMap.get(count).containsKey(ch)) {
                    flag = false;
                }
            }
            if (flag) {
                gem++;
            }
        }

        return gem;

    }

    public static void separateNumbers(String s) {
        int limit = s.length()/2 + 1;
        int range = 1;
        String result = "";
        boolean flag = true;
        // System.out.println("Limit: " + limit);
        if (s.length() > 1) {
            while (range < limit) {
                long number = 0;
                result  = "";
                number =Long.parseLong(s.substring(0, range));
                long initial = number;
                // System.out.println("Range: " + range);
                // System.out.println("Number: " + number);
                while (result.length() < s.length()) {
                    result +=  Long.toString(number);
                    number = number + 1;
                }
                // System.out.println("Result: " + result);
                if (result.equals(s)) {
                    System.out.println("YES" + " " + initial);
                    flag = false;
                    break;
                }
                range++;
            }
        }
        if (flag) {
            System.out.println("NO");
        }
    }

    public static String hackerrankInString(String s) {
        String originalString = "hackerrank";
        int index = 0;
        int i = 0;
        boolean flag = false;
        for (; i<originalString.length();  i++) {
            for (; index<s.length(); index++) {
                if (originalString.charAt(i) == s.charAt(index)) {
                    index++;
                    break;
                }
            }
            if (index >= s.length() && i+1 != originalString.length()) {
                flag = true;
            }
        }
        return (flag) ? "NO": "YES";
    }

    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {

        char []charArray = s.toLowerCase().toCharArray();
        List<Integer> resultWeight = new ArrayList<>();
        List<String> resultString = new ArrayList<>();
        Arrays.sort(charArray);
        Map<Character, Integer> map = new HashMap<>();
        for (int i=0; i<charArray.length; i++) {
            if (map.containsKey(charArray[i])) {
                map.put(charArray[i], map.get(charArray[i]) + 1);
            } else {
                map.put(charArray[i], 1);
            }
        }
        Set<Character> keySet = map.keySet();
        for (Character ch: keySet) {
            int count = map.get(ch);
            while (count != 0) {
                int weight = getWeight(ch);
                resultWeight.add(weight * count);
                count--;
            }
        }
        System.out.println(resultWeight);
        queries.forEach(value -> {
            if (resultWeight.contains(value)) {
                resultString.add("Yes");
            } else {
                resultString.add("No");
            }
        });

        return resultString;
    }

    public static int getWeight(char ch) {
        String allLetters = "abcdefghijklmnopqrstuvwxyz";
        return allLetters.indexOf(ch) + 1;
    }

    public static int divisibleSumNPairs(int n, int k, List<Integer> ar) {
        int counter =0;
        // 1, 2, 3, 4
        for (int i=0; i<ar.size() - k; i++) {
        }
        return 0;
    }

    public static int divisibleSumPairs(int n, int k, List<Integer> ar) {
        int counter = 0;
        for (int i=0; i<ar.size() - 1; i++) {
            for (int j=i+1; j<ar.size(); j++) {
                if ((ar.get(i) + ar.get(j)) % k == 0) {
                    counter = counter + 1;
                }
            }
        }
        return counter;
    }

    public static int migratoryBirds(List<Integer> arr) {
        arr.sort((x , y) -> x - y);
        Map<Integer, Integer> map = new HashMap<>();
        arr.forEach(value -> {
            if (map.containsKey(value)) {
                map.put(value, map.get(value) + 1);
            } else {
                map.put(value, 1);
            }
        });
        int max = 0;
        int key = 0;
        Set<Integer> keys = map.keySet();

        for (int value: keys) {
            if (max < map.get(value)) {
                max = map.get(value);
                key = value;
            }
        }
        return key;
    }

    public static void printSeries(int input) {
        // 0, 1, 1, 2, 3, 5, 8, 13
        int counter = 0;
        int prev = 0;
        int result = 0;
        while (counter < input) {
            if (counter == 0) {
                prev = 0;
                result = 0;
                counter++;
            } else if (counter == 1) {
                prev = result;
                result = 1;
                counter++;
            } else {
                int temp = prev;
                prev = result;
                result = result + temp;
                counter++;
            }
            System.out.println(result);
        }
    }

    public static int numberOfPrimeNumbers(int start, int end) {
        int counter = 0;
        for (int i=start; i<=end; i++) {
            boolean flag = true;
            for (int j=2; j<i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                System.out.println(i);
                counter++;
            }
        }
        return counter;
    }

    public static void primeNumbers(int input) {
        // should only divide by 1 and number itself
        int counter = 0;
        for (int i=2; counter != input; i++) {
            boolean flag = true;
            for (int j=2; j<i; j++) {
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                counter++;
                System.out.println(i);
            }
        }
    }

    public static boolean palindromeOrNot(int input) {
        int last = 0;
        int output = 0;
        int process = input;
        while (process != 0) {
            int result = process % 10;
            output = last * 10 + result;
            last = output;
            process = process / 10;
        }

        System.out.println("Entered input: " + input);
        System.out.println("Resulted Output : " + output);

        if (input == output)
            return true;
        else
            return false;

        // by converting it into string and reversing it
//        String numberString = Integer.toString(input);
//        String resultNumberString = "";
//        for (int i=numberString.length()-1; i>=0; i--)
//            resultNumberString += numberString.charAt(i);
//        int output = Integer.parseInt(resultNumberString);
//        if (input == output)
//            return true;
//        else
//            return false;
        
    }

    public static boolean anagramOrNot(String input1, String input2) {
        // Using sorting the array
        char[] chars1 = input1.toLowerCase().replace(" ", "").toCharArray();
        char[] chars2 = input2.toLowerCase().replace(" ", "").toCharArray();

        Arrays.sort(chars1);
        Arrays.sort(chars2);

        System.out.println(chars1);
        System.out.println(chars2);
        return Arrays.equals(chars1, chars2);

//          using Map
//        if (Objects.isNull(input1) || Objects.isNull(input2))
//            return true;
////        if (input1.length() != input2.length())
////            return false;
//
//        HashMap<Character, Integer> valueSet1 = new HashMap<>();
//        HashMap<Character, Integer> valueSet2 = new HashMap<>();
//
//        for (int i=0; i<input1.length(); i++) {
//            if (input1.charAt(i) == ' ')
//                continue;
//            if (valueSet1.containsKey(input1.charAt(i))) {
//                valueSet1.put(Character.toLowerCase(input1.charAt(i)), valueSet1.get(input1.charAt(i)) + 1);
//            } else {
//                valueSet1.put(Character.toLowerCase(input1.charAt(i)), 1);
//            }
//        }
//
//        for (int i=0; i<input2.length(); i++) {
//            if (input2.charAt(i) == ' ')
//                continue;
//            if (valueSet2.containsKey(input2.charAt(i))) {
//                valueSet2.put(Character.toLowerCase(input2.charAt(i)), valueSet2.get(input2.charAt(i)) + 1);
//            } else {
//                valueSet2.put(Character.toLowerCase(input2.charAt(i)), 1);
//            }
//        }
//        System.out.println(valueSet1);
//        System.out.println(valueSet2);
//        return valueSet1.equals(valueSet2);
    }
}
