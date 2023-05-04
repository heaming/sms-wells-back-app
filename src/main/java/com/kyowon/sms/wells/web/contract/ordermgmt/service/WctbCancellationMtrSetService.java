package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctbCancellationMtrSetConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctbCancellationMtrSetDto.SearchRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctbCancellationMtrSetDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctbCancellationMtrSetMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WctbCancellationMtrSetService {
    private final WctbCancellationMtrSetMapper mapper;
    private final WctbCancellationMtrSetConverter converter;

    @Transactional
    public int cancellationMtrClSe(String businessType, String performanceYm) {
        int result = 0;
        String canDtm = "";
        if (StringUtils.isEmpty(performanceYm)) {
            return result;
        }
        if (businessType != "2" || businessType != "3" || businessType != "6" || businessType != "9") {
            return result;
        }
        List<SearchRes> dtos = mapper.selectContractBase(businessType, performanceYm);

        for (SearchRes dto : dtos) {

            WctbCancellationMtrSetDvo dvo = converter.mapSearchResToWctbCancellationMtrSetDvo(dto);
            if (dvo.getCntrChFshDtmNchk() == "0" && dvo.getCntrCanDtmNchk() == "1") {
                canDtm = dvo.getCntrChFshDtm();
                mapper.updateContractBas(canDtm, dvo.getCntrNo());
            }
            if (dvo.getCntrChFshDtmNchk() == "1" && dvo.getCntrCanDtmNchk() == "0") {
                canDtm = dvo.getCntrCanDtm();
                mapper.updateCntrChRcpBas(canDtm, dvo.getCntrChRcpId());
            }

            List<WctbCancellationMtrSetDto.SearchAcmpalCntrIzRes> acmpalCntrIzdtos = mapper
                .selectOjCntrNos(dvo.getCntrNo(), dvo.getCntrSn());
            for (WctbCancellationMtrSetDto.SearchAcmpalCntrIzRes acmpalCntrIzdto : acmpalCntrIzdtos) {
                mapper.updateContractBas(canDtm, acmpalCntrIzdto.ojCntrNo());
                mapper.updateAcmpalCntrIz(acmpalCntrIzdto.acmpalCntrId());
                mapper.updateAcmpalCntrChHist(acmpalCntrIzdto.acmpalCntrId());
                mapper.insertAcmpalCntrChHist(acmpalCntrIzdto.acmpalCntrId());
            }

            result += mapper.updateCntrChRcpBas(canDtm, dvo.getCntrChRcpId());
        }
        return result;
    }

}
