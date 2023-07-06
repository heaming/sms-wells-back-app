package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.common.mapper.WsnzHistoryMapper;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaSeedReleaseScheduleConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedReleaseScheduleMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * W-SV-U-0128M01 모종 출고 예정리스트 서비스
 * </pre>
 *
 * @author SaeRomI.Kim
 * @since 2023-07-03
 */

@Service
@RequiredArgsConstructor
public class WsnaSeedReleaseScheduleService {

    private final WsnaSeedReleaseScheduleMapper maaper;

    private final WsnaSeedReleaseScheduleConverter converter;

    private final WsnzHistoryMapper historyMapper;

    // 메시지 서비스
    private final MessageResourceService messageService;

    private static final String SV_BIZ_HCLSF_CD_INSTL = "1";
    private static final String SV_BIZ_HCLSF_CD_BS = "2";

    private static final String SPP_DV_CD_PCSV = "2";

    /**
     * 모종 출고 예정 리스트 페이징 조회
     * @param dto
     * @param pageInfo
     * @return
     */
    public PagingResult<SearchRes> getSeedReleaseSchedulesPaging(
        SearchReq dto, PageInfo pageInfo
    ) {

        PagingResult<WsnaSeedReleaseScheduleSearchDvo> pageRes = this.maaper
            .selectSeedReleaseSchedulesPaging(dto, pageInfo);

        List<WsnaSeedReleaseScheduleSearchDvo> dvos = pageRes.getList();
        List<SearchRes> searchRes = this.converter.mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(dvos);

        return new PagingResult<>(searchRes, pageInfo);
    }

    /**
     * 모종 출고 예정 리스트 엑셀 다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedReleaseSchedulesExcelDownload(SearchReq dto) {

        List<WsnaSeedReleaseScheduleSearchDvo> dvos = this.maaper.selectSeedReleaseSchedulesPaging(dto);

        return this.converter.mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(dvos);
    }

    /**
     * 모종 출고 예정 리스트 저장
     * @param dtos
     * @return
     */
    @Transactional
    public int editSeedReleaseSchedules(List<EditReq> dtos) {

        int count = 0;

        for (EditReq dto : dtos) {
            WsnaSeedReleaseScheduleDvo dvo = this.converter.mapEditReqToWsnaSeedReleaseScheduleDvo(dto);

            int result = this.maaper.updateSdingSppPlanIzDpDt(dvo);
            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            count += result;
        }

        return count;
    }

    /**
     * 모종 출고 예정 리스트 출고 확정
     * @param dtos
     * @return
     */
    @Transactional
    public int createSeedReleaseSchedulesForCnfm(List<CreateReq> dtos) {

        int count = 0;

        // 배양액 택배배송
        String svProcCn = this.messageService.getMessage("MSG_TXT_SDING_PCSV_FW");

        for (CreateReq dto : dtos) {
            WsnaSeedReleaseScheduleCnfmDvo dvo = this.converter.mapCreateReqToWsnaSeedReleaseScheduleCnfmDvo(dto);

            // 확정 데이터 체크
            Integer dupCnt = this.maaper.selectSdingSppCnfmIzCount(dvo);
            // 이미 완료 처리 되었습니다.
            BizAssert.isNull(dupCnt, "MSG_ALT_ALRDY_FSH_PROCS");

            int result = this.maaper.insertSdingSppCnfmIz(dvo);
            // 저장에 실패 하였습니다.
            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            // 서비스업무대분류코드
            String svBizHclsfCd = dvo.getSvBizHclsfCd();
            // 고객서비스배정번호
            String cstSvAsnNo = dvo.getCstSvAsnNo();

            // B/S가 아닌 경우
            if (!SV_BIZ_HCLSF_CD_BS.equals(svBizHclsfCd)) {
                // 고객서비스AS설치배정내역 저장
                this.maaper.updateCstSvasIstAsnIzForCnfm(cstSvAsnNo);
            }

            // 설치인 경우
            if (SV_BIZ_HCLSF_CD_INSTL.equals(svBizHclsfCd)) {
                String sdingMcnrCntrNo = dvo.getSdingMcnrCntrNo();

                // 고객서비스AS설치배정내역 저장
                this.maaper.updateCstSvasIstAsnIzForInstl(sdingMcnrCntrNo);
            }

            // 모종 출고확정일 저장
            this.maaper.updateSdingSppPlanIzForCnfm(dvo);

            // 배송구분
            String sppDvCd = dvo.getSppDvCd();
            // 택배인 경우
            if (!SV_BIZ_HCLSF_CD_BS.equals(svBizHclsfCd) && SPP_DV_CD_PCSV.equals(sppDvCd)) {
                // 서비스작업출고내역 데이터 생성
                this.maaper.insertSvWkOstrIzs(dvo);

                // 작업결과 조회
                dupCnt = this.maaper.selectCstSvWkRsIzCount(cstSvAsnNo);
                // 이미 완료 처리 되었습니다.
                BizAssert.isNull(dupCnt, "MSG_ALT_ALRDY_FSH_PROCS");

                // AS유형코드 조회
                WsnaSeedReleaseScheduleAsTpDvo asTpDvo = this.maaper.selectAsTpCdInfo(dvo);
                // 현장수당항목코드
                String siteAwAtcCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getSiteAwAtcCd();

                // 고객서비스AS설치배정내역 업데이트
                this.maaper.updateCstSvasIstAsnIz(cstSvAsnNo, svBizHclsfCd, siteAwAtcCd);

                // 로그 저장
                this.historyMapper.insertCstSvasIstAsnHistByPk(cstSvAsnNo);

                WsnaSeedReleaseScheduleWkRsDvo wkRsDvo = this.converter
                    .mapWsnaSeedReleaseScheduleCnfmDvoToWsnaSeedReleaseScheduleWkRsDvo(dvo);

                // AS위치코드
                String asLctCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAcLctCd();
                // AS현상코드
                String asPhnCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAsPhnCd();
                // AS원인코드
                String asCausCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAsCausCd();
                // 불량구분코드
                String badDvCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getBadDvCd();

                wkRsDvo.setAcLctCd(asLctCd);
                wkRsDvo.setAsPhnCd(asPhnCd);
                wkRsDvo.setAsCausCd(asCausCd);
                wkRsDvo.setBadDvCd(badDvCd);
                wkRsDvo.setSvProcsCn(svProcCn);

                // 작업결과저장
                this.maaper.insertCstSvWkRsIz(wkRsDvo);

                // 배송 업데이트
                this.maaper.updateSdingSppPlanIzForPcsv(dvo);
            }

            count += result;

        }

        return count;

    }

}
