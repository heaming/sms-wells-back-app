package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncBsPeriodCustomerTfConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncBsPeriodCustomerTfDto.*;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncBsPeriodCustomerTfCreateDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncBsPeriodCustomerTfMgtMapper;
import com.kyowon.sms.wells.web.service.common.service.WsnzHistoryService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncBsPeriodCustomerTfMgtService {

    private final WsncBsPeriodCustomerTfMgtMapper mapper;

    private final WsncBsPeriodCustomerTfConverter converter;

    private final WsnzHistoryService wsnzHistoryService;

    public PagingResult<SearchRes> getBsPeriodCustomers(
        SearchReq dto,
        PageInfo pageInfo
    ) {
        return mapper.selectBsPeriodCustomers(dto, pageInfo);
    }

    public List<SearchRes> getBsPeriodCustomersForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectBsPeriodCustomers(dto);
    }

    public List<BranchsAndServiceCentersRes> getBranchsAndServiceCenters(
        String ogTpCd, String ogId, String dgr1LevlOgId, String dgr2LevlOgId
    ) {
        return mapper.selectBranchsAndServiceCenters(ogTpCd, ogId, dgr1LevlOgId, dgr2LevlOgId);
    }

    public List<ManagersAndEngineersRes> getManagersAndEngineers(String ogId) {
        return mapper.selectManagersAndEngineers(ogId);
    }

    @Transactional
    public int createTransfer(List<CreateTfReq> dtos) throws Exception {
        int cnt = 0;
        for (CreateTfReq dto : dtos) {
//            WsncBsPeriodCustomerTfCreateDvo dvo = converter.mapCreateTfReqToBsPeriodCustomerTfCreateDvo(dto);
//
//            String baseYm = dto.baseYm();
//            String bfchIchrBrchOgId = dto.bfchIchrBrchOgId();
//            String afchIchrBrchOgId = dto.tfAkPrtnrOgId();
//
//            String asnTfDvCd = mapper.selectAsnTfDvCd(baseYm, bfchIchrBrchOgId, afchIchrBrchOgId);
//            BizAssert.notNull(asnTfDvCd, "MSG_ALT_SLCT_FAIL_ASN_TF_DV_CD"); // 배정이관구분코드를 조회할 수 없습니다.
//
//            dvo.setAsnTfDvCd(asnTfDvCd);
//            cnt += mapper.insertTransfer(dvo);
            cnt += createTransfer(dto);
        }
        return cnt;
    }
    @Transactional
    public int createTransfer(CreateTfReq dto) throws Exception {
        WsncBsPeriodCustomerTfCreateDvo dvo = converter.mapCreateTfReqToBsPeriodCustomerTfCreateDvo(dto);

        String baseYm = dto.baseYm();
        String bfchIchrBrchOgId = dto.bfchIchrBrchOgId();
        String afchIchrBrchOgId = dto.tfAkPrtnrOgId();

        String asnTfDvCd = mapper.selectAsnTfDvCd(baseYm, bfchIchrBrchOgId, afchIchrBrchOgId);
        BizAssert.notNull(asnTfDvCd, "MSG_ALT_SLCT_FAIL_ASN_TF_DV_CD"); // 배정이관구분코드를 조회할 수 없습니다.

        dvo.setAsnTfDvCd(asnTfDvCd);
        return mapper.insertTransfer(dvo);
    }

    @Transactional
    public int saveTransferConfirm(List<CreateTfReq> dtos) throws Exception {
        int cnt = 0;
        for (CreateTfReq dto : dtos) {
            WsncBsPeriodCustomerTfCreateDvo dvo = converter.mapCreateTfReqToBsPeriodCustomerTfCreateDvo(dto);

            String baseYm = dto.baseYm();
            String bfchIchrBrchOgId = dto.bfchIchrBrchOgId();
//            String afchIchrBrchOgId = dto.tfAkPrtnrOgId();
//            String afchIchrBrchOgId = dto.afchIchrBrchOgId();
            String afchIchrBrchOgId = "";

            if("00".equals(dto.tfStatCd())){
                afchIchrBrchOgId = dto.tfAkPrtnrOgId();
                dvo.setAfchIchrBrchOgId(dto.tfAkPrtnrOgId());
                dvo.setAfchMngrDvCd(dto.tfAkRsonCd());
                dvo.setAfchIchrPrtnrOgTpCd(dto.tfAkPrtnrOgTpCd());
                dvo.setAfchIchrPrtnrNo(dto.tfAkPrtnrNo());
            } else {
                afchIchrBrchOgId = dto.afchIchrBrchOgId();
            }

            String asnTfDvCd = mapper.selectAsnTfDvCd(baseYm, bfchIchrBrchOgId, afchIchrBrchOgId);
            BizAssert.notNull(asnTfDvCd, "MSG_ALT_SLCT_FAIL_ASN_TF_DV_CD"); // 배정이관구분코드를 조회할 수 없습니다.

//            if(("311".equals(asnTfDvCd) || "316".equals(asnTfDvCd)) && ("00".equals(dto.tfStatCd()))){
//                createTransfer(dto);
//            }
            if("00".equals(dto.tfStatCd())){
                if("311".equals(asnTfDvCd) || "316".equals(asnTfDvCd)){
                    createTransfer(dto);
                } else {
                    throw new BizException("MSG_ALT_NOT_INNER_TRANSFER"); //내부이관 건만 이관요청 없이 확정이 가능합니다.
                }
            }

            dvo.setAsnTfDvCd(asnTfDvCd);
            cnt += mapper.mergeTransferConfirm(dvo);

            // TB_SVPD_CST_SV_BFSVC_ASN_IZ - 확정담당자 정보 update
            dvo.setAfchIchrBrchOgId(afchIchrBrchOgId);
            mapper.updateCstSvBfsvcAsn(dvo);

            // save history
            String cstSvAsnNo = dto.cstSvAsnNo();
            wsnzHistoryService.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);
        }
        return cnt;
    }
}
