package com.kyowon.sms.wells.web.service.common.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

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
    PagingResult<WsnyAsVisitCostMgtDvo> selectAsVisitCostPages(SearchReq searchReq, PageInfo pageInfo);

    /**
     * 유상 AS 출장비 관리 조회(엑셀 다운로드)
     * @param searchReq 조회조건
     * @return 조회결과
     */
    List<WsnyAsVisitCostMgtDvo> selectAsVisitCostPages(SearchReq searchReq);

    int updateAsVisitCostForRemove(String pdCd, int izSn);

    int updateAsVisitCostEnddt(String pdCd, String apyStrtdt);

    int selectMaxIzSn(String pdCd);

    int insertRecapAsBstrCost(WsnyAsVisitCostMgtDvo dvo);

    int updateRecapAsBstrCost(WsnyAsVisitCostMgtDvo dvo);

}
