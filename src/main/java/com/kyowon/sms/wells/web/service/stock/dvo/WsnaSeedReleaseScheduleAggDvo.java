package com.kyowon.sms.wells.web.service.stock.dvo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 집계표 dvo
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-08-30
 */

@Getter
@Setter
public class WsnaSeedReleaseScheduleAggDvo {

    // 센터
    private String dept;
    // 센터명
    private String deptNm;

    /**
     * 전체
     */
    // 샐러드
    private BigDecimal pak01;
    // 채소식단
    private BigDecimal pak02;
    // 건강밥상
    private BigDecimal pak03;
    // 항암건강
    private BigDecimal pak04;
    // 숙면힐링
    private BigDecimal pak05;
    // 이유식
    private BigDecimal pak13;
    // 미니
    private BigDecimal pak23;
    // 아이쑥쑥유엔젤
    private BigDecimal pak50;
    // 선택모종
    private BigDecimal pak24;
    // 버터헤드
    private BigDecimal pak08;
    // 케일
    private BigDecimal pak09;
    // 비타민다채
    private BigDecimal pak12;
    // 먹치마
    private BigDecimal pak51;
    // 여름청치마
    private BigDecimal pak52;
    // 청경채
    private BigDecimal pak53;
    // 먹치마+여름청치마
    private BigDecimal pak54;
    // 먹치마+청경채
    private BigDecimal pak55;
    // 먹치마+적소렐
    private BigDecimal pak56;
    // 여름청치마+청경채
    private BigDecimal pak57;
    // 여름청치마+적소렐
    private BigDecimal pak58;
    // 설치_자유WIDE
    private BigDecimal pak61;
    // BS/AS_자유WIDE
    private BigDecimal pak62;
    // 설치_자유SLIM
    private BigDecimal pak63;
    // BS/AS_자유SLIM
    private BigDecimal pak64;
    // 유러피안
    private BigDecimal pak28;
    // 우리가족
    private BigDecimal pak29;
    // 모둠쌈
    private BigDecimal pak30;
    // 기능성채소
    private BigDecimal pak31;
    // 소계
    private BigDecimal totSum;

    /**
     * 설치, BS, AS
     */
    // 건강샐러드/주스SLIM
    private BigDecimal pak01001;
    // 건강샐러드/주스WIDE
    private BigDecimal pak01002;
    // 우리아이채소식단SLIM
    private BigDecimal pak02001;
    // 우리아이채소식단WIDE
    private BigDecimal pak02002;
    // 건강밥상SLIM
    private BigDecimal pak03001;
    // 건강밥상WIDE
    private BigDecimal pak03002;
    // 항암건강SLIM
    private BigDecimal pak04001;
    // 항암건강WIDE
    private BigDecimal pak04002;
    // 숙면힐링SLIM
    private BigDecimal pak05001;
    // 숙면힐링WIDE
    private BigDecimal pak05002;
    // 우리아이신선이유식SLIM
    private BigDecimal pak13001;
    // 우리아이신선이유식WIDE
    private BigDecimal pak13002;
    // 웰스팜미니채소
    private BigDecimal pak23001;
    // 아이쑥쑥유엔젤WIDE
    private BigDecimal pak50001;
    // 선택모종
    private BigDecimal pak24001;
    // 버터헤드SLIM
    private BigDecimal pak08003;
    // 버터헤드WIDE
    private BigDecimal pak08004;
    // 케일SLIM
    private BigDecimal pak09003;
    // 케일WIDE
    private BigDecimal pak09004;
    // 비타민다채SLIM
    private BigDecimal pak12003;
    // 비타민다채WIDE
    private BigDecimal pak12004;
    // 버터헤드+케일WIDE
    private BigDecimal pak08002;
    // 버터헤드+비타민다채WIDE
    private BigDecimal pak08001;
    // 케일+비타민다채WIDE
    private BigDecimal pak09002;
    // 먹치마_미니
    private BigDecimal pak51001;
    // 여름청치마_미니
    private BigDecimal pak52001;
    // 청경채_미니
    private BigDecimal pak53001;
    // 먹치마+여름청치마_미니
    private BigDecimal pak54001;
    // 먹치마+청경채_미니
    private BigDecimal pak55001;
    // 먹치마+적소렐_미니
    private BigDecimal pak56001;
    // 여름청치마+청경채_미니
    private BigDecimal pak57001;
    // 여름청치마+적소렐_미니
    private BigDecimal pak58001;
    // 선택모종WIDE
    private BigDecimal pak59001;
    // 선택모종SLIM
    private BigDecimal pak60001;
    // 유러피안샐러드SLIM
    private BigDecimal pak28001;
    // 유러피안샐러드WIDE
    private BigDecimal pak28002;
    // 우리가족건강채소SLIM
    private BigDecimal pak29001;
    // 우리가족건강채소WIDE
    private BigDecimal pak29002;
    // 모둠쌈채소SLIM
    private BigDecimal pak30001;
    // 모둠쌈채소WIDE
    private BigDecimal pak30002;
    // 기능성채소SLIM
    private BigDecimal pak31001;
    // 기능성채소WIDE
    private BigDecimal pak31002;
}
