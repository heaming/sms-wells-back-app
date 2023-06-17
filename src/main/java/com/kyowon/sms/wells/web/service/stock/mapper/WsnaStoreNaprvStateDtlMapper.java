package com.kyowon.sms.wells.web.service.stock.mapper;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto.SearchReq;
import static com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto.SearchRes;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStoreNaprvStateDtlDvo;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

/**
 *
 * <pre>
 * 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho 조혜미
 * @since 2023.06.13
 */
@Mapper
public interface WsnaStoreNaprvStateDtlMapper {
    /**
     * 조회 (페이징)
     *
     * @param dto : SearchReq(strWareNo-입고창고번호, itmPdCd-품목코드, startDate-적용시작일자, endDate-적용마감일자, ostrWareDvCd-출고창고구분)
     * @return 조회결과
     */
    PagingResult<SearchRes> selectStoreNaprvStateDtl(
        SearchReq dto, PageInfo pageInfo
    );

    /**
     * 조회
     *
     * @param dto : SearchReqSearchReq(strWareNo-입고창고번호, itmPdCd-품목코드, startDate-적용시작일자, endDate-적용마감일자, ostrWareDvCd-출고창고구분)
     * @return 조회결과
     */
    List<SearchRes> selectStoreNaprvStateDtl(
        SearchReq dto
    );

    /**
     * 입고 미승인 현황 업데이트 -- 품목출고내역
     * @param dvo : WsnaStoreNaprvStateDtlDvo dvo
     * @return 조회결과 update된 데이터 수
     */
    int updateStoreNaprvStateDtlOstr(WsnaStoreNaprvStateDtlDvo dvo);

    /**
    * 입고 미승인 현황 업데이트 -- 품목입고내역
    * @param dvo : WsnaStoreNaprvStateDtlDvo dvo
    * @return 조회결과 update된 데이터 수
    */
    int updateStoreNaprvStateDtlStr(WsnaStoreNaprvStateDtlDvo dvo);

    /**
    * 입고 미승인 현황 업데이트 -- 고객서비스품목재고내역
    * @param dvo : WsnaStoreNaprvStateDtlDvo dvo
    * @return 조회결과 update된 데이터 수
    */
    int updateStoreNaprvStateDtlItmStock(WsnaStoreNaprvStateDtlDvo dvo);
}
