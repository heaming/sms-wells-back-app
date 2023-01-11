package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.Warehouse;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskRgstDto;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.WarehouseReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaOutOfStorageAskMngtMapper;

import lombok.RequiredArgsConstructor;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaOutOfStorageAskMngtDto.*;

/**
 *
 * <pre>
 * W-SV-U-0117M01 출고요청 관리
 * </pre>
 *
 * @author songtaesung
 * @since 2022.12.26
 */
@Service
@RequiredArgsConstructor
public class WsnaOutOfStorageAskMngtService {

    private final WsnaOutOfStorageAskMngtMapper mapper;

    /**
     * 출고요청 관리 - 조회
     * @param dto : { strOjWareNo: 출고요청창고, ostrAkTpCd: 출고요청유형코드, startStrHopDt: 입고희망일자 시작일, endStrHopDt: 입고희망일자 종료일, wareDvCd: 출고요청 접수창고, wareLocaraCd: 창고지역코드 }
     * @return 조회결과
     */
    public List<SearchRes> getOutOfStorageAsks(SearchReq dto) {
        return this.mapper.selectOutOfStorageAsks(dto);
    }

    /**
     * 출고요청 관리 - 로그인 사용자 출고요청창고 조회
     * @param dto : { session.userid : 사용자id , apyYm : 기준년월 }
     * @return 조회결과
     */
    public List<Warehouse> getOutOfStorageAskWares(WarehouseReq dto) {
        return this.mapper.selectWarehouses(dto);
    }

    public FindRes getOutOfStorageAskItems(FindReq dto) {
        return this.mapper.selectOutOfStorageAskItms(dto).orElseThrow(
            () -> new BizException("MSG_ALT_NO_DATA")
        );
    }

    public PagingResult<WsnaOutOfStorageAskMngtDto.OutOfRes> getOutOfStorageItemPages(
        WsnaOutOfStorageAskMngtDto.SearchReq dto, PageInfo pageInfo
    ) {
        return this.mapper.selectOutOfStorageItms(dto, pageInfo);
    }
}
