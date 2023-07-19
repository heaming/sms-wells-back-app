package com.kyowon.sms.wells.web.service.interfaces.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.thymeleaf.util.StringUtils;

import com.kyowon.sms.wells.web.service.interfaces.converter.WsniBarcodeProductInterfaceConverter;
import com.kyowon.sms.wells.web.service.interfaces.dto.WsniBarcodeProductInterfaceDto;
import com.kyowon.sms.wells.web.service.interfaces.dvo.WsniBarcodeProductInterfaceDvo;
import com.kyowon.sms.wells.web.service.interfaces.mapper.WsniBarcodeProductInterfaceMapper;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.exception.BizException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WsniBarcodeProductInterfaceService {

    private final WsniBarcodeProductInterfaceMapper mapper;

    private final WsniBarcodeProductInterfaceConverter converter;

    private final MessageResourceService messageService;

    public WsniBarcodeProductInterfaceDto.SearchJsonRes getBarcodeProducts(
        WsniBarcodeProductInterfaceDto.SearchReq dto
    ) {
        log.info("[WsniBarcodeProductInterfaceService.getBarcodeProducts] QRCD ::: " + dto.qrcd());
        WsniBarcodeProductInterfaceDvo dvo = new WsniBarcodeProductInterfaceDvo();

        try {
            WsniBarcodeProductInterfaceDto.SearchRes res = mapper.selectBarcodeProduct(dto);

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
        return converter.mapBarcodeProductInterfaceDvoToJsonRes(dvo);
    }

    public List<WsniBarcodeProductInterfaceDto.SearchCustJsonRes> getBarcodeSearchCustomers(
        WsniBarcodeProductInterfaceDto.SearchCustReq dto
    ) {
        log.info("[WsniBarcodeProductInterfaceService.getBarcodeSearchCustomers] BARCODE ::: " + dto.barcode());
        List<WsniBarcodeProductInterfaceDto.SearchCustRes> custList;
        List<WsniBarcodeProductInterfaceDto.SearchCustJsonRes> resList = new ArrayList<WsniBarcodeProductInterfaceDto.SearchCustJsonRes>();

        try {
            custList = mapper.selectBarcodeSearchCustomer(dto);

            if(CollectionUtils.isEmpty(custList)){
                throw new BizException(messageService.getMessage("MSG_TXT_RENTAL_NOT_EXIST")); //렌탈 정보가 존재하지 않습니다
            }

            for(WsniBarcodeProductInterfaceDto.SearchCustRes custVo : custList){
                resList.add(converter.mapBarcodeProductCustDtoToJsonRes(custVo));
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(messageService.getMessage("MSG_TXT_BARCODE_SEARCH_ERROR")); //바코드 조회 오류
        }

        return resList;
    }

}
