package io.adarsh.springdatajpaexp.reposES;

import io.adarsh.springdatajpaexp.model.SectionTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SectionTestRepository extends ElasticsearchRepository<SectionTest, Long> {
}
