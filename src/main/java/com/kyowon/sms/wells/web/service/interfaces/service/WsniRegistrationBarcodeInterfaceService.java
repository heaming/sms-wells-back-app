package com.kyowon.sms.wells.web.service.interfaces.service;

import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniRegistrationBarcodeInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniRegistrationBarcodeInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniRegistrationBarcodeInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniRegistrationBarcodeInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniRegistrationBarcodeInterfaceService {

    private final WsniRegistrationBarcodeInterfaceMapper mapper;

    private final WsniRegistrationBarcodeInterfaceConverter converter;

    private final MessageResourceService messageService;

    public WsniRegistrationBarcodeInterfaceDto.SearchJsonRes getRegistrationBarcodes(
        WsniRegistrationBarcodeInterfaceDto.SearchReq dto
    ) {
        log.info("[WsniRegistrationBarcodeInterfaceService.getRegistrationBarcodes] QRCD ::: " + dto.qrcd());
        WsniRegistrationBarcodeInterfaceDvo dvo = new WsniRegistrationBarcodeInterfaceDvo();

        try {
            WsniRegistrationBarcodeInterfaceDto.SearchRes res = mapper.selectRegistrationBarcode(dto);

            if (res == null || StringUtils.isEmpty(res.cntrNo())) {
                dvo.setCode("1");
                dvo.setMessage(messageService.getMessage("MSG_TXT_NOT_EXIST_QR")); //정보가 존재하지 않습니다
            } else {
                dvo.setCode("0");
                dvo.setMessage(messageService.getMessage("MSG_TXT_NOM")); //정상
                dvo.setBasePdCd(res.basePdCd());
                dvo.setUswyDvCd(res.uswyDvCd());
                dvo.setPdctPdCd(res.pdctPdCd());
                dvo.setFarmYn(res.farmYn());
                dvo.setItemNm(res.itemNm());
                dvo.setCntrNo(res.cntrNo());
                dvo.setCntrSn(res.cntrSn());
                dvo.setCustNm(res.custNm());
                dvo.setHnoNo(res.hnoNo());
//                dvo.setCsmrYr(res.csmrYr()); //빈값 전달 (삭제)
//                dvo.setCsmrCd(res.csmrCd()); //빈값 전달 (삭제)
                dvo.setAddr(res.addr());
                dvo.setZipno(res.zipno());
                dvo.setEmpId(res.empId());
                dvo.setEmpNm(res.empNm());
                dvo.setDeptNm(res.deptNm());
                dvo.setMngHpNo(res.mngHpNo());
                dvo.setVstDt(res.vstDt());
                dvo.setMngTyp(res.mngTyp());
                dvo.setMngCyc(res.mngCyc());
                dvo.setDbldNm(res.dbldNm());
                dvo.setFilterYn(res.filterYn());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(messageService.getMessage("MSG_TXT_QR_SEARCH_ERROR")); //QR코드 조회 오류
        }
        return converter.mapRegistrationBarcodeInterfaceDvoToJsonRes(dvo);
    }

}
