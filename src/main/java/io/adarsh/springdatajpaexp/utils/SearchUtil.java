package io.adarsh.springdatajpaexp.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import io.adarsh.springdatajpaexp.DTO.*;
import io.adarsh.springdatajpaexp.esDTO.ESBindDTO;
import io.adarsh.springdatajpaexp.esDTO.SearchRequestDTO;
import io.adarsh.springdatajpaexp.modelES.Person;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchHitsImpl;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

@Service
public final class SearchUtil {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    private SearchUtil() { }

    public static SearchRequest buildSearchRequest(final String indexName, String field, Date date) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().postFilter(getQueryBuilder(field, date));
        SearchRequest request = new SearchRequest(indexName);
        request.source(searchSourceBuilder);
        return request;
    }

    private static int getPage(int page, int size) {
        return page <=0 ? 0 : page * size;
    }

    public static SearchRequest buildSearchRequest(final String indexName, final SearchRequestDTO dto) {
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .from(getPage(dto.getPage(), dto.getSize()))
                .size(dto.getSize())
                .postFilter(getQueryBuilder(dto));

        if (dto.getSortBy() != null) {
            builder.sort(dto.getSortBy(),
                    dto.getSortOrder() != null ? dto.getSortOrder() : SortOrder.ASC);
        }
        SearchRequest request = new SearchRequest(indexName);
        request.source(builder);

        return request;
    }

    public static QueryBuilder getQueryBuilder(final SearchRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        List<String> fields = dto.getFields();
        if (CollectionUtils.isEmpty(fields)) {
            return null;
        }

        if (fields.size() > 1) {
            MultiMatchQueryBuilder queryBuilder = QueryBuilders.multiMatchQuery(dto.getSearchTerm())
                    .type(MultiMatchQueryBuilder.Type.CROSS_FIELDS)
                    .operator(Operator.AND);

            fields.forEach(field -> queryBuilder.field(field));
            return queryBuilder;
        }

        return fields.stream().findFirst()
                .map(field -> QueryBuilders.matchQuery(field, dto.getSearchTerm())
                                .operator(Operator.AND))
                .orElse(null);
    }

    public static QueryBuilder getQueryBuilder(String field, Date date) {
        if (field == null || date == null)
            return null;

        return QueryBuilders.rangeQuery(field).gte(date);
    }

    public static SearchRequest buildSearchRequest(String indexName, SearchRequestDTO dto, Date date) {
        SearchSourceBuilder builder = new SearchSourceBuilder()
                .from(getPage(dto.getPage(), dto.getSize()))
                .size(dto.getSize())
                .postFilter(getBoolQuery(dto, date));

        if (dto.getSortBy() != null) {
            builder.sort(dto.getSortBy(),
                    dto.getSortOrder() != null ? dto.getSortOrder() : SortOrder.ASC);
        }

        SearchRequest request = new SearchRequest(indexName);
        request.source(builder);
        return request;
    }

    public static QueryBuilder getBoolQuery(SearchRequestDTO dto, Date date) {
        QueryBuilder multiMatchQueryBuilder = getQueryBuilder(dto);
        QueryBuilder rangeQueryBuilder = getQueryBuilder("createdDate", date);

        QueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                                        .mustNot(multiMatchQueryBuilder)
                                        .must(rangeQueryBuilder);
        return boolQueryBuilder;
    }

    public List<Person> getAllPersonData(SearchRequestDTO searchRequestDTO) {
        List<String> personNames = searchRequestDTO.getPersonNames();


        List<BoolQueryBuilder> personQueryBuilder = new ArrayList<>();
        if (Objects.nonNull(searchRequestDTO.getPersonNames())) {
            personQueryBuilder = searchRequestDTO.getPersonNames().stream().
                    map(name -> new BoolQueryBuilder().should(QueryBuilders.matchQuery("name", name)))
                    .collect(Collectors.toList());
        }


//        List<MatchQueryBuilder> yearQueryBuilderList = new ArrayList<>();
//        List<MatchQueryBuilder> monthQueryBuilderList = new ArrayList<>();
        List<String> concatenatedYearMonthList = searchRequestDTO.getConcatenatedYearMonth();
//        if (Objects.nonNull(concatenatedYearMonthList)) {
//            concatenatedYearMonthList.forEach(concatenatedYearMonth -> {
//                String year = concatenatedYearMonth.substring(0, 4);
//                yearQueryBuilderList.add(QueryBuilders.matchQuery("year", year));
//                String month = concatenatedYearMonth.substring(4);
//                monthQueryBuilderList.add(QueryBuilders.matchQuery("month", month));
//            });
//        }

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        personQueryBuilder.forEach(boolQueryBuilder::should);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(boolQueryBuilder);

        List<Person> personList = elasticsearchRestTemplate.search(nativeSearchQueryBuilder.build(), Person.class).getSearchHits()
                .stream().map(SearchHit::getContent)
                .collect(Collectors.toList());
//        return personList;

//        List<YearMonthDTO> yearMonthList = new ArrayList<>();
//        if (Objects.nonNull(concatenatedYearMonthList)) {
//            concatenatedYearMonthList.forEach(concatenatedYearMonth -> {
//                String year = concatenatedYearMonth.substring(0, 4);
//                String month = concatenatedYearMonth.substring(4);
//                yearMonthList.add(new YearMonthDTO(year, month));
//        });
//        }

        List<Person> filteredPersonList = new ArrayList<>();

        personList.stream().forEach(person -> {
            concatenatedYearMonthList.forEach(ym -> {
                if (ym.equals(person.getConcatenatedYearMonth())) {
                    filteredPersonList.add(person);
                }
            });
        });

//        personList.stream().forEach(person -> {
//            yearMonthList.forEach(yearMonth -> {
//                if (person.getMonth().equals(yearMonth.getMonth()) && person.getYear().equals(yearMonth.getYear()))
//                    filteredPersonList.add(person);
//            });
//        });

        return filteredPersonList;
    }

    public List<Person> testJsonFromObject(SearchRequestDTO searchRequestDTO) {
        List<String> personNames = searchRequestDTO.getPersonNames();
        ESQueryDTO esQueryDTO = new ESQueryDTO();
        QueryESDTO queryESDTO = new QueryESDTO();
        BoolDTO boolDTO = new BoolDTO();
        personNames.forEach(person -> {
            MatchDTO matchDTO = new MatchDTO();
            matchDTO.setName(person);
            boolDTO.getShould().add(new ShouldDTO(matchDTO));
        });

        queryESDTO.setBool(boolDTO);
        esQueryDTO.setQuery(queryESDTO);

        Gson gson = new Gson();
//        JsonElement jsonElement = gson.toJsonTree(esQueryDTO);
//        System.out.println(jsonElement);
        System.out.println(gson.toJson(esQueryDTO));
        return fetchPersonData(gson.toJson(esQueryDTO));
    }

    public List<Person> fetchPersonData(String jsonBody) {
        RestTemplate restTemplate = new RestTemplate();
        List<Person> personList;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        Map<String, String> params = new HashMap<>();
        params.put("Content-Type", "application/json");
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            ResponseEntity<Map> exchange = restTemplate.exchange("http://localhost:9200/person/_search", HttpMethod.GET, entity,
                    Map.class, params);

            System.out.println("called");
//            System.out.println(exchange.getBody());
            LinkedHashMap hitMap = (LinkedHashMap) exchange.getBody().get("hits");
            ArrayList hits = (ArrayList) hitMap.get("hits");
            System.out.println("Mapped into: " + hits.getClass().toString());
            System.out.println(hits);
            for (Object x : hits) {
                Class<?> clasz = x.getClass();
                Field field = clasz.getField("_source");
                Object fieldValue = field.get(x);
                System.out.println(fieldValue);
            }
//            personList = hitsResponseEntity.getBody().stream().map(SearchHit::getContent).collect(Collectors.toList());
        } catch (HttpStatusCodeException | NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Exception occurred while calling the API url:");
            throw new RestClientException(e.toString());
        }
        return null;
    }
}
