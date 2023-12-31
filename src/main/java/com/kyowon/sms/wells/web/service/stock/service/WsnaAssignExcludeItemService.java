package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaAssignExcludeItemDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaAssignExcludeItemConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAssignExcludeItemRemoveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAssignExcludeItemMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0189P01 배정제외품목 등록 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-14
 */

@Service
@RequiredArgsConstructor
public class WsnaAssignExcludeItemService {

    private final WsnaAssignExcludeItemMapper mapper;

    private final WsnaAssignExcludeItemConverter converter;

    /**
     * 배정제외품목 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getAssignExcludeItemsPaging(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectAssignExcludeItemsPaging(dto, pageInfo);
    }

    /**
     * 창고조회
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getWarehouses() {
        return this.mapper.selectWarehouses();
    }

    /**
     * 배정제외 품목 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int removeAssignExcludeItems(List<RemoveReq> dtos) {

        int count = 0;

        List<WsnaAssignExcludeItemRemoveDvo> dvos = this.converter.mapAllRemoveReqToWsnaAssignExcludeItemDelDvo(dtos);

        for (WsnaAssignExcludeItemRemoveDvo dvo : dvos) {
            count += this.mapper.updateQomAsnExcdIzForRemove(dvo);
        }

        return count;
    }

    /**
     * 배정제외 품목 등록
     * @param list
     * @return
     */
    @Transactional
    public int createAssignExcludeItems(List<CreateReq> list) {
        int cnt = 0;
        List<WsnaAssignExcludeItemDvo> dvoList = this.converter.mapAllCreateReqToWsnaAssignExcludeItemDvo(list);
        for (WsnaAssignExcludeItemDvo dvo : dvoList) {
            cnt += mapper.insertQomAsnExcdIz(dvo);
        }
        return cnt;
    }
}
