package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;

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
public interface WsnyAsVisitCostMgtMapper {
    /**
     * 유상 AS 출장비 관리 조회(페이징)
     * @param searchReq 조회조건
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    List<WsnyAsVisitCostMgtDvo> selectAsVisitCostPages(WsnyAsVisitCostMgtDto.SearchReq searchReq, PageInfo pageInfo);

    /**
     * 유상 AS 출장비 관리 조회(엑셀 다운로드)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnyAsVisitCostMgtDvo> selectAsVisitCostPages(WsnyAsVisitCostMgtDto.SearchReq searchReq);

    /**
     * 유상 AS 출장비 관리 중복 검증
     *
     * @param searchReq 조회조건
     * @return 조회결과
     */
    WsnyAsVisitCostMgtDvo selectTarget(WsnyAsVisitCostMgtDto.SaveReq searchReq);

    WsnyAsVisitCostMgtDvo selectMaxIzSn(WsnyAsVisitCostMgtDvo dvo);

    /**
     * 유상 AS 출장비 관리 삭제
     *
     * @param saveReq 저장데이터
     * @return 처리수
     */
    int deleteRecapAsBstrCost(WsnyAsVisitCostMgtDto.SaveReq saveReq);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updateNextIsZnStrtDtm(WsnyAsVisitCostMgtDvo dvo);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updatePrevIsZnEndDtm(WsnyAsVisitCostMgtDvo dvo);

    /**
     * 유상 AS 출장비 관리 수정
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int updateRecapAsBstrCost(WsnyAsVisitCostMgtDvo dvo);

    /**
     * 유상 AS 출장비 관리 등록
     *
     * @param dvo 저장데이터
     * @return 처리수
     */
    int insertRecapAsBstrCost(WsnyAsVisitCostMgtDvo dvo);

}
