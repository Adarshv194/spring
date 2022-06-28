package io.adarsh.springdatajpaexp.model;

import io.adarsh.springdatajpaexp.modelES.College;
import io.adarsh.springdatajpaexp.modelES.Person;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(indexName = "test")
@Setting(settingPath = "es-setting.json")
public class Test {

    @Id
    String id;

    @Field(type = FieldType.Nested)
    List<Person> personList = new ArrayList<>();

    List<College> collegeList = new ArrayList<>();

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void addCollege(College college) {
        collegeList.add(college);
    }
}
