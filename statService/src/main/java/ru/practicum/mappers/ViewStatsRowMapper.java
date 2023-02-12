package ru.practicum.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.practicum.model.ViewStats;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStatsRowMapper implements RowMapper<ViewStats> {
    @Override
    public ViewStats mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ViewStats.builder()
                .app(rs.getString("app"))
                .uri(rs.getString("uri"))
                .hits(rs.getLong("hits"))
                .build();
    }
}
