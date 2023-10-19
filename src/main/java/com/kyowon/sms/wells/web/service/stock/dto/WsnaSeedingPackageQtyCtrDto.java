package com.kyowon.sms.wells.web.service.stock.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.system.config.validation.validator.ValidDate;

import io.swagger.annotations.ApiModel;
import lombok.Builder;

public class WsnaSeedingPackageQtyCtrDto {

    @Builder
    @ApiModel("WsnaSeedingPackageQtyCtrDto-SearchReq")
    public record SearchReq(
        // 출고일자
        @NotBlank
        @ValidDate
        String ostrDt
    ) {}

    @Builder
    @ApiModel("WsnaSeedingPackageQtyCtrDto-SearchRes")
    public record SearchRes(
        // 센터코드
        String dgGgLctCd,
        // 센터
        String dgGgLctNm,
        // 건강샐러드/주스SLIM
        BigDecimal type1,
        // 건강샐러드/주스WIDE
        BigDecimal type2,
        // 우리아이채소식단SLIM
        BigDecimal type3,
        // 우리아이채소식단WIDE
        BigDecimal type4,
        // 건강밥상SLIM
        BigDecimal type5,
        // 건강밥상WIDE
        BigDecimal type6,
        // 항암건강SLIM
        BigDecimal type7,
        // 항암건강WIDE
        BigDecimal type8,
        // 숙면힐링SLIM
        BigDecimal type9,
        // 숙면힐링WIDE
        BigDecimal type10,
        // 우리아이신선이유식SLIM
        BigDecimal type11,
        // 우리아이신선이유식WIDE
        BigDecimal type12,
        // 버터헤드SLIME
        BigDecimal type13,
        // 버터헤드WIDE
        BigDecimal type14,
        // 케일SLIM
        BigDecimal type15,
        // 케일WIDE
        BigDecimal type16,
        // 비타민다채SLIM
        BigDecimal type17,
        // 비타민다채WIDE
        BigDecimal type18,
        // 버터헤드+케일WIDE
        BigDecimal type19,
        // 버터헤드+비타민다채WIDE
        BigDecimal type20,
        // 케일+비타민다채WIDE
        BigDecimal type21,
        // 웰스팜미니채소
        BigDecimal type22,
        // 아이쑥쑥유엔젤WIDE
        BigDecimal type23,
        // 선택모종
        BigDecimal type24,
        // 먹치마_미니
        BigDecimal type25,
        // 여름청치마_미니
        BigDecimal type26,
        // 청경채_미니
        BigDecimal type27,
        // 먹치마+여름청치마_미니
        BigDecimal type28,
        // 먹치마+청경채_미니
        BigDecimal type29,
        // 먹치마+적소렐_미니
        BigDecimal type30,
        // 여름청치마+청경채_미니
        BigDecimal type31,
        // 여름청치마+적소렐_미니
        BigDecimal type32,
        // 선택모종WIDE
        BigDecimal type33,
        // 선택모종SLIM
        BigDecimal type34,
        // 유러피안샐러드SLIM
        BigDecimal type35,
        // 유러피안샐러드WIDE
        BigDecimal type36,
        // 우리가족건강채소SLIM
        BigDecimal type37,
        // 우리가족건강채소WIDE
        BigDecimal type38,
        // 모둠쌈채소WIDE
        BigDecimal type39,
        // 모둠쌈채소SLIM
        BigDecimal type40,
        // 기능성채소WIDE
        BigDecimal type41,
        // 기능성채소SLIM
        BigDecimal type42

    ) {}

}
