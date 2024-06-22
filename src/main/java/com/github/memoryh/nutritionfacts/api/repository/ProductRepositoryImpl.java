package com.github.memoryh.nutritionfacts.api.repository;

import com.github.memoryh.nutritionfacts.api.exception.NoSuchDBException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final JdbcTemplate template;

    public ProductRepositoryImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    // 제품 이름을 기반으로 해당 제품이 물인지 아닌지 판단하는 메서드
    @Override
    public boolean isProductWater(String productName) {
        String sql = "SELECT is_water FROM product_type WHERE product_name = ?";

        try {
            // SQL 쿼리 실행: 제품명을 사용하여 is_water 값을 조회하고 Boolean으로 반환
            // queryForObject(): 단일 조회할 때 사용하는 메서드 (쿼리문 실행 결과로 1개만 나오는 경우)
            return Boolean.TRUE.equals(template.queryForObject(sql, isWaterRowMapper(), productName));
        } catch (EmptyResultDataAccessException e) {
            // 조회 결과가 없을 경우 NoSuchDBException 예외 발생
            throw new NoSuchDBException("데이터베이스에 저장되어 있지 않은 제품명 요청", e);
        }
    }

    // RowMapper는 데이터베이스의 반환 결과인 ResultSet을 객체로 변환한다.
    private RowMapper<Boolean> isWaterRowMapper() {
        return (rs, rowNum) -> rs.getBoolean("is_water");
    }

}