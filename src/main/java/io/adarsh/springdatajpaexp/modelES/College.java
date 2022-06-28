package io.adarsh.springdatajpaexp.modelES;

import io.adarsh.springdatajpaexp.model.KYATest;
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
@Document(indexName = "college")
@Setting(settingPath = "es-setting.json")
public class College {
    @Id
    String id;

    String name;
    String country;

    List<Person> personList = new ArrayList<>();

    @Field(type = FieldType.Nested)
    List<KYATest> kyaTestList = new ArrayList<>();

    public void addPerson(Person person) {
        personList.add(person);
    }

    public void addKyaTest(KYATest kyaTest) {
        kyaTestList.add(kyaTest);
    }
}
