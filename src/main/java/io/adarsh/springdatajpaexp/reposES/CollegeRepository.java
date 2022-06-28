package io.adarsh.springdatajpaexp.reposES;

import io.adarsh.springdatajpaexp.modelES.College;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CollegeRepository extends ElasticsearchRepository<College, String> {
}
