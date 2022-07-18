package io.adarsh.springdatajpaexp.model;

import io.adarsh.springdatajpaexp.embeddable.IdName;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Project {

//    @Id @GeneratedValue(strategy = GenerationType.AUTO)
//    int id;

    @EmbeddedId
    IdName id;

    int t;

    @OneToMany(mappedBy = "project")
    List<PayStub> payStubs = new ArrayList<>();

//    @ManyToMany
//    @JoinColumn(name = "mapping_id")
//    List<Employee> employees;


    public Project(int t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Project{" +
                "t=" + t +
                '}';
    }

    public static void main(String []args) {
        Set<Project> projectSet = new HashSet<>();
        projectSet.add(new Project(10));
        projectSet.add(new Project(101));
        projectSet.add(new Project(110));
        projectSet.add(new Project(11));

        projectSet.forEach(System.out::println);
        System.out.println("Sorting projectSet");
//        Stream<Project> sortedStream = projectSet.stream().sorted();
        Stream<Project> sortedStream = projectSet.stream().sorted((obj1, obj2) -> {
            System.out.println("Comparator called");
            return obj1.getT() - obj2.getT();
        });
        System.out.println("Sorted stream:");
        sortedStream.collect(Collectors.toList());
//        sortedStream.forEach(System.out::println);
        List<String> list = List.of("Adarsh");
        list.add("");
        List<String> objects = Collections.unmodifiableList(List.of(""));

//        try {
//            list.add("Verma");
//            System.out.println("Added");
//        } catch (UnsupportedOperationException e) {
//            System.out.println("Immutable collection list");
//        }

    }

}
