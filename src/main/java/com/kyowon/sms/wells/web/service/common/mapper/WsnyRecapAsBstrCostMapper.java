package com.kyowon.sms.wells.web.service.common.mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyRecapAsBstrCostDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyRecapAsBstrCostDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
@Mapper
public interface WsnyRecapAsBstrCostMapper {
    /**
     * 유상 AS 출장비 관리 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    List<WsnyRecapAsBstrCostDvo> selectRecapAsBstrCostPages(WsnyRecapAsBstrCostDto.SearchReq searchReq, PageInfo pageInfo);

    /**
     * 유상 AS 출장비 관리 중복 검증
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    WsnyRecapAsBstrCostDvo selectTarget(WsnyRecapAsBstrCostDto.SaveReq searchReq);

    WsnyRecapAsBstrCostDvo selectMaxIzSn(WsnyRecapAsBstrCostDvo dvo);

    /**
     * 유상 AS 출장비 관리 삭제
     *
     * @param saveReq 저장데이터
     * @return 처리수
     */
    int deleteRecapAsBstrCost(WsnyRecapAsBstrCostDto.SaveReq saveReq);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updateNextIsZnStrtDtm(WsnyRecapAsBstrCostDvo dvo);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updatePrevIsZnEndDtm(WsnyRecapAsBstrCostDvo dvo);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updateRecapAsBstrCost(WsnyRecapAsBstrCostDvo dvo);

    /**
     * 유상 AS 출장비 관리 등록
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int insertRecapAsBstrCost(WsnyRecapAsBstrCostDvo dvo);
}
