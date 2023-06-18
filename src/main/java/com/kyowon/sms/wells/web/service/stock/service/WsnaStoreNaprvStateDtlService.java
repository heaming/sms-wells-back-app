package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaStoreNaprvStateDtlConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaStoreNaprvStateDtlDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaStoreNaprvStateDtlDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaStoreNaprvStateDtlMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 *  K-W-SV-U-0176P01 입고 미승인상세현황
 * </pre>
 *
 * @author heymi.cho
 * @since 2023.06.13
 */
@Service
@RequiredArgsConstructor
public class WsnaStoreNaprvStateDtlService {

    private final WsnaStoreNaprvStateDtlMapper mapper;
    private final WsnaStoreNaprvStateDtlConverter converter;

    /**
     * 입고 미승인상세현황 - 조회
     *
     * @param dto : { strWareNo:입고창고번호, itmPdCd:품목코드, startDate:적용시작일자, endDate:적용마감일자, ostrWareDvCd:출고창고구분 }
     * @return 조회결과
     */
    public PagingResult<WsnaStoreNaprvStateDtlDto.SearchRes> getStoreNaprvStateDtl(
        WsnaStoreNaprvStateDtlDto.SearchReq dto, PageInfo pageInfo
    ) {
        return mapper.selectStoreNaprvStateDtl(dto, pageInfo);
    }

    /**
     * 입고 미승인상세현황 - 조회 (엑셀 다운로드)
     *
     * @param dto : { strWareNo:입고창고번호, itmPdCd:품목코드, startDate:적용시작일자, endDate:적용마감일자, ostrWareDvCd:출고창고구분 }
     * @return 조회결과
     */
    public List<WsnaStoreNaprvStateDtlDto.SearchRes> getStoreNaprvStateDtl(WsnaStoreNaprvStateDtlDto.SearchReq dto) {
        return mapper.selectStoreNaprvStateDtl(dto);
    }

    /**
     * 입고 미승인상세현황 - 입고 확인 업데이트
     *
     * @param dtos : List<SaveReq { strWareNo: 입고창고번호, itmPdCd: 품목코드, itmOstrNo: 품목출고번호, ostrSn:출고일련번호, itmStrNo: 품목입고번호, strSn: 입고일련번호, itmGdCd: 품목등급, strQty: 입고수량, userId: 사용자ID }>
     * @return 조회결과
     */
    @Transactional
    public int saveStoreNaprvStateDtl(List<WsnaStoreNaprvStateDtlDto.SaveReq> dtos) {
        int processCount = 0;

        for (WsnaStoreNaprvStateDtlDto.SaveReq dto : dtos) {
            WsnaStoreNaprvStateDtlDvo dvo = converter.mapSaveReqToStoreNaprvStateDtlDvo(dto);
            System.out.println(dto);
            System.out.println(dvo);
            mapper.updateStoreNaprvStateDtlOstr(dvo);
            mapper.updateStoreNaprvStateDtlItmStock(dvo);
            mapper.updateStoreNaprvStateDtlStr(dvo);
            processCount++;
        }

        return processCount;
    }
}
