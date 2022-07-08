package io.adarsh.springdatajpaexp.service.serviceES;

import io.adarsh.springdatajpaexp.utils.ESUtils;
import io.adarsh.springdatajpaexp.utils.Indices;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class IndexService {

    private final List<String> INDICES_TO_CREATE = List.of(Indices.VEHICLE_INDEX);
    private final RestHighLevelClient client;

    @Autowired
    public IndexService(RestHighLevelClient client) {
        this.client = client;
    }

    @PostConstruct
    public void recreateIndex(/*boolean flag*/) {
        createIndices(false);
    }

    public void createIndices(boolean deleteExisting) {
        final String settings = ESUtils.loadAsString("es-setting.json");

        for (final String index : INDICES_TO_CREATE) {
            try {
                boolean exists = client.indices().exists(new GetIndexRequest(index), RequestOptions.DEFAULT);
                if (exists) {
                    if (!deleteExisting) {
                        continue;
                    }

                    // should firstly previously saved data to new index one.
                    client.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
                }

                final String mappings = ESUtils.loadAsString("mappings/" + index + ".json");
                if (settings == null || mappings == null) {
                    System.out.println("Failed to create index with name: "+ index);
                }

                final CreateIndexRequest createIndexRequest = new CreateIndexRequest(index);
                createIndexRequest.settings(settings, XContentType.JSON);
                createIndexRequest.mapping(mappings, XContentType.JSON);

                CreateIndexResponse createIndexResponse = client.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
