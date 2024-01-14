package com.kyowon.sms.wells.web.service.common.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.converter.WsnyAsVisitCostMgtConverter;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.RemoveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.common.dto.WsnyAsVisitCostMgtDto.SearchReq;
import com.kyowon.sms.wells.web.service.common.dvo.WsnyAsVisitCostMgtDvo;
import com.kyowon.sms.wells.web.service.common.mapper.WsnyAsVisitCostMgtMapper;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * <pre>
 * W-SV-U-0101M01 유상 AS 출장비 관리
 * </pre>
 *
 * @author gs.piit122 김동엽
 * @since 2022.11.18
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class WsnyAsVisitCostMgtService {
    private final WsnyAsVisitCostMgtMapper mapper;
    private final WsnyAsVisitCostMgtConverter converter;

    /**
     * 유상 AS 출장비 관리 조회(페이징)
     *
     * @param searchReq : { pdCd:상품코드 }
     * @param pageInfo : 페이징정보
     * @return 조회결과
     */
    public PagingResult<WsnyAsVisitCostMgtDvo> getAsVisitCostPages(
        SearchReq searchReq, PageInfo pageInfo
    ) {
        return mapper.selectAsVisitCostPages(searchReq, pageInfo);
    }

    /**
     * 유상 AS 출장비 관리 조회(엑셀 다운로드)
     * @param searchReq : { pdCd:상품코드 }
     * @return 조회결과
     */
    public List<WsnyAsVisitCostMgtDvo> getAsVisitCostForExcelDownload(SearchReq searchReq) {
        return mapper.selectAsVisitCostPages(searchReq);
    }

    /**
     * 유상 AS 출장비 삭제
     * @param dtos
     * @return
     */
    @Transactional
    public int removeAsVisitCosts(List<RemoveReq> dtos) {

        int count = 0;

        for (RemoveReq dto : dtos) {
            String pdCd = dto.pdCd();
            int izSn = dto.izSn();

            // 데이터 삭제
            count += this.mapper.updateAsVisitCostForRemove(pdCd, izSn);

            // 이전 데이터 적용종료일 수정
            if (izSn > 1) {
                this.mapper.updateAsVisitCostEnddt(pdCd, null);
            }
        }

        return count;
    }

    /**
     * 유상 AS 출장비 관리 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int saveAsVisitCosts(List<SaveReq> dtos) {

        int count = 0;

        for (SaveReq dto : dtos) {
            // row 상태
            String rowState = dto.rowState();
            // 품목코드
            String pdCd = dto.pdCd();
            // 적용시작일자
            String apyStrtdt = dto.apyStrtdt();
            // 적용종료일자
            String apyEnddt = dto.apyEnddt();

            WsnyAsVisitCostMgtDvo dvo = this.converter.mapSaveReqToWsnyAsVisitCostMgtDvo(dto);

            // 신규일 경우
            if (CommConst.ROW_STATE_CREATED.equals(rowState)) {

                // 일련번호 생성
                int newIzSn = this.mapper.selectMaxIzSn(pdCd);
                dvo.setIzSn(newIzSn);

                if (newIzSn > 1) {
                    this.mapper.updateAsVisitCostEnddt(pdCd, apyStrtdt);
                }

                // 유효성 체크
                String validPdCd = this.mapper.selectPdCdValid(pdCd, apyStrtdt, apyEnddt);
                // 해당 적용일자에 등록된 데이터가 있습니다. [품목코드 : {0}]
                BizAssert.isNull(validPdCd, "MSG_ALT_APY_DT_IS_EXISTS", new String[] {pdCd});

                count += this.mapper.insertRecapAsBstrCost(dvo);

            } else {
                count += this.mapper.updateRecapAsBstrCost(dvo);
            }
        }

        return count;
    }

}
