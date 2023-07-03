package com.kyowon.sms.wells.web.contract.changeorder.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.changeorder.converter.WctbContractChangeMngtConverter;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeReq;
import com.kyowon.sms.wells.web.contract.changeorder.dto.WctbContractChangeMngtDto.SearchContractChangeRes;
import com.kyowon.sms.wells.web.contract.changeorder.dvo.WctbContractChangeMngtDvo;
import com.kyowon.sms.wells.web.contract.changeorder.mapper.WctbContractChangeMngtMapper;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WctbContractChangeMngtService {
    private final WctbContractChangeMngtConverter converter;
    private final WctbContractChangeMngtMapper mapper;

    public PagingResult<SearchContractChangeRes> getContractChangePages(
        SearchContractChangeReq dto, PageInfo pageInfo
    ) {
        return mapper.selectContractChanges(dto, pageInfo);

    }

    public WctbContractChangeMngtDto.FindPartnerRes getPartnerByCntrNo(String cntrNo, String cntrSn) {
        // 계약변경관리-파트너 변경(조회)
        return mapper.selectPartnerByCntrNo(cntrNo, cntrSn);
    }

    @Transactional
    public int editPartnerInformations(WctbContractChangeMngtDto.EditPartnerReq dto) {
        // 계약변경관리-파트너 변경(저장)
        // 저장할 데이터 변환 DTO -> DVO
        WctbContractChangeMngtDvo inputDvo = converter.mapEditPartnerReqToWctbContractChangeMngtDvo(dto);

        // 데이터의 INSERT/UPDATE/유효시작일시/유효종료일시를 일관되게 맞추기 위해, 미리 조회해온다.
        WctbContractChangeMngtDvo dateTimeDvo = mapper.selectDateTime();
        inputDvo.setFstRgstDtm(dateTimeDvo.getFstRgstDtm());
        inputDvo.setFstRgstUsrId(dateTimeDvo.getFstRgstUsrId());
        inputDvo.setFstRgstPrgId(dateTimeDvo.getFstRgstPrgId());
        inputDvo.setFstRgstDeptId(dateTimeDvo.getFstRgstDeptId());
        inputDvo.setFnlMdfcDtm(dateTimeDvo.getFnlMdfcDtm());
        inputDvo.setFnlMdfcUsrId(dateTimeDvo.getFnlMdfcUsrId());
        inputDvo.setFnlMdfcPrgId(dateTimeDvo.getFnlMdfcPrgId());
        inputDvo.setFnlMdfcDeptId(dateTimeDvo.getFnlMdfcDeptId());

        int result;
        // 접수기본/상세, 계약기본에 승인, 변경 완료 상태로 처리한다.
        // 1. INSERT 계약변경접수기본
        result = mapper.insertSellPartnerToCntrChRcpBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 2. INSERT 계약변경접수변경이력
        result = mapper.insertSellPartnerToCntrChRcchHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 3. INSERT 계약변경접수상세
        result = mapper.insertSellPartnerToCntrChRcpDtl(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 4. INSERT 계약변경접수상세이력
        result = mapper.insertSellPartnerToCntrChRcpDchHist(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 5. UPDATE 계약기본
        result = mapper.updateSellPartnerToCntrBas(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-1. UPDATE 계약변경이력
        result = mapper.updateExSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 6-2. INSERT 계약변경이력
        result = mapper.insertSellPartnerToCntrChHist(inputDvo);
        BizAssert.isTrue(result == 1, "MSG_ALT_SVE_ERR");

        // 7-1. UPDATE 계약파트너관계 유효종료일시
        result = mapper.updateSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        // 7-2. INSERT 계약파트너관계
        result = mapper.insertSellPartnerToCntrPrtnrRel(inputDvo);
        BizAssert.isTrue(result > 0, "MSG_ALT_SVE_ERR");

        return 1;
    }
}
