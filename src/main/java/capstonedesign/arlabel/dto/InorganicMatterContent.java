package capstonedesign.arlabel.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InorganicMatterContent {

    private String productName; // 제품 이름

    private String calcium; // 칼슘

    private String potassium; // 칼륨

    private String natrium; // 나트륨

    private String magnesium; // 마그네슘

    private String fluorine; // 불소

    private String catchmentArea; // 수원지

}