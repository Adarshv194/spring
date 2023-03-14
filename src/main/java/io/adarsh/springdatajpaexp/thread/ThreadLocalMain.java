package io.adarsh.springdatajpaexp.thread;

import io.adarsh.springdatajpaexp.model.User;
import io.adarsh.springdatajpaexp.modelES.Person;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.WeakHashMap;
import java.util.stream.Stream;

public class ThreadLocalMain {

    private static ThreadLocal threadLocal;

    public static void main(String[] args) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<Integer> threadLocalUserId = new ThreadLocal<>();
        threadLocalUserId.set(1234);
        threadLocal.set("Adarsh");
        Integer integer = threadLocalUserId.get();
//        threadLocals();
//        allFieldsOfAnObjectAndMethodInvocation();
        weakHashMap();
    }

    private static void threadLocals() throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException {
        // Get a reference to the thread locals table of the current thread.
        
        Thread thread = Thread.currentThread();
        Field threadLocalsField = Thread.class.getDeclaredField("threadLocals");
        threadLocalsField.setAccessible(true);
        Object threadLocalTable = threadLocalsField.get(thread);
        
        // Get a reference to the array holding the thread local variables inside the
        // ThreadLocalMap of the current thread

        Class threadLocalMapClass = Class.forName("java.lang.ThreadLocal$ThreadLocalMap");
        Field tableField = threadLocalMapClass.getDeclaredField("table");
        tableField.setAccessible(true);
        Object table = tableField.get(threadLocalTable);

        // The key to the ThreadLocalMap is a WeakReference object. The referent field of this object
        // is a reference to the actual ThreadLocal variable

        Field referentField = Reference.class.getDeclaredField("referent");
        referentField.setAccessible(true);

        for (int i=0; i< Array.getLength(table); i++) {
            // Each entry in the table array of ThreadLocalMap is an Entry object
            // representing the thread local reference and its value
            Object entry = Array.get(table, i);
            if (entry != null) {
                ThreadLocal threadLocal = (ThreadLocal) referentField.get(entry);
                WeakReference<User> userWeakReference = new WeakReference<User>(new User());
                User user = userWeakReference.get();

//                if (threadLocal.get().equals(1234) || threadLocal.get().equals("Adarsh"))
//                    System.out.println("Custom Thread Local");

                System.out.println(threadLocal.get());
            }
        }
    }

    private static void allFieldsOfAnObjectAndMethodInvocation() {
        User user = new User();
        user.setEmail("adarshv194@gmail.com");
        user.setUsername("adarshv194");
        user.setPassword("123456789");
        Field[] declaredFields = User.class.getDeclaredFields();
        System.out.println("Number of fields in user class are: " + declaredFields.length);
        Stream<Field> stream = Arrays.stream(declaredFields);
        System.out.println("Field names:");
        stream.forEach(field -> {
            System.out.println("Field name: " + field.getName());
            String fieldName = field.getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
            System.out.println("Method name: " + methodName);
            try {
                Method method = User.class.getDeclaredMethod(methodName);
                method.setAccessible(true);
                System.out.println("Result of method: " + methodName + " is: " + method.invoke(user));
            } catch (NoSuchMethodException e) {
                System.out.println("No method found with name: " + fieldName);
            } catch (InvocationTargetException e) {
                System.out.println("InvocationTargetException occurred");
            } catch (IllegalAccessException e) {
                System.out.println("IllegalAccessException occurred");
            }
        });
    }

    private static void weakHashMap() {
        User user = new User();
        User user1 = new User();
        Person person = new Person();
        WeakHashMap<User, Person> userPersonWeakHashMap = new WeakHashMap<>();
        userPersonWeakHashMap.put(user, person);
        userPersonWeakHashMap.put(user1, new Person());
        System.out.println("When there is a strong reference of user is active");
        System.out.println("Getting the meta data of the user object: " + userPersonWeakHashMap.get(user));
        System.out.println("Calling the Garbage Collector:");
        System.gc();
        System.out.println("Getting the meta data of the user object when user is still active: " + userPersonWeakHashMap.get(user));
        System.out.println("Setting the strong reference as null");
        user = null;
        user1 = null;
        System.gc();
        System.out.println("After setting the strong reference as null and calling the Garbage collector too, WeakHashMap size: " + userPersonWeakHashMap.size());
        System.out.println("Getting the meta data of the user object when user is not active: " + userPersonWeakHashMap.get(user));
        System.out.println("After calling get method on the WeakHashMap with the user as null, WeakHashMap size: " + userPersonWeakHashMap.size());
        // Now the entry will get remove from the WeakHashMap because on every method call
        // it internally remove the stale entries by calling expungeStaleEntries() -
        // basically removes all the keys whose keys are no longer in use with strong reference.
    }
}
