package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaAsMaterialsItemGradeDto;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaAsMaterialsItemGradeWareDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaAsMaterialsItemGradeMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0115M01 AS자재 품목등급관리 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-06-15
 */

@Service
@RequiredArgsConstructor
public class WsnaAsMaterialsItemGradeService {

    private final WsnaAsMaterialsItemGradeMapper mapper;

    /**
     * 창고리스트 조회
     * @param dto   (필수) 조회조건
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getWareHouses(WsnaAsMaterialsItemGradeDto.SearchWareReq dto) {
        ValidAssert.notNull(dto);
        ValidAssert.hasText(dto.baseYm());
        ValidAssert.hasText(dto.wareDvCd());

        return this.mapper.selectWareHouses(dto);
    }

    /**
     * AS자재 품목등급 조회
     * @param dto       (필수) 조회조건
     * @param pageInfo  (필수) 페이징 정보
     * @return
     */
    public PagingResult<WsnaAsMaterialsItemGradeDto.SearchRes> getAsMaterialsItemGradePages(
        WsnaAsMaterialsItemGradeDto.SearchReq dto, PageInfo pageInfo
    ) {

        ValidAssert.notNull(dto);
        ValidAssert.notNull(pageInfo);

        // 기준년월
        String baseYm = dto.baseYm();
        // 현재년월
        String nowYm = DateUtil.getNowDayString().substring(0, 6);
        // 기준년월 > 현재년월일 경우 메시지 처리
        BizAssert.isTrue(nowYm.compareTo(baseYm) >= 0, "MSG_ALT_INQR_CRTL_MM_PSB");

        // 창고세부구분코드
        String wareDtlDvCd = dto.wareDtlDvCd();

        // 창고세부구분코드가 전체인 경우
        if (StringUtils.isEmpty(wareDtlDvCd)) {
            return this.mapper.selectAsMaterialsItemGradePages(dto, pageInfo);
        } else {
            return this.mapper.selectAsMaterialsItemGradePagesForWare(dto, pageInfo);
        }
    }

    /**
     * AS자재 품목등급 조회 - 엑셀 다운로드
     * @param dto       (필수) 조회조건
     * @return
     */
    public List<WsnaAsMaterialsItemGradeDto.SearchRes> getAsMaterialsItemGradesExcelDownload(
        WsnaAsMaterialsItemGradeDto.SearchReq dto
    ) {
        ValidAssert.notNull(dto);

        // 기준년월
        String baseYm = dto.baseYm();
        // 현재년월
        String nowYm = DateUtil.getNowDayString().substring(0, 6);
        // 기준년월 > 현재년월일 경우 메시지 처리
        BizAssert.isTrue(nowYm.compareTo(baseYm) >= 0, "MSG_ALT_INQR_CRTL_MM_PSB");

        // 창고세부구분코드
        String wareDtlDvCd = dto.wareDtlDvCd();

        // 창고세부구분코드가 전체인 경우
        if (StringUtils.isEmpty(wareDtlDvCd)) {
            return this.mapper.selectAsMaterialsItemGradePages(dto);
        } else {
            return this.mapper.selectAsMaterialsItemGradePagesForWare(dto);
        }
    }

    /**
     * AS자재 품목등급 데이터 생성 중복체크
     * @param dvo   (필수) 품목등급 데이터 생성 체크 dvo
     * @return 데이터 생성 중복 여부
     */
    public String getCreateAsMaterialsDuplication(WsnaAsMaterialsItemGradeDvo dvo) {

        ValidAssert.notNull(dvo);
        // 기준년월
        String baseYm = dvo.getBaseYm();
        ValidAssert.hasText(baseYm);
        ValidAssert.hasText(dvo.getItmKndCd());

        // 데이터가 생성되었는지 체크
        Integer createCount = this.mapper.selectCstSvItmGdIzCount(dvo);

        return createCount == null ? "N" : "Y";
    }

    /**
     * AS자재 품목등급 데이터 생성
     * @param dvo   (필수) 품목등급 데이터 생성 dvo
     * @return 품목등급 데이터 생성 건수
     */
    @Transactional
    public int createAsMaterialsItemGrade(WsnaAsMaterialsItemGradeDvo dvo) {

        ValidAssert.notNull(dvo);
        // 기준년월
        String baseYm = dvo.getBaseYm();
        ValidAssert.hasText(baseYm);
        ValidAssert.hasText(dvo.getItmKndCd());

        // 데이터가 생성되었는지 체크
        Integer createCount = this.mapper.selectCstSvItmGdIzCount(dvo);
        String year = baseYm.substring(0, 4);
        String month = baseYm.substring(4, 6);
        BizAssert.isFalse(createCount != null, "MSG_TXT_EXIST_NEW_DATA", new String[] {year + "-" + month});

        int count = 0;

        // 현재년월에 해당하는 창고리스트 조회
        List<WsnaAsMaterialsItemGradeWareDvo> wareDvos = this.mapper.selectMcbyWareList();
        if (CollectionUtils.isNotEmpty(wareDvos)) {

            for (WsnaAsMaterialsItemGradeWareDvo wareDvo : wareDvos) {
                // 창고번호
                String wareNo = wareDvo.getWareNo();
                // 창고구분코드
                String wareDvCd = wareDvo.getWareDvCd();

                dvo.setWareNo(wareNo);
                dvo.setWareDvCd(wareDvCd);

                count += this.mapper.insertCstSvItmGd(dvo);
            }
        }

        return count;
    }

    /**
     * AS자재 품목등급 데이터 저장
     * @param dvos  (필수) 변경된 데이터 리스트
     * @return 품목등급 데이터 저장 건수
     */
    @Transactional
    public int saveAsMaterialsItemGrades(List<WsnaAsMaterialsItemGradeDvo> dvos) {
        ValidAssert.notEmpty(dvos);

        int count = 0;

        for (WsnaAsMaterialsItemGradeDvo dvo : dvos) {
            ValidAssert.hasText(dvo.getBaseYm());
            ValidAssert.hasText(dvo.getItmPdCd());
            ValidAssert.hasText(dvo.getCtrItmMngtGdCd());

            count += this.mapper.insertCstSvItmGdForSave(dvo);
        }

        return count;
    }

}
