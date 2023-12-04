package com.kyowon.sms.wells.web.service.allocate.service;

import java.util.List;

import com.sds.sflex.system.config.annotation.LongTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.service.allocate.converter.WsncManagementCstRglvlConverter;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.OrganizationRes;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SavePartnerReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchReq;
import com.kyowon.sms.wells.web.service.allocate.dto.WsncManagementCstRglvlDto.SearchRes;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlBsAssignInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncManagementCstRglvlExchangeInfoDvo;
import com.kyowon.sms.wells.web.service.allocate.dvo.WsncSvpdAsnRsTfIzDvo;
import com.kyowon.sms.wells.web.service.allocate.mapper.WsncManagementCstRglvlMapper;
import com.kyowon.sms.wells.web.service.common.service.WsnzHistoryService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WsncManagementCstRglvlService {
    private final WsncManagementCstRglvlMapper mapper;
    private final WsncManagementCstRglvlConverter converter;
    private final WsnzHistoryService wsnzHistoryService;

    public List<SearchRes> getManagementCustomerRglvls(
        SearchReq dto
    ) {
        return mapper.selectManagementCustomerRglvls(dto);
    }

    public List<SearchRes> getManagementCustomerRglvlsForExcelDownload(
        SearchReq dto
    ) {
        return mapper.selectManagementCustomerRglvls(dto);
    }

    @Transactional
    public int savePartnerInfoAndMngerRglvlDvCd(List<SavePartnerReq> dtos) {
        int processCount = 0;

        WsncManagementCstRglvlExchangeInfoDvo exchangeInfoDvo;
        WsncManagementCstRglvlBsAssignInfoDvo bsAssignInfoDvo;

        for (SavePartnerReq dto : dtos) {
            exchangeInfoDvo = converter.mapSavePartnerReqToWsncManagementCstRglvlExchangeInfoDvo(dto);
            bsAssignInfoDvo = converter.mapSavePartnerReqToWsncManagementCstRglvlBsAssignInfoDvo(dto);

            if (exchangeInfoDvo.getMngtPrtnrNo() != null
                && exchangeInfoDvo.getMngStdMngerRglvlDvCd() != null) {
                processCount += mapper.updateClientServiceExchangeInfo(exchangeInfoDvo);
            }

            if (bsAssignInfoDvo.getCnfmPsicPrtnrNo() != null
                && bsAssignInfoDvo.getCurMnthAlctnMngerRglvlDvCd() != null
                && bsAssignInfoDvo.getCstSvAsnNo() != null) {
                processCount += mapper.updateClientServiceBsAssignInfo(bsAssignInfoDvo);

                WsncSvpdAsnRsTfIzDvo wsncSvpdAsnRsTfIzDvo = converter.mapSavePartnerReqToWsncSvpdAsnRsTfIzDvo(dto);
                mapper.insertTransfer(wsncSvpdAsnRsTfIzDvo);

                // save history
                String cstSvAsnNo = dto.cstSvAsnNo();
                wsnzHistoryService.insertCstSvBfsvcAsnHistByPk(cstSvAsnNo);
            }
        }
        return processCount;
    }

    public OrganizationRes getOrganizationInfo(String ogId) {
        return mapper.selectOrganizationInfo(ogId);
    }

}
