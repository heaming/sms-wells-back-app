package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.List;

import com.kyowon.sms.common.web.organization.common.dvo.ZogzPartnerDvo;
import com.kyowon.sms.common.web.organization.common.service.ZogzPartnerService;
import com.sds.sflex.common.utils.DateUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerPlannerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SaveQulificationReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseDetailRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerQualificationDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerPlannerMapper;
import com.kyowon.sms.wells.web.organization.zcommon.constants.OgConst.QlfAplcDvCd;
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
    private final WogcPartnerPlannerMapper mapper;
    private final WogcPartnerPlannerConverter converter;

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
     * 수석플래너 신청관리 삭제
     * @param dto
     * @return
     */
    @Transactional
    public int removeTopPlanner(WogcPartnerPlannerDto.DeleteReq dto) {
        WogcPartnerPlannerDvo planner = this.converter.mapDeleteReqToWogcPartnerPlannerDvo(dto);
        int processCount = mapper.deleteTopPlanner(planner);

        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");
        return processCount;
    }

    /**
     * 수석플래너 신청관리 자격생성
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveTopPlanner(WogcPartnerPlannerDto.SaveReq dto) throws Exception {
        int processCount = 0;
        WogcPartnerPlannerDvo planner = this.converter.mapSaveReqToWogcPartnerPlannerDvo(dto);

        /*자격생성 시 월파트너내역에 파트너정보 존재하는지 확인*/
        int cnt = this.mapper.selectCountMmPartner(planner);
        BizAssert.isTrue(cnt > 0, "MSG_ALT_SVE_ERR"); //저장에 실패했습니다

        /*자격생성 시 플래너자격변경내역(M조직)에 파트너정보 존재하는지 확인 (이미 웰스매니저라면 등록불가)*/
        int cnt2 = this.mapper.selectCountPlarPartner(planner);
        BizAssert.isTrue(cnt2 > 0, "MSG_ALT_SVE_ERR"); //저장에 실패했습니다

        /*자격생성 시 수석플래너신청내역(P조직)에 파트너정보 존재하는지 확인 (수석플래너신청내역 테이블에 있으면 삭제 후 등록)*/
        int cnt3 = this.mapper.selectCountTopPlarPartner(planner);
        BizAssert.isTrue(cnt3 > 0, "MSG_ALT_SVE_ERR"); //저장에 실패했습니다

        /*수석플래너신청내역 테이블에 있으면 삭제*/
        if (cnt3 > 0) {
            int cnt4 = this.mapper.deleteTopPlanner(planner);
            BizAssert.isTrue(cnt4 > 0, "MSG_ALT_SVE_ERR"); //저장에 실패했습니다
        }

        /*체크한 파트너에 대해 수석플래너신청내역 테이블에 등록*/
        processCount = this.mapper.insertTopPlanner(planner);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        return processCount;
    }

    /**
     * 수석플래너 신청관리 자격조정 팝업 조회
     * @param bldCd
     * @return
     */
    public WogcPartnerPlannerDto.FindRes getTopPlanner(String bldCd, String gridOgTpCd) {
        return mapper.selectTopPlannerByPk(bldCd, gridOgTpCd);
    }

    /**
     * 수석플래너 신청관리 자격조정
     * @param dto
     * @return
     * @throws Exception
     */
    @Transactional
    public int saveBuilding(WogcPartnerPlannerDto.EditReq dto) throws Exception {
        WogcPartnerPlannerDvo planner = this.converter.mapEditReqToWogcPartnerPlannerDvo(dto);

        int processCount = this.mapper.updateTopPlanner(planner);
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

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

        int processCount = 0;
        if (dto.qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_1.getCode())) {
            // 차월개시 일경우
            processCount = mapper.insertPlannerQualificationChange(qualificationDvo);

            // 당월개시 일경우
            if (DateUtil.getDays(DateUtil.getNowDayString(), qualificationDvo.getStrtdt()) == 0) {
                ZogzPartnerDvo partnerDvo = new ZogzPartnerDvo();
                partnerDvo.setOgTpCd(qualificationDvo.getOgTpCd());
                partnerDvo.setPrtnrNo(qualificationDvo.getPrtnrNo());
                partnerDvo.setQlfDvCd(qualificationDvo.getQlfDvCd());

                // 월파트너 갱신
                ogzPartnerService.updateQlfDvCdOfMonthPartner(partnerDvo);
                // 파트너상세 갱신
                ogzPartnerService.updateQlfDvCdOfPartnerDetail(partnerDvo);
            }
        } else if (dto.qlfAplcDvCd().equals(QlfAplcDvCd.QLF_APLC_DV_CD_3.getCode())) {
            processCount = mapper.updatePlannerQualificationChange(qualificationDvo);
        }
        BizAssert.isTrue(processCount == 1, "MSG_ALT_SVE_ERR");

        return processCount;
    }
}
