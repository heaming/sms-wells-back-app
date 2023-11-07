package com.kyowon.sms.wells.web.closing.performance.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SaveReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchReq;
import com.kyowon.sms.wells.web.closing.performance.dto.WdccMembershipCnfmGenDto.SearchRes;
import com.kyowon.sms.wells.web.closing.performance.dvo.MembershipCnfmGenDvo;
import com.kyowon.sms.wells.web.closing.performance.mapper.WdccMembershipCnfmGenMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * <pre>
 * W-CL-U-0047M01	멤버십 확정 생성
 * </pre>
 *
 * @author gs.piit128 gilyong.han
 * @since 2023-08-02
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class WdccMembershipCnfmGenService {

    private final WdccMembershipCnfmGenMapper mapper;

    /**
     * 멤버십 확정 생성 페이징 조회
     * @param dto, pageInfo
     * @return PagingResult<SearchRes>
     */
    public PagingResult<SearchRes> getMembershipConfirmGenPages(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectMembershipConfirmGens(dto, pageInfo);
    }

    /**
     * 멤버십 확정 생성 엑셀 다운로드
     * @param dto
     * @return List<SearchRes>
     */
    public List<SearchRes> getMembershipCnfmGensForExcelDownload(SearchReq dto) {
        return this.mapper.selectMembershipConfirmGens(dto);
    }

    /**
     * 확정 버튼 클릭
     * 1. 화면에서 넘겨받은 조회조건으로 입력 파라미터 값 셋팅
     * 2. 입력받은 파라미터값(가입일자)을 TB_SSCT_CNTR_DTL(계약상세) 테이블의 CNTR_PD_STRTDT(계약상품시작일자) 컬럼을 update 한다.
     * 3. 입력받은 파라미터값(확정일자)을 TB_SSCT_CNTR_BAS(계약기본) 테이블의 CNTR_CNFM_DTM(계약확정일시) 컬럼을 update 한다.
     */
    @Transactional
    public int editMembershipCnfmGen(SaveReq dto) {
        int processCount = 0;
        List<SearchRes> list = this.mapper.selectMembershipConfirmGens(dto.searchReq());
        for (SearchRes res : list) {
            MembershipCnfmGenDvo dvo = new MembershipCnfmGenDvo();
            dvo.setCntrNo(res.cntrNo());
            dvo.setCntrSn(Integer.parseInt(res.cntrSn()));
            dvo.setConfirmDate(dto.confirmDate());
            dvo.setJoinDate(dto.joinDate());

            int resultJoinDate = this.mapper.updateMembershipCnfmGenJoinDate(dvo);
            BizAssert.isTrue(resultJoinDate == 1, "MSG_ALT_SVE_ERR");

            int resultConfirmDate = this.mapper.updateMembershipCnfmGenConfirmDate(dvo);
            BizAssert.isTrue(resultConfirmDate == 1, "MSG_ALT_SVE_ERR");

            processCount += 1;
        }
        return processCount;
    }
}
