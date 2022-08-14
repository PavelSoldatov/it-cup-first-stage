package ru.vk.competition.minbenchmark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.service.TableService;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = MinbenchmarkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TableQueryControllerTest {

    @Autowired
    private TableQueryRepository tableQueryRepository;

    @Autowired
    private TestUtils testUtils;

    public static String baseUrl = "/api/table-query";

    @Test
    public void createTable201() {
        TableService.tableStorage.put("test_table", new CreateTableDto());
        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.CREATED,
                new AddNewQueryDto().setQueryId(1).setTableName("test_table").setQuery("select 1")
        );
    }

    @Test
    public void createTableWithWrongQuery406() {
        TableService.tableStorage.put("test_table", new CreateTableDto());
        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.NOT_ACCEPTABLE,
                new AddNewQueryDto().setQueryId(1).setTableName("test_table").setQuery("select фывыфв")
        );
    }

    @Test
    public void createTableWithQuerySizeMore120__406() {
        TableService.tableStorage.put("test_table", new CreateTableDto());
        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.NOT_ACCEPTABLE,
                new AddNewQueryDto().setQueryId(1).setTableName("test_table").setQuery("Contrary to popular belief, Lorem Ipsum is not simply random textContrary to popular belief, Lorem Ipsum is not simply random textContrary to popular belief, Lorem Ipsum is not simply random text")
        );
    }

    @Test
    public void createTableWithTableNameSizeMore120__406() {
        TableService.tableStorage.put("test_table", new CreateTableDto());
        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.NOT_ACCEPTABLE,
                new AddNewQueryDto().setQueryId(1).setTableName("sdfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff").setQuery("select 1")
        );
    }

    @Test
    public void createTableQueryIfTableNotExist406() {
        testUtils.invokePostApi(
            Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.NOT_ACCEPTABLE,
                new AddNewQueryDto().setQueryId(1).setTableName("Customer").setQuery("select 1")
        );
    }

    @Test
    public void getAllQueriesByName_200() {
        List<TableQueriesResponseDto> pageOfDto1 = testUtils.invokeGetApi(new ParameterizedTypeReference<List<TableQueriesResponseDto>>() {
        }, "/api/table-query/get-all-queries-by-table-name/customer", HttpStatus.OK);

        Assertions.assertEquals(pageOfDto1.size(), 0);
    }
}
