package com.kyowon.sms.wells.web.customer.contact.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kyowon.sms.common.web.customer.common.dto.ZcsaCustomerInfoDto.SearchCustomersRes;
import com.kyowon.sms.common.web.customer.common.dto.ZcsaCustomerInfoDto.SearchParameterTypeReq;
import com.kyowon.sms.common.web.customer.common.dvo.ZcsaCustomerInfoDvo;
import com.kyowon.sms.common.web.customer.common.service.ZcsaCustomerInfoService;
import com.kyowon.sms.wells.web.customer.contact.converter.WcsaCustomerInterfaceConverter;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerInfoReq;
import com.kyowon.sms.wells.web.customer.contact.dto.WcsaCustomerInterfaceDto.SearchCustomerRes;
import com.kyowon.sms.wells.web.customer.contact.dvo.WcsaInterfaceResultDvo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WcsaCustomerInterfaceService {
    private final ZcsaCustomerInfoService zcsaCustomerInfoService;
    private final WcsaCustomerInterfaceConverter converter;

    /**
    * 고객번호 기준으로 고객정보를 조회 - 고객번호에 해당하는 고객 기본/상세 정보 조회
    * @param dto
    * @return
    */
    public SearchCustomerRes getCustomerByCstNo(
        SearchCustomerInfoReq dto
    ) {
        // 응답코드
        WcsaInterfaceResultDvo ifResDvo = new WcsaInterfaceResultDvo();
        ifResDvo.setRsCd("S");//정상 처리되었습니다.
        ifResDvo.setRsMsg(null); //정상의 경우 결과코드(rsCd)만 송신한다.

        //  1. 필수값 체크(고객번호 → 필수값 체크)
        if (dto.cstNo().isEmpty()) {
            ifResDvo.setRsCd("F");
            Arrays.stream(dto.getClass().getDeclaredFields()).forEach(data -> {
                ifResDvo.setRsMsg(data.getName() + "가(이) 없습니다 !");
            });

            return converter.copy(ifResDvo);
        }

        //  2. 고객 조회 서비스 호출
        SearchParameterTypeReq param = converter.copy(dto);

        List<SearchCustomersRes> pextCustomer = zcsaCustomerInfoService.getCustomers(param);
        if (pextCustomer.isEmpty()) {
            ifResDvo.setRsCd("S");//계약자 정보가 없습니다!
            ifResDvo.setRsMsg(null);//정상의 경우 결과코드(rsCd)만 송신한다.
            return converter.copy(ifResDvo);
        }

        //  3. 개인고객 / 법인고객 확인 (법인격구분에 따라 다르게 요청)
        if ("1".equals(pextCustomer.get(0).copnDvCd())) {
            //    - 개인고객 호출
            ZcsaCustomerInfoDvo indvCustomerDtl = zcsaCustomerInfoService
                .getIndvCustomerInfo(pextCustomer.get(0).cstNo());
            return converter.mapCreateIndvCustomerToCustomerRes(indvCustomerDtl, ifResDvo);
        } else {
            //    - 법인고객 호출
            ZcsaCustomerInfoDvo crpCustomerDtl = zcsaCustomerInfoService
                .getCrpCustomerInfo(pextCustomer.get(0).cstNo());
            return converter.mapCreateCrpCustomerToCustomerRes(crpCustomerDtl, ifResDvo);
        }

    }

}
