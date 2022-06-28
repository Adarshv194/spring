package io.adarsh.springdatajpaexp.reposES;

import io.adarsh.springdatajpaexp.model.KYATest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface KYATestRepository extends ElasticsearchRepository<KYATest, Long> {
}
