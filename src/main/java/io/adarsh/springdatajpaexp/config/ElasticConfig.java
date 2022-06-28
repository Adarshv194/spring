package io.adarsh.springdatajpaexp.config;

import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;

public class ElasticConfig {

    @Value("${elasticsearch.port}")
    private String elasticSearchPort;

    @Value("${elasticsearch.host}")
    private String elasticSearchHost;

    @Bean
    private RestHighLevelClient client() {
        return new RestHighLevelClient(RestClient.builder(HttpHost.create(elasticSearchHost+":"+elasticSearchPort)));
    }

    @Bean
    private ElasticsearchOperations elasticSearchTemplate() {
        return new ElasticsearchRestTemplate(client());
    }
}
