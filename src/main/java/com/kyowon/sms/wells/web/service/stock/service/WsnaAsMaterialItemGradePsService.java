package com.kyowon.sms.wells.web.service.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialItemGradePsDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialItemGradePsDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialItemGradePsMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0257M01 AS자재 품목등급현황 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-20
 */

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialItemGradePsService {

    private final WsnaAsMaterialItemGradePsMapper mapper;

    // 메시지 서비스
    private final MessageResourceService messageService;

    /**
     * 창고 조회 (PIVOT Header 용)
     * @param dto
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(WsnaAsMaterialItemGradePsDto.SearchWareReq dto) {

        ValidAssert.notNull(dto);
        // 기준년월
        String baseYm = dto.baseYm();
        ValidAssert.hasText(baseYm);

        // 현재년월
        String nowYm = DateUtil.getNowDayString().substring(0, 6);
        // 기준년월 > 현재년월일 경우 메시지 처리
        BizAssert.isTrue(nowYm.compareTo(baseYm) >= 0, "MSG_ALT_INQR_CRTL_MM_PSB");

        // 창고 리스트 조회
        String allText = this.messageService.getMessage("MSG_TXT_ALL");
        return this.mapper.selectMcByWares(baseYm, allText);
    }

    /**
     * AS자재 품목등급현황 페이징 조회
     * @param dvo
     * @return
     */
    public PagingResult<HashMap<String, String>> getAsMaterialItemGradePsPaging(
        WsnaAsMaterialItemGradePsDvo dvo, PageInfo pageInfo
    ) {

        ValidAssert.notNull(dvo);
        ValidAssert.notNull(pageInfo);

        // 기준년월
        String baseYm = dvo.getBaseYm();
        ValidAssert.hasText(baseYm);

        // 현재년월
        String nowYm = DateUtil.getNowDayString().substring(0, 6);
        // 기준년월 > 현재년월일 경우 메시지 처리
        BizAssert.isTrue(nowYm.compareTo(baseYm) >= 0, "MSG_ALT_INQR_CRTL_MM_PSB");

        // 창고 리스트 조회
        List<WsnzWellsCodeWareHouseDvo> wares = this.mapper.selectMcByWares(baseYm, "");

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS GD_" + item.getWareNo())
            .collect(Collectors.joining(","));
        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "D2.GD_" + item.getWareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);

        return this.setAsMaterialItemGradePsPaging(dvo, pageInfo);
    }

    /**
     * AS자재 품목등급현황 페이징 처리
     * @param dvo
     * @param pageInfo
     * @return
     */
    private PagingResult<HashMap<String, String>> setAsMaterialItemGradePsPaging(
        WsnaAsMaterialItemGradePsDvo dvo, PageInfo pageInfo
    ) {

        boolean needTotalCount = pageInfo.isNeedTotalCount();
        // 총 건수를 조회해야 할 경우
        if (needTotalCount) {
            // 건수 조회
            long totalCount = this.mapper.selectAsMaterialItemGradePsCount(dvo);
            pageInfo.setTotalCount(totalCount);
            pageInfo.setNeedTotalCount(Boolean.FALSE);
        }

        int pageIndex = pageInfo.getPageIndex();
        int pageSize = pageInfo.getPageSize();

        int startRow = (pageIndex - 1) * pageSize;
        dvo.setOffSet(startRow);
        dvo.setSize(pageSize);

        List<HashMap<String, String>> itemGradePs = this.mapper.selectAsMaterialItemGradePs(dvo);

        return new PagingResult<>(
            itemGradePs, pageInfo
        );
    }

    /**
     * AS자재 품목등급현황 엑셀 다운로드
     * @param dvo
     * @return
     */
    public List<HashMap<String, String>> getAsMaterialItemGradePsExcelDownload(WsnaAsMaterialItemGradePsDvo dvo) {

        ValidAssert.notNull(dvo);

        // 기준년월
        String baseYm = dvo.getBaseYm();
        ValidAssert.hasText(baseYm);

        // 현재년월
        String nowYm = DateUtil.getNowDayString().substring(0, 6);
        // 기준년월 > 현재년월일 경우 메시지 처리
        BizAssert.isTrue(nowYm.compareTo(baseYm) >= 0, "MSG_ALT_INQR_CRTL_MM_PSB");

        // 창고 리스트 조회
        List<WsnzWellsCodeWareHouseDvo> wares = this.mapper.selectMcByWares(baseYm, "");

        // PIVOT 창고 조건 변환
        String wareNoInStr = wares.stream()
            .map(item -> "'" + item.getWareNo() + "' AS GD_" + item.getWareNo())
            .collect(Collectors.joining(","));
        // PIVOT 필드
        String wareNoFields = wares.stream()
            .map(item -> "D2.GD_" + item.getWareNo())
            .collect(Collectors.joining(","));

        dvo.setWareNoInStr(wareNoInStr);
        dvo.setWareNoFields(wareNoFields);

        return this.mapper.selectAsMaterialItemGradePs(dvo);
    }

}
