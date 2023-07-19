package com.kyowon.sms.wells.web.organization.hmnrsc.service;

import java.util.List;

import com.kyowon.sms.common.web.organization.organization.dto.ZogaBuildingDto;
import com.kyowon.sms.common.web.organization.organization.dvo.ZogaBuildingDvo;
import com.kyowon.sms.wells.web.organization.hmnrsc.converter.WogcPartnerPlannerConverter;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto;
import com.kyowon.sms.wells.web.organization.hmnrsc.dvo.WogcPartnerPlannerDvo;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.response.SaveResponse;
import com.sds.sflex.system.config.validation.BizAssert;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Service;

import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseReq;
import com.kyowon.sms.wells.web.organization.hmnrsc.dto.WogcPartnerPlannerDto.SearchLicenseRes;
import com.kyowon.sms.wells.web.organization.hmnrsc.mapper.WogcPartnerPlannerMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

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

}
