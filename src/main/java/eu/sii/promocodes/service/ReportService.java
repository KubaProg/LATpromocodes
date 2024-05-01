package eu.sii.promocodes.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getReport() {
        String sql = "SELECT \n" +
                "    pr.currency,\n" +
                "    SUM(pr.regular_price) AS total_amount,\n" +
                "    SUM(p.discount_applied) AS total_discount,\n" +
                "    COUNT(*) AS no_of_purchases\n" +
                "FROM purchases p\n" +
                "INNER JOIN products pr ON p.product_id = pr.id\n" +
                "GROUP BY pr.currency;\n" +
                " ";

        List<Map<String, Object>> reportData = jdbcTemplate.queryForList(sql);

        return reportData;
    }

}
