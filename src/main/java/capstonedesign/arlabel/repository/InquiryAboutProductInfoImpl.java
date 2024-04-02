package capstonedesign.arlabel.repository;

import capstonedesign.arlabel.dto.InorganicMatterContent;
import capstonedesign.arlabel.dto.NutritionInfo;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class InquiryAboutProductInfoImpl implements InquiryAboutProductInfo {

    private final JdbcTemplate template;

    public InquiryAboutProductInfoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    // 무기 물질 함량 정보를 조회하는 메서드
    @Override
    public InorganicMatterContent getInorganicMatterContentByProductName(String productName) {
        String sql = "SELECT * FROM InorganicMatter WHERE product_name = ?";

        // queryForObject(): 단건을 조회할 때 사용하는 메서드 (쿼리문 실행 결과로 1개만 나오는 경우)
        return template.queryForObject(sql, InorganicMatterContentRowMapper(), productName);
    }

    // 제품 영양 정보를 조회하는 메서드
    @Override
    public NutritionInfo getNutritionInfoByProductName(String productName) {
        String sql = "SELECT * FROM Nutrition WHERE product_name = ?";

        return template.queryForObject(sql, nutritionInfoRowMapper(), productName);
    }

    // RowMapper는 데이터베이스의 반환 결과인 ResultSet을 객체로 변환한다.
    private RowMapper<InorganicMatterContent> InorganicMatterContentRowMapper() {
        // BeanPropertyRowMapper는 ResultSet의 결과를 받아서 자바 빈 규약에 맞춰 데이터를 변환한다. (언더 스코어 표기법을 카멜로 자동 변환)
        return BeanPropertyRowMapper.newInstance(InorganicMatterContent.class);
    }

    private RowMapper<NutritionInfo> nutritionInfoRowMapper() {
        return BeanPropertyRowMapper.newInstance(NutritionInfo.class);
    }

}