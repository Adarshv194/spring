package io.adarsh.springdatajpaexp.modelES;

import io.adarsh.springdatajpaexp.utils.Indices;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Document(indexName = Indices.PERSON_INDEX)
@Setting(settingPath = "es-setting.json")
public class Person {

    @Id
    @Field(type = FieldType.Keyword)
    String id;

    @Field(type = FieldType.Text)
    String name;

    String year;

    String month;

    String concatenatedYearMonth;
}
