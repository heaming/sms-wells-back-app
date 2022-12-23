package com.kyowon.sms.wells.web.service.stock.service;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaWarehouseOgConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.CountReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseOgDvo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.SearchRes;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.SearchReq;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaWarehouseOgMapper;

import java.util.List;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaWarehouseOgDto.*;

/**
 * <pre>
 * Class Description
 * </pre>
 *
 * @author gs.piit58 송태성
 * @since 2022.12.08
 */
@Service
@RequiredArgsConstructor
public class WsnaWarehouseOgService {

    private final WsnaWarehouseOgMapper mapper;
    private final WsnaWarehouseOgConverter converter;

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
}
