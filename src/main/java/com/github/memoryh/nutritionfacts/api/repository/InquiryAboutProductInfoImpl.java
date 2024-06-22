package com.github.memoryh.nutritionfacts.api.repository;

import com.github.memoryh.nutritionfacts.api.dto.MineralContent;
import com.github.memoryh.nutritionfacts.api.dto.NutritionFacts;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class InquiryAboutProductInfoImpl implements InquiryAboutProductInfo {

    private final JdbcTemplate template;

    public InquiryAboutProductInfoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<MineralContent> findAllSizesMineralContentByProductName(String productName) {
        String sql = "SELECT * FROM mineral_content WHERE product_name LIKE ?";

        return template.query(sql, mineralContentRowMapper(), productName + "%");
    }

    @Override
    public List<NutritionFacts> findAllSizesNutritionFactsByProductName(String productName) {
        String sql = "SELECT * FROM nutrition_facts WHERE product_name LIKE ?";

        return template.query(sql, nutritionFactsRowMapper(), productName + "%");
    }

    @Override
    public MineralContent findMineralContentByProductNameAndSize(String productName, String size) {
        String sql = "SELECT * FROM mineral_content where product_name = ?";

        return template.queryForObject(sql, mineralContentRowMapper(), combineProductNameAndSize(productName, size));
    }

    @Override
    public NutritionFacts findNutritionFactsByProductNameAndSize(String productName, String size) {
        String sql = "SELECT * FROM nutrition_facts WHERE product_name = ?";

        return template.queryForObject(sql, nutritionFactsRowMapper(), combineProductNameAndSize(productName, size));
    }

    // RowMapper는 데이터베이스의 반환 결과인 ResultSet을 객체로 변환한다.
    private RowMapper<MineralContent> mineralContentRowMapper() {
        // BeanPropertyRowMapper는 ResultSet의 결과를 받아서 자바 빈 규약에 맞춰 데이터를 변환한다. (언더 스코어 표기법을 카멜로 자동 변환)
        return BeanPropertyRowMapper.newInstance(MineralContent.class);
    }

    private RowMapper<NutritionFacts> nutritionFactsRowMapper() {
        return BeanPropertyRowMapper.newInstance(NutritionFacts.class);
    }

    //
    private static String combineProductNameAndSize(String productName, String size) {
        productName += "(" + size + ")";

        return productName;
    }

}