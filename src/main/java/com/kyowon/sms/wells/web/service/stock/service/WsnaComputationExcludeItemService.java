package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaComputationExcludeItemDto.*;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.stock.converter.WsnaComputationExcludeItemConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaComputationExcludeItemPdDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaComputationExcludeItemMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0296P01 산출 제외품목 등록 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-23
 */

@Service
@RequiredArgsConstructor
public class WsnaComputationExcludeItemService {

    private final WsnaComputationExcludeItemMapper mapper;

    private final WsnaComputationExcludeItemConverter converter;

    /**
     * 품목 조회
     * @param dto
     * @return
     */
    public List<WsnaComputationExcludeItemPdDvo> getProducts() {

        return this.mapper.selectProducts();
    }

    /**
     * 산출 제외품목 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getCountExcludeItemsPaging(SearchReq dto, PageInfo pageInfo) {
        ValidAssert.notNull(dto);
        ValidAssert.notNull(pageInfo);

        return this.mapper.selectCmptExcdItms(dto, pageInfo);
    }

    /**
     * 산출 제외품목 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getCountExcludeItemsExcelDownload(SearchReq dto) {
        ValidAssert.notNull(dto);

        return this.mapper.selectCmptExcdItms(dto);
    }

    /**
     * 산출 제외품목 전월 데이터 이관
     * @param dto
     * @return
     */
    @Transactional
    public int createCountExcludeItemForTransfers(TransferReq dto) {
        ValidAssert.notNull(dto);

        // 조회년월
        String inqrYm = dto.inqrYm();
        ValidAssert.hasText(inqrYm);

        return this.mapper.insertCmptExcdItmForTransfer(inqrYm);
    }

    /**
     * 산출 제외품목 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int updateCountExcludeItemForRemove(List<RemoveReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int count = 0;

        for (RemoveReq dto : dtos) {
            WsnaComputationExcludeItemDvo dvo = this.converter.mapRemoveReqToWsnaComputationExcludeItemDvo(dto);
            int result = this.mapper.updateCmptExcdItmForRemove(dvo);

            // 삭제에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_DEL_ERR");
            count += result;
        }

        return count;
    }

    /**
     * 산출 제외품목 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveCountExcludeItem(List<SaveReq> dtos) {
        ValidAssert.notEmpty(dtos);

        int count = 0;

        for (SaveReq dto : dtos) {
            WsnaComputationExcludeItemDvo dvo = this.converter.mapSaveReqToWsnaComputationExcludeItemDvo(dto);

            // row상태
            String rowState = dto.rowState();
            int result = 0;

            // 생성
            if (CommConst.ROW_STATE_CREATED.equals(rowState)) {

                result = this.mapper.insertCmptExcdItm(dvo);

                // 수정
            } else if (CommConst.ROW_STATE_UPDATED.equals(rowState)) {
                result = this.mapper.updateCmptExcdItm(dvo);
            }

            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");
            count += result;
        }

        return count;
    }

}
