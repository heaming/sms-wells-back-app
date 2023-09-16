package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.common.web.organization.common.dvo.ZogzPartnerDvo;
import com.kyowon.sms.common.web.organization.common.service.ZogzPartnerService;
import com.kyowon.sms.common.web.organization.hmnrsc.dto.ZogcTransferPartnerDto.SaveAppointReq;
import com.kyowon.sms.common.web.organization.hmnrsc.service.ZogcTransferService;
import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerPlannerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SaveQulificationReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseDetailRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerQualificationDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerPlannerMapper;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst.QlfAplcDvCd;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseCloseCheckDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaWarehouseCloseCheckService;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.constant.CommConst;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;

/**
 *
 * <pre>
 *
 * </pre>
 *
 * @author 김동석
 * @since 2023-05-24
 */
@Service
@RequiredArgsConstructor
public class WogcPartnerPlannerService {

    private final ZogzPartnerService ogzPartnerService;
    private final ZogcTransferService ogcTransferService;
    private final WsnaWarehouseCloseCheckService snaWarehouseCloseCheckService;
    private final WogcPartnerPlannerMapper mapper;
    private final WogcPartnerPlannerConverter converter;

    private final static String MSG_ALT_SVE_ERR = "MSG_ALT_SVE_ERR";

    public List<SearchLicenseRes> getPlannerLicenses(SearchLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }

    /**
     * 수석플래너 신청관리 조회
     * @param
     * @return
     */
    public PagingResult<WogcPartnerPlannerDto.SearchRes> getTopPlannerPages(
        WogcPartnerPlannerDto.SearchReq dto, PageInfo pageinfo
    ) {
        return mapper.selectTopPlannerPages(dto, pageinfo);
    }

    /**
     * 수석플래너 신청관리 엑셀 다운로드
     * @param
     * @return
     */
    public List<WogcPartnerPlannerDto.SearchRes> getTopPlannerForExcelDownload(WogcPartnerPlannerDto.SearchReq dto) {
        return mapper.selectTopPlannerPages(dto);
    }

    /**
     * 순주문 체크
     * @param dto
     * @return
     */
    public int getOrderChecks(WogcPartnerPlannerDto.SearchCheckReq dto) {
        int gradeCnt = 0;

        gradeCnt = mapper.selectFeamOrderCnt(dto); // D-1월 순주문마감된 경우 진행 (CNT > 0 이면 순주문마감된 경우)

        return gradeCnt;

    }

    /**
     * 자격생성 체크
     * @param dto
     * @return
     */
    public int getCreatedChecks(WogcPartnerPlannerDto.SearchCheckReq dto) {
        int gradeCnt = 0;

        gradeCnt = mapper.selectQuaCreateCnt(dto); // D월 신청내역이 생성되지 않은 경우에 진행 (CNT = 0 이면 생성이 진행되지 않은 경우)

        return gradeCnt;

    }

    /**
     * 수석플래너 신청관리 자격생성
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int createTopPlanner(WogcPartnerPlannerDto.SaveReq dto) throws Exception {
        int processCount = 0;
        WogcPartnerPlannerDvo planner = this.converter.mapSaveReqToWogcPartnerPlannerDvo(dto);

        //  1/4. 수석플래너신청내역(TB_OGPS_TOPMR_PLAR_APLC_IZ) 생성 (전월 -> 당월)
        processCount = this.mapper.insertTopPlanner(planner);

        //  2/4. 수석플래너신청내역 - 전월 실적마감 후 당월재직 기준 P조직(W01) 자격 갱신
        processCount = this.mapper.updateTopPlanner(planner);

        //  3/4. 월파트너내역(TB_OGBS_MM_PRTNR_IZ) - 전월 실적마감 후 당월재직 기준 P조직(W01) 자격 갱신
        //        processCount = this.mapper.updateMmPartner(planner);
        //        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        //  4/4. 파트너상세(TB_OGBS_PRTNR_DTL) - 전월 실적마감 후 당월재직 기준 P조직(W01) 자격 갱신
        //        processCount = this.mapper.updateDtlPartner(planner);
        //        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        return processCount;
    }

    /**
     * 수석플래너 신청관리 자격조정 팝업 조회
     * @param ogTpCd
     * @param prtnrNo
     * @param mngtYm
     * @return
     */
    public WogcPartnerPlannerDto.FindRes getTopPlanner(String ogTpCd, String prtnrNo, String mngtYm) {
        return mapper.selectTopPlannerByPk(ogTpCd, prtnrNo, mngtYm);
    }

    /**
     * 수석플래너 신청관리 자격조정
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveTopPlanner(WogcPartnerPlannerDto.EditReq dto) throws Exception {
        WogcPartnerPlannerDvo planner = this.converter.mapEditReqToWogcPartnerPlannerDvo(dto);

        int processCount = this.mapper.insertAdTopPlanner(planner); // 1. 수석플래너신청내역 테이블 INSERT
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        //        processCount = this.mapper.updateAdMmPartner(planner); // 2. 월파트너내역 파트너등급 UPDATE
        //        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        //        processCount = this.mapper.updateAdDtlPartner(planner); // 3. 파트너상세의 파트너등급 UPDATE
        //        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        return processCount;
    }

    public PagingResult<SearchLicenseRes> getPlannerLicensePages(SearchLicenseReq dto, PageInfo pageinfo) {
        return mapper.selectPlannerLicensePages(dto, pageinfo);
    }

    public List<SearchLicenseRes> getPlannerLicenseForExcelDownload(SearchLicenseReq dto) {
        return mapper.selectPlannerLicensePages(dto);
    }

    public PagingResult<SearchLicenseDetailRes> getPlannerLicenseDetailPages(String prtnrNo, PageInfo pageinfo) {
        return mapper.selectPlannerLicenseDetailPages(prtnrNo, pageinfo);
    }

    /**
     * 매니저 자격관리 보류, 개시 저장
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int createPlannerQualificationChange(SaveQulificationReq dto) throws Exception {
        WogcPartnerPlannerQualificationDvo qualificationDvo = converter
            .mapSaveQulificationReqToPartnerPlannerQualificationDvo(dto);

        // 상세 조회
        ZogzPartnerDvo partnerDvo = new ZogzPartnerDvo();
        partnerDvo.setOgTpCd(qualificationDvo.getOgTpCd());
        partnerDvo.setPrtnrNo(qualificationDvo.getPrtnrNo());
        partnerDvo.setQlfDvCd(qualificationDvo.getQlfDvCd());
        List<SearchLicenseDetailRes> detailList = mapper
            .selectPlannerLicenseDetailPages(qualificationDvo.getPrtnrNo());

        int processCount = 0;
        if (dto.qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_1.getCode())) {
            // 당월개시 일경우
            if (DateUtil.getDays(DateUtil.getNowDayString(), qualificationDvo.getStrtdt()) == 0) {
                WogcPartnerPlannerQualificationDvo beforeQualificationDvo = new WogcPartnerPlannerQualificationDvo();

                if (detailList.get(0).qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_3.getCode())) {
                    // 보류 -> 승급
                    String newStrtdt = DateUtil.getNowDayString();

                    qualificationDvo.setStrtdt(detailList.get(0).strtdt());
                    qualificationDvo.setQlfDvCd(detailList.get(0).qlfDvCd());
                    qualificationDvo.setNewStrtdt(newStrtdt);
                    mapper.updatePlannerQualificationChange(qualificationDvo);

                    beforeQualificationDvo.setEnddt(DateUtil.addDays(newStrtdt, -1));
                } else {
                    // 승급
                    mapper.insertPlannerQualificationChange(qualificationDvo);

                    beforeQualificationDvo.setEnddt(DateUtil.addDays(detailList.get(0).strtdt(), -1));
                }

                // 이전 데이터 자격해제 처리
                beforeQualificationDvo.setOgTpCd(detailList.get(1).ogTpCd());
                beforeQualificationDvo.setPrtnrNo(detailList.get(1).prtnrNo());
                beforeQualificationDvo.setQlfDvCd(detailList.get(1).qlfDvCd());
                beforeQualificationDvo.setStrtdt(detailList.get(1).strtdt());
                beforeQualificationDvo.setQlfAplcDvCd(OgConst.QlfAplcDvCd.QLF_APLC_DV_CD_2.getCode());
                processCount = mapper.updatePlannerQualificationChange(beforeQualificationDvo);

                // 월파트너 갱신
                ogzPartnerService.updateQlfDvCdOfMonthPartner(partnerDvo);
                // 파트너상세 갱신
                ogzPartnerService.updateQlfDvCdOfPartnerDetail(partnerDvo);
            } else {
                // 차월개시 일경우

                WogcPartnerPlannerQualificationDvo beforeQualificationDvo = new WogcPartnerPlannerQualificationDvo();

                if (detailList.get(0).qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_3.getCode())) {
                    // 보류 -> 승급
                    String now = DateUtil.addMonths(DateUtil.getNowDayString(), 1);
                    String newStrtdt = now.substring(0, 6) + "01";

                    qualificationDvo.setStrtdt(detailList.get(0).strtdt());
                    qualificationDvo.setQlfDvCd(detailList.get(0).qlfDvCd());
                    qualificationDvo.setNewStrtdt(newStrtdt);
                    mapper.updatePlannerQualificationChange(qualificationDvo);

                    beforeQualificationDvo.setEnddt(DateUtil.addDays(newStrtdt, -1));
                } else {
                    // 승급
                    mapper.insertPlannerQualificationChange(qualificationDvo);

                    beforeQualificationDvo.setEnddt(DateUtil.addDays(detailList.get(0).strtdt(), -1));
                }

                // 이전 데이터 종료일자 변경
                beforeQualificationDvo.setOgTpCd(detailList.get(1).ogTpCd());
                beforeQualificationDvo.setPrtnrNo(detailList.get(1).prtnrNo());
                beforeQualificationDvo.setQlfDvCd(detailList.get(1).qlfDvCd());
                beforeQualificationDvo.setStrtdt(detailList.get(1).strtdt());
                processCount += mapper.updatePlannerQualificationChange(beforeQualificationDvo);
            }
        } else if (dto.qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_3.getCode())) {
            // 보류
            mapper.updatePlannerQualificationChange(qualificationDvo);

            // 이전 데이터 승급 처리
            WogcPartnerPlannerQualificationDvo beforeQualificationDvo = new WogcPartnerPlannerQualificationDvo();
            beforeQualificationDvo.setOgTpCd(detailList.get(1).ogTpCd());
            beforeQualificationDvo.setPrtnrNo(detailList.get(1).prtnrNo());
            beforeQualificationDvo.setQlfDvCd(detailList.get(1).qlfDvCd());
            beforeQualificationDvo.setStrtdt(detailList.get(1).strtdt());
            beforeQualificationDvo.setEnddt("99991231");
            beforeQualificationDvo.setQlfAplcDvCd(OgConst.QlfAplcDvCd.QLF_APLC_DV_CD_1.getCode());
            processCount = mapper.updatePlannerQualificationChange(beforeQualificationDvo);
        }
        BizAssert.isTrue(processCount == 1, MSG_ALT_SVE_ERR);

        return processCount;
    }

    /**
     * 매니저 자격관리 해약
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int updatePlannerQualificationCancel(SaveQulificationReq dto) throws Exception {
        WogcPartnerPlannerQualificationDvo qualificationDvo = converter
            .mapSaveQulificationReqToPartnerPlannerQualificationDvo(dto);
        int processCount = 0;

        WsnaWarehouseCloseCheckDvo warehouseCloseCheckDvo = new WsnaWarehouseCloseCheckDvo();
        warehouseCloseCheckDvo.setOgTpCd(qualificationDvo.getOgTpCd());
        warehouseCloseCheckDvo.setPrtnrNo(qualificationDvo.getPrtnrNo());
        List<String> checks = snaWarehouseCloseCheckService.getWarehouseCloseCheck(warehouseCloseCheckDvo);

        if (CollectionUtils.isNotEmpty(checks)) {
            String result = checks.get(0);

            if ("00".equals(result)) {
                // 재고가 없을 때
                processCount = mapper.updatePlannerQualificationChange(qualificationDvo);
                BizAssert.isTrue(processCount == 1, MSG_ALT_SVE_ERR);

                List<SaveAppointReq> transferDtos = new ArrayList<SaveAppointReq>();
                SaveAppointReq transferDto = SaveAppointReq.builder()
                    .ogTpCd(qualificationDvo.getOgTpCd())
                    .prtnrNo(qualificationDvo.getPrtnrNo())
                    .gaoorDvCd(
                        com.kyowon.sms.common.web.organization.zcommon.constants.OgConst.GaoorTpCd.GAOOR_TP_CD_10
                            .getCode()
                    )
                    .chdt(DateUtil.getLastDateOfMonth(DateUtil.getNowDayString()))
                    .aplcStatCd(
                        com.kyowon.sms.common.web.organization.zcommon.constants.OgConst.AplcStatCd.APLC_STAT_CD_20
                            .getCode()
                    )
                    .rowState(CommConst.ROW_STATE_CREATED)
                    .build();
                transferDtos.add(transferDto);
                processCount = ogcTransferService.saveAppointPartner(transferDtos);
            } else {
                if (checks.contains("01")) {
                    // 1.2 품목입고내역, 서비스품목재고내역 이동재고수량 체크
                    BizAssert.isTrue(processCount == 1, "MSG_ALT_MMT_STOC_EXST_PROCS_IMPSB");
                } else if (checks.contains("02")) {
                    // 1.3 월별품목재고내역 시점재고수량 체크
                    BizAssert.isTrue(processCount == 1, "MSG_ALT_PITM_STOC_MINUS_EXST_PROCS_IMPSB");
                }
            }
        }

        return processCount;
    }
}
