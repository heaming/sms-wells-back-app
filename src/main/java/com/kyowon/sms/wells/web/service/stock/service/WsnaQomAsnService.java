package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaQomAsnDto.*;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.dvo.WsnzWellsCodeWareHouseDvo;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaQomAsnConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnCreateDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnIndividualSearchDvo;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaQomAsnWareRenewalDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaQomAsnMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.ValidAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0190M01, W-SV-U-0191M01 개인창고, 독립창고 물량배정 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-11
 */

@Service
@RequiredArgsConstructor
public class WsnaQomAsnService {

    private final WsnaQomAsnMapper mapper;

    private final WsnaQomAsnConverter converter;

    /**
     * 물량배정 출고창고 조회
     * @param apyYm (필수) 배정년월
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getQomAsnOstrWares(String apyYm) {
        ValidAssert.hasText(apyYm);

        return this.mapper.selectQomAsnOstrWares(apyYm);
    }

    /**
     * 물량배정 입고창고 조회
     * @param apyYm         (필수) 배정년월
     * @param wareDvCd      (필수) 창고구분코드
     * @param wareDtlDvCd   (필수) 창고세부구분코드
     * @return
     */
    public List<WsnzWellsCodeWareHouseDvo> getQomAsnStrWares(String apyYm, String wareDvCd, String wareDtlDvCd) {
        ValidAssert.hasText(apyYm);
        ValidAssert.hasText(wareDvCd);
        ValidAssert.hasText(wareDtlDvCd);

        return this.mapper.selectQomAsnStrWares(apyYm, wareDvCd, wareDtlDvCd);
    }

    /**
     * 물량배정 건수 조회
     * @param asnOjYm
     * @param cnt
     * @return
     */
    public String getQomAsnExistCheck(String asnOjYm, int cnt) {
        // 물량배정 건수 체크 (배정년월, 회차)
        Integer count = this.mapper.selectQomAsnCount(asnOjYm, cnt);

        return count == null ? "N" : "Y";
    }

    /**
     * 개인창고 물량배정 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getQomAsnsForIndividual(SearchReq dto, PageInfo pageInfo) {

        PagingResult<WsnaQomAsnIndividualSearchDvo> result = this.mapper.selectQomAsnsForIndividual(dto, pageInfo);
        List<WsnaQomAsnIndividualSearchDvo> dvos = result.getList();

        List<SearchRes> resDtos = this.converter.mapAllWsnaQomAsnIndividualSearchDvoToSearchRes(dvos);

        return new PagingResult<>(resDtos, pageInfo);
    }

    /**
     * 개인창고 물량배정 데이터 생성
     * @param dto
     * @return
     */
    @Transactional(timeout = -1)
    public int createQomAsnForIndividual(CreateReq dto) {

        // 기준년월
        String apyYm = dto.apyYm();
        // 배정년월
        String asnOjYm = dto.asnOjYm();
        // 회차
        int cnt = dto.cnt();

        List<WsnaQomAsnCreateDvo> dvos = null;

        // 1회차 이고 기준년월과 배정년월이 다를 경우
        if (cnt == 1 && !apyYm.equals(asnOjYm)) {
            dvos = this.mapper.selectQomAsnFirstTnIndividualsForCreate(dto);
        } else {
            dvos = this.mapper.selectQomAsnIndividualsForCreate(dto);
        }

        return CollectionUtils.isNotEmpty(dvos) ? this.mapper.insertItmQomAsnIz(dvos) : 0;
    }

    /**
     * 개인창고 물량배정 엑셀 다운로드 조회
     * @param dto
     * @return
     */
    public List<SearchRes> getQomAsnsExcelDownloadForIndividual(SearchReq dto) {

        List<WsnaQomAsnIndividualSearchDvo> dvos = this.mapper.selectQomAsnsForIndividual(dto);

        return this.converter.mapAllWsnaQomAsnIndividualSearchDvoToSearchRes(dvos);
    }

    /**
     * 창고갱신
     * @param dto
     * @return
     */
    public int editQomAsnForWareRenewalDvo(EditReq dto) {

        WsnaQomAsnWareRenewalDvo dvo = this.converter.mapEditReqToWsnaQomAsnWareRenewalDvo(dto);

        return this.mapper.updateRgbsPuItmForWareRenewal(dvo);
    }
}
