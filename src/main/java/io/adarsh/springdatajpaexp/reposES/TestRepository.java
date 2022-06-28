package io.adarsh.springdatajpaexp.reposES;

import io.adarsh.springdatajpaexp.model.Test;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TestRepository extends ElasticsearchRepository<Test, String> {
}
