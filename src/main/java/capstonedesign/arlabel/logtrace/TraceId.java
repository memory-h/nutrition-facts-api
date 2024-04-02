package capstonedesign.arlabel.logtrace;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TraceId {

    private String id; // 트랜잭션 ID를 저장

    private int level; // 깊이를 표현하는 레벨

    public TraceId() {
        this.id = createId();
        this.level = 0; // 레벨을 0으로 초기화
    }

    // 고유한 트랜잭션 ID를 생성하는 메서드 (UUID를 사용하여 랜덤한 ID를 생성하고, 그중 앞 8자리만 사용)
    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    // 트랜잭션 ID가 첫 번째 레벨인지 확인하는 메서드
    public boolean isFirstLevel() {
        return level == 0;
    }

}