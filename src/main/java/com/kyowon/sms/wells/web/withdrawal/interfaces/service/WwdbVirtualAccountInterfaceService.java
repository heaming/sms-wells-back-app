package com.kyowon.sms.wells.web.withdrawal.interfaces.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.withdrawal.idvrve.service.ZwdbVirtualAccountIsMgtService;
import com.kyowon.sms.wells.web.withdrawal.interfaces.dto.WwdbVirtualAccountInterfaceDto;
import com.kyowon.sms.wells.web.withdrawal.interfaces.mapper.WwdbVirtualAccountInterfaceMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbVirtualAccountInterfaceService {

    private final WwdbVirtualAccountInterfaceMapper mapper;
    private final ZwdbVirtualAccountIsMgtService vacService;

    public List<WwdbVirtualAccountInterfaceDto.SearchRes> getVirtualAccountIssues(
        WwdbVirtualAccountInterfaceDto.SearchReq dto
    ) {
        return mapper.selectVirtualAccountIssues(dto);
    }

    public WwdbVirtualAccountInterfaceDto.SaveRes createVirtualAccount(WwdbVirtualAccountInterfaceDto.SaveReq dto)
        throws ParseException {

        HashMap<String, String> paramVal = new HashMap<String, String>();

        paramVal.put("KW_GRP_CO_CD", "2000");
        paramVal.put("CNTR_NO", dto.cntrNo());
        paramVal.put("CNTR_SN", dto.cntrSn());
        paramVal.put("DP_AMT", dto.dpAmt());
        paramVal.put("FNIT_CD", dto.fnitCd());

        HashMap<String, String> returnVal = vacService.createVirtualAccountInterface(paramVal);
        return WwdbVirtualAccountInterfaceDto.SaveRes.builder().cntrNo(dto.cntrNo()).cntrSn(dto.cntrSn())
            .fnitCd(dto.fnitCd()).fnitNm(returnVal.get("FNIT_NM")).vacNo(returnVal.get("VAC_NO")).build();
    }
}
