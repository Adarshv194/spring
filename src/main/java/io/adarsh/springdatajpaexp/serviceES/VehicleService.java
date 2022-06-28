package io.adarsh.springdatajpaexp.serviceES;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.adarsh.springdatajpaexp.esDTO.SearchRequestDTO;
import io.adarsh.springdatajpaexp.modelES.Vehicle;
import io.adarsh.springdatajpaexp.utils.Indices;
import io.adarsh.springdatajpaexp.utils.SearchUtil;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class VehicleService {

    private static final ObjectMapper mapper = new ObjectMapper();
    private final RestHighLevelClient client;

    @Autowired
    public VehicleService(RestHighLevelClient client) {
        this.client = client;
    }

    public Boolean save(Vehicle vehicle) {
        try {
            String vehicleAsString = mapper.writeValueAsString(vehicle);
            IndexRequest indexRequest = new IndexRequest(Indices.VEHICLE_INDEX);
            indexRequest.id(vehicle.getId());
            indexRequest.source(vehicleAsString, XContentType.JSON);

            IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
            return indexResponse != null && indexResponse.status().equals(RestStatus.OK);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vehicle getVehicleById(String id) throws IOException {
        GetResponse documentFields = client.get(new GetRequest(Indices.VEHICLE_INDEX, id), RequestOptions.DEFAULT);
        if (documentFields == null || documentFields.isSourceEmpty())
            return null;

        return mapper.readValue(documentFields.getSourceAsString(), Vehicle.class);
    }

    public List<Vehicle> getAllVehiclesCreatedSince(Date date) throws IOException {
        SearchRequest searchRequest = SearchUtil.buildSearchRequest(Indices.VEHICLE_INDEX, "createdDate", date);
        return searchInternal(searchRequest);
    }

    public List<Vehicle> search(SearchRequestDTO dto) throws IOException {
        SearchRequest searchRequest = SearchUtil.buildSearchRequest(Indices.VEHICLE_INDEX, dto);
        return searchInternal(searchRequest);
    }

    private List searchInternal(SearchRequest searchRequest) throws IOException {
        if (searchRequest == null) {
            return Collections.EMPTY_LIST;
        }

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHit[] hits = response.getHits().getHits();
        List<Vehicle> vehicles = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            vehicles.add(
                    mapper.readValue(hit.getSourceAsString(), Vehicle.class)
            );
        }
        return vehicles;
    }

    public List<Vehicle> searchCreatedSince(SearchRequestDTO dto, Date date) throws IOException {
        SearchRequest request = SearchUtil.buildSearchRequest(Indices.VEHICLE_INDEX, dto, date);
        return searchInternal(request);
    }
}
