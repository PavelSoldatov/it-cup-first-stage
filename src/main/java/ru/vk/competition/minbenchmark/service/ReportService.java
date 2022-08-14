package ru.vk.competition.minbenchmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.vk.competition.minbenchmark.dto.ColumnInfos;
import ru.vk.competition.minbenchmark.dto.CreateTableDto;
import ru.vk.competition.minbenchmark.dto.report.Columns;
import ru.vk.competition.minbenchmark.dto.report.ReportDto;
import ru.vk.competition.minbenchmark.dto.report.TablesDto;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static ru.vk.competition.minbenchmark.service.TableService.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    public static ConcurrentHashMap<Integer, ReportDto> reportStorage = new ConcurrentHashMap<>();

    public ResponseEntity<Void> createReport(ReportDto reportDto) {
        if (reportStorage.containsKey(reportDto.getReportId())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (reportDto.getTables().size() != Integer.parseInt(reportDto.getTableAmount())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        List<TablesDto> tables = reportDto.getTables();
        for (TablesDto table : tables) {
            if (!tableStorage.containsKey(table.getTableInfo().getTableName())) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }

            List<Columns> columns = table.getTableInfo().getColumns();
            List<ColumnInfos> columnInfos = tableStorage.get(table.getTableInfo().getTableName()).getColumnInfos();

            for (int i = 0; i < columns.size(); i++) {
                Columns reportColumn = columns.get(i);
                ColumnInfos existedColumn = columnInfos.get(i);

                if (!reportColumn.getTitle().equalsIgnoreCase(existedColumn.getTitle())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }

                if (!reportColumn.getType().equalsIgnoreCase(existedColumn.getType())) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
                }
            }
        }

        reportStorage.put(reportDto.getReportId() ,reportDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ReportDto getReportById(int id) {
        if (reportStorage.containsKey(id)) {
            return reportStorage.get(id);
        } else {
            throw new RuntimeException("Не существует такого отчета");
        }
    }
}
