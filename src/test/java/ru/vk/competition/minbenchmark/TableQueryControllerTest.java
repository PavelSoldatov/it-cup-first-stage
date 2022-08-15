package ru.vk.competition.minbenchmark;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import ru.vk.competition.minbenchmark.dto.ColumnInfos;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.dto.query.AddNewQueryDto;
import ru.vk.competition.minbenchmark.dto.query.TableQueriesResponseDto;
import ru.vk.competition.minbenchmark.dto.report.Columns;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;
import ru.vk.competition.minbenchmark.dto.report.TablesDto;
import ru.vk.competition.minbenchmark.repository.TableQueryRepository;
import ru.vk.competition.minbenchmark.service.TableService;

import java.util.ArrayList;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = MinbenchmarkApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TableQueryControllerTest {

    @Autowired
    private TableQueryRepository tableQueryRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    @Test
    public void getQueryById_500() {
        List<TableQueriesResponseDto> pageOfDto1 = testUtils.invokeGetApi(new ParameterizedTypeReference<List<TableQueriesResponseDto>>() {
        }, "/api/table-query/get-table-query-by-id/1", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void getAllTableQueries_500() {
        List<TableQueriesResponseDto> pageOfDto1 = testUtils.invokeGetApi(new ParameterizedTypeReference<List<TableQueriesResponseDto>>() {
        }, "/api/table-query/get-all-table-queries", HttpStatus.OK);
        Assertions.assertEquals(pageOfDto1.size(), 0);
    }

    @Test
    public void countReport() {
        CreateTableDto createTableDto =
                new CreateTableDto().setTableName("NAMES")
                        .setColumnsAmount(2)
                        .setColumnInfos(List.of(new ColumnInfos()
                                .setTitle("id")
                                .setType("int4"),
                                        new ColumnInfos()
                                        .setTitle("name")
                                        .setType("VARCHAR(40)")))
                        .setPrimaryKey("id");


        testUtils.invokePostApi(Void.class, "/api/table/create-table", HttpStatus.CREATED, createTableDto);

        jdbcTemplate.execute("INSERT INTO NAMES (id, name) VALUES (1, 'pasha')");
        jdbcTemplate.execute("INSERT INTO NAMES (id, name) VALUES (2, null)");

        ReportDto reportDto =
                new ReportDto().setReportId(1)
                        .setTableAmount("1")
                        .setTables(List.of(new TablesDto().setTableName("NAMES")
                                .setColumns(List.of(new Columns().setTitle("id").setType("int4"),
                                        new Columns().setTitle("name").setType("VARCHAR(40)")))));

        testUtils.invokePostApi(Void.class, "/api/report/create-report", HttpStatus.CREATED, reportDto);

        ReportDto reportDto1 = testUtils.invokeGetApi(new ParameterizedTypeReference<ReportDto>() {
                                                      }, "/api/report/get-report-by-id/1",
                HttpStatus.CREATED, reportDto);

        int reportId = reportDto1.getReportId();

    }

    @Test
    public void changeNameWhenAlterTableName() {
        CreateTableDto createTableDto =
                new CreateTableDto().setTableName("NAMES")
                        .setColumnsAmount(2)
                        .setColumnInfos(List.of(new ColumnInfos()
                                        .setTitle("id")
                                        .setType("int4"),
                                new ColumnInfos()
                                        .setTitle("name")
                                        .setType("VARCHAR(40)")))
                        .setPrimaryKey("id");


        testUtils.invokePostApi(Void.class, "/api/table/create-table", HttpStatus.CREATED, createTableDto);

        TableService.tableStorage.put("test_table", new CreateTableDto());
        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.CREATED,
                new AddNewQueryDto().setQueryId(1).setTableName("NAMES").setQuery("ALTER TABLE NAMES RENAME TO NEWNAME")
        );

        testUtils.invokePostApi(
                Void.class, "/api/table-query/add-new-query-to-table",
                HttpStatus.CREATED,
                new AddNewQueryDto().setQueryId(2).setTableName("NAMES").setQuery("SELECT * FROM NAMES")
        );

        testUtils.invokeGetApi(
                new ParameterizedTypeReference<Void>() {
                }, "/api/table-query/execute-table-query-by-id/1",
                HttpStatus.OK
        );

        Assertions.assertEquals(tableQueryRepository.findAllByTableName("NEWNAME").size(), 2);
    }
}
