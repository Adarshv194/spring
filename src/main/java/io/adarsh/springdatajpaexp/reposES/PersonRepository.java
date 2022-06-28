package io.adarsh.springdatajpaexp.reposES;

import io.adarsh.springdatajpaexp.modelES.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person, String> {
}
