package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOrganizationConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.CountReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOrganizationMapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOrganizationDto.*;

/**
 * <pre>
 * Class Description
 * </pre>
 *
 * @author songTaeSung
 * @since 2022.12.08
 */
@Service
@RequiredArgsConstructor
public class WsnaWarehouseOrganizationService {

    private final WsnaWarehouseOrganizationMapper mapper;
    private final WsnaWarehouseOrganizationConverter converter;

    /**
     * 창고조직관리 - 조회
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */

    public List<SearchRes> getWarehouseOgs(SearchReq dto) {
        return this.mapper.selectWarehouseOgs(dto);
    }

    /**
     * 창고조직이월 - 조회
     *
     * @param dto : { baseYm : 기준년월}
     * @return 조회결과
     */
    public int getWareCarriedCounter(CountReq dto) {
        return this.mapper.selectWareCarriedCounter(dto);
    }

    public int createWareCarried(CreateReq dto) {

        int processCount = 0;

        processCount += this.mapper.insertWareCarried(dto);
        return processCount;
    }

    /**
     * 창고조직관리 - 엑셀 다운로드
     *
     * @param dto : { baseYm : 기준년월, wareDv : 창고구분, codeUseYn : 사용여부, wareLocaraCd : 창고지역코드}
     * @return 조회결과
     */
    public List<SearchRes> getWarehouseOgsExcelDownload(SearchReq dto) {
        return this.mapper.selectWarehouseOgs(dto);
    }
}
