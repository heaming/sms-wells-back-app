package com.kyowon.sms.wells.web.service.stock.service;

import static com.kyowon.sms.wells.web.service.stock.dto.WsnaSeedReleaseScheduleDto.*;
import static com.kyowon.sms.wells.web.service.visit.dto.WsnbIndividualVisitPrdDto.SearchProcessReq;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncRegularBfsvcAsnDvo;
import com.kyowon.sms.wells.web.service.allocate.service.WsncRegularBfsvcAsnService;
import com.kyowon.sms.wells.web.service.common.service.WsnzHistoryService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaSeedReleaseScheduleConverter;
import com.kyowon.sms.wells.web.service.stock.dvo.*;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaSeedReleaseScheduleMapper;
import com.kyowon.sms.wells.web.service.visit.service.WsnbIndividualVisitPrdService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;
import com.sds.sflex.system.config.validation.ValidAssert;

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

    private final WsnzHistoryService historyService;

    // 메시지 서비스
    private final MessageResourceService messageService;

    // BS주기표 서비스
    private final WsnbIndividualVisitPrdService visitPrdService;

    // 계약서비스
    private final WctaInstallationReqdDtInService installationReqdDtInService;

    // BS배정 서비스
    private final WsncRegularBfsvcAsnService bfsvcAsnService;

    private static final String SV_BIZ_HCLSF_CD_INSTL = "1";
    private static final String SV_BIZ_HCLSF_CD_BS = "2";
    private static final String PKG_DV_CD_FLORIN = "4";
    private static final String SV_BIZ_DCLSF_CD_PD_SPP = "1112";

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
     * 모종 출고 예정리스트 플로린 확정 엑셀다운로드
     * @param dto
     * @return
     */
    public List<SearchRes> getSeedReleaseSchedulesForFlorin(SearchReq dto) {

        List<WsnaSeedReleaseScheduleSearchDvo> dvos = this.maaper.selectSeedReleaseSchedulesForFlorin(dto);

        return this.converter.mapAllWsnaSeedReleaseScheduleSearchDvoToSearchRes(dvos);
    }

    /**
     * 모종 출고 예정 리스트 집계표 조회
     * @param dto
     * @return
     */
    public List<WsnaSeedReleaseScheduleAggDvo> getSeedReleaseAggregations(SearchReq dto) {
        return this.maaper.selectSeedReleaseAggregations(dto);
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
    public int createSeedReleaseSchedulesForCnfm(List<CreateReq> dtos) throws Exception {

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
                // 현재일자
                String curDt = DateUtil.getNowDayString();
                // 패키지구분
                String pkgDvCd = dto.pkgDvCd();
                // 서비스업무세분류코드
                String svBizDclsfCd = dvo.getSvBizDclsfCd();
                // 출고예정일
                String sppDuedt = dvo.getSppDuedt();

                String cntrNo = dvo.getCntrNo();
                int cntrSn = dvo.getCntrSn();

                // 수행내역에 설치일자 저장
                this.maaper.updateCstSvExcnIzForInstl(cntrNo, cntrSn, curDt);

                // 계약정보 update
                this.installationReqdDtInService
                    .saveInstallReqdDt(cntrNo, String.valueOf(cntrSn), curDt, "", sppDuedt);

                // BS주기표 생성
                SearchProcessReq visitDto = this.convertVisitPrdProcessReq(cntrNo, String.valueOf(cntrSn), curDt);
                this.visitPrdService.processVisitPeriodRegen(visitDto);

                // 플로린이면서 제품배송일 경우 웰컴 BS 서비스 호출
                if (PKG_DV_CD_FLORIN.equals(pkgDvCd) && SV_BIZ_DCLSF_CD_PD_SPP.equals(svBizDclsfCd)) {
                    this.createWelcomeBS(cntrNo, cntrSn, curDt);
                }
            }

            // 모종 출고확정일 저장
            this.maaper.updateSdingSppPlanIzForCnfm(dvo);

            // 배송구분
            String sppDvCd = dvo.getSppDvCd();
            // 택배인 경우
            if (SPP_DV_CD_PCSV.equals(sppDvCd)) {

                // B/S가 아닌 경우
                if (!SV_BIZ_HCLSF_CD_BS.equals(svBizHclsfCd)) {
                    // 택배발송 처리
                    this.saveSeedReleasePcsvForAS(dvo, svProcCn);

                    // B/S인 경우
                } else {
                    this.saveSeedReleasePcsvForBS(dvo, svProcCn);
                }

                // 배송 업데이트
                this.maaper.updateSdingSppPlanIzForPcsv(dvo);
            }

            count += result;

        }

        return count;
    }

    /**
     * 웰컴BS 데이터 생성
     * @param cntrNo
     * @param cntrSn
     * @param istDt
     * @throws Exception
     */
    @Transactional
    public void createWelcomeBS(String cntrNo, int cntrSn, String istDt) throws Exception {
        // 주기표 데이터 생성
        this.maaper.insertWelcomeBS(cntrNo, cntrSn, istDt);

        WsncRegularBfsvcAsnDvo dvo = new WsncRegularBfsvcAsnDvo();
        dvo.setAsnOjYm(istDt.substring(0, 6));
        dvo.setCntrNo(cntrNo);
        dvo.setCntrSn(String.valueOf(cntrSn));

        // BS 배정 생성
        this.bfsvcAsnService.processRegularBfsvcAsn(dvo);
    }

    /**
     * 설치/AS 택배발송 처리
     * @param dvo
     * @param svProcCn
     */
    @Transactional
    public void saveSeedReleasePcsvForAS(WsnaSeedReleaseScheduleCnfmDvo dvo, String svProcCn) {
        ValidAssert.notNull(dvo);

        // 서비스업무대분류코드
        String svBizHclsfCd = dvo.getSvBizHclsfCd();
        // 고객서비스배정번호
        String cstSvAsnNo = dvo.getCstSvAsnNo();

        // 서비스작업출고내역 데이터 생성
        this.maaper.insertSvWkOstrIzsForAS(dvo);

        // AS유형코드 조회
        WsnaSeedReleaseScheduleAsTpDvo asTpDvo = this.maaper.selectAsTpCdInfo(dvo);
        // 현장수당항목코드
        String siteAwAtcCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getSiteAwAtcCd();

        // 고객서비스AS설치배정내역 업데이트
        this.maaper.updateCstSvasIstAsnIz(cstSvAsnNo, svBizHclsfCd, siteAwAtcCd);

        // 로그 저장
        this.historyService.createCstSvasIstAsnHistByPk(cstSvAsnNo);

        // 작업결과 조회
        Integer dupCnt = this.maaper.selectCstSvWkRsIzCount(cstSvAsnNo);
        if (dupCnt == null) {
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
        }
    }

    /**
     * BS 택배발송 처리
     * @param dvo
     * @param svProcCn
     */
    @Transactional
    public void saveSeedReleasePcsvForBS(WsnaSeedReleaseScheduleCnfmDvo dvo, String svProcCn) {
        ValidAssert.notNull(dvo);

        // 서비스업무대분류코드
        String svBizHclsfCd = dvo.getSvBizHclsfCd();
        // 고객서비스배정번호
        String cstSvAsnNo = dvo.getCstSvAsnNo();
        // 계약번호
        String cntrNo = dvo.getCntrNo();
        // 계약일련번호
        int cntrSn = dvo.getCntrSn();
        // 방문예정일자
        String vstDuedt = dvo.getVstDuedt();
        // 모종1상품코드
        String pdCd = dvo.getSdingPdCd1();
        // 업무세분류코드
        String svBizDclsfCd = dvo.getSvBizDclsfCd();

        // 서비스작업출고내역 데이터 생성
        this.maaper.insertSvWkOstrIzsForBS(dvo);

        // 품목그룹코드, AS유형코드 조회
        WsnaSeedReleaseScheduleAsTpDvo asTpDvo = this.maaper.selectPdGrpAtcCdInfo(pdCd, svBizDclsfCd);
        // 현장수당항목코드
        String siteAwAtcCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getSiteAwAtcCd();

        // 고객서비스정기BS주기내역 업데이트
        this.maaper.updateCstSvRgbsprIz(cntrNo, cntrSn, vstDuedt);

        // 고객서비스BS배정내역 업데이트
        this.maaper.updateCstSvBfSvcAsnIz(cstSvAsnNo, svBizHclsfCd, siteAwAtcCd);

        // 로그 저장
        this.historyService.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);

        // 작업결과 조회
        Integer dupCnt = this.maaper.selectCstSvWkRsIzCount(cstSvAsnNo);
        if (dupCnt == null) {
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
        }

        // 씨앗재배기 + 씨앗의 경우 별도 처리
        WsnaSeedReleaseScheduleSproutDvo sproutDvo = this.maaper.selectSdingSproutInfo(dvo);
        if (ObjectUtils.isNotEmpty(sproutDvo)) {
            // 서비스 작업출고내역 저장
            this.maaper.insertSvWkOstrIzsForSprout(sproutDvo);

            cstSvAsnNo = sproutDvo.getCstSvAsnNo();
            cntrNo = sproutDvo.getCntrNo();
            cntrSn = sproutDvo.getCntrSn();
            vstDuedt = sproutDvo.getVstDuedt();

            // 상품코드
            pdCd = sproutDvo.getPdCd();
            // 업무세분류코드
            svBizDclsfCd = sproutDvo.getSvBizDclsfCd();

            // 고객서비스정기BS주기내역 업데이트
            this.maaper.updateCstSvRgbsprIz(cntrNo, cntrSn, vstDuedt);

            // 품목그룹코드, AS유형코드 조회
            asTpDvo = this.maaper.selectPdGrpAtcCdInfo(pdCd, svBizDclsfCd);
            // 현장수당항목코드
            siteAwAtcCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getSiteAwAtcCd();

            // 고객서비스BS배정내역 업데이트
            this.maaper.updateCstSvBfSvcAsnIz(cstSvAsnNo, svBizHclsfCd, siteAwAtcCd);

            // 로그 저장
            this.historyService.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);

            // 작업결과 조회
            dupCnt = this.maaper.selectCstSvWkRsIzCount(cstSvAsnNo);
            if (dupCnt == null) {
                WsnaSeedReleaseScheduleWkRsDvo wkRsDvo = this.converter
                    .mapWsnaSeedReleaseScheduleSproutDvoToWsnaSeedReleaseScheduleWkRsDvo(sproutDvo);

                // AS위치코드
                String asLctCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAcLctCd();
                // AS현상코드
                String asPhnCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAsPhnCd();
                // AS원인코드
                String asCausCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getAsCausCd();
                // 불량구분코드
                String badDvCd = ObjectUtils.isEmpty(asTpDvo) ? "" : asTpDvo.getBadDvCd();

                wkRsDvo.setSvBizHclsfCd(SV_BIZ_HCLSF_CD_BS);
                wkRsDvo.setAcLctCd(asLctCd);
                wkRsDvo.setAsPhnCd(asPhnCd);
                wkRsDvo.setAsCausCd(asCausCd);
                wkRsDvo.setBadDvCd(badDvCd);
                wkRsDvo.setRefriDvCd("1");
                wkRsDvo.setCstCralLocaraTno(dvo.getCstCralLocaraTno());
                wkRsDvo.setCstMexnoEncr(dvo.getCstMexnoEncr());
                wkRsDvo.setCstCralIdvTno(dvo.getCstCralIdvTno());

                // 작업결과저장
                this.maaper.insertCstSvWkRsIz(wkRsDvo);
            }
        }
    }

    /**
     * BS주기표 생성 파라미터
     * @param cntrNo
     * @param cntrSn
     * @param istDt
     * @return
     */
    private SearchProcessReq convertVisitPrdProcessReq(String cntrNo, String cntrSn, String istDt) {
        SearchProcessReq visitDto = new SearchProcessReq(
            cntrNo,
            cntrSn,
            "",
            "",
            DateUtil.getNowDayString(),
            istDt,
            "",
            ""
        );
        return visitDto;
    }

    /**
     * 모종 설치 완료 처리 (배치에서 호출)
     * @param map
     * @throws Exception
     */
    @Transactional
    public void saveSeedReleaseSchedulesForInstl(Map<String, String> map) throws Exception {

        // 현재일자
        String curDt = DateUtil.getNowDayString();

        // 계약번호
        String cntrNo = map.get("PARAM1");
        // 계약일련번호
        String cntrSn = map.get("PARAM2");
        // 패키지구분
        String pkgDvCd = map.get("PARAM3");
        // 서비스업무세분류코드
        String svBizDclsfCd = map.get("PARAM4");
        // 배송예정일자
        String sppDuedt = map.get("PARAM5");

        ValidAssert.hasText(cntrNo);
        ValidAssert.hasText(cntrSn);

        // 수행내역에 설치일자 저장
        this.maaper.updateCstSvExcnIzForInstl(cntrNo, Integer.parseInt(cntrSn), curDt);

        // 계약정보 update
        this.installationReqdDtInService
            .saveInstallReqdDt(cntrNo, cntrSn, curDt, "", sppDuedt);

        // BS주기표 생성
        SearchProcessReq visitDto = this.convertVisitPrdProcessReq(cntrNo, cntrSn, curDt);
        this.visitPrdService.processVisitPeriodRegen(visitDto);

        // 플로린이면서 제품배송일 경우 웰컴 BS 서비스 호출
        if (PKG_DV_CD_FLORIN.equals(pkgDvCd) && SV_BIZ_DCLSF_CD_PD_SPP.equals(svBizDclsfCd)) {
            this.createWelcomeBS(cntrNo, Integer.parseInt(cntrSn), curDt);
        }
    }

}
