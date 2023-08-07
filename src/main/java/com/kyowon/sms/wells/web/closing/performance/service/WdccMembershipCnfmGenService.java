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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WdccMembershipCnfmGenService {

    private final WdccMembershipCnfmGenMapper mapper;

    public PagingResult<SearchRes> getMembershipConfirmGenPages(SearchReq dto, PageInfo pageInfo) {
        return this.mapper.selectMembershipConfirmGens(dto, pageInfo);
    }

    public List<SearchRes> getMembershipCnfmGensForExcelDownload(SearchReq dto) {
        return this.mapper.selectMembershipConfirmGens(dto);
    }

    // TODO: 로직 설계부분 확인중
    /**
     * 확정 버튼 클릭
     * 1. 화면에서 넘겨받은 조회조건으로 입력 파라미터 값 셋팅
     * 2. 입력받은 파라미터값을 키로 TB_SSCT_CNTR_BAS(계약기본) 테이블의 CNTR_CNFM_DTM(계약확정일시) 컬럼을 sysdate 로 update 한다.
     */
    @Transactional
    public int editMembershipCnfmGen(SaveReq dto) {
        int processCount = 0;
        List<SearchRes> list = this.mapper.selectMembershipConfirmGens(dto.searchReq());
        for (SearchRes res : list) {
            MembershipCnfmGenDvo dvo = new MembershipCnfmGenDvo();
            dvo.setCntrNo(res.cntrNo());
            dvo.setConfirmDate(dto.confirmDate());
            dvo.setJoinDate(dto.joinDate());

            //            int result = this.mapper.updateMembershipCnfmGen(dvo);
            //            BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

            //            processCount += result;
            processCount += 1;
        }
        return processCount;
    }
}
