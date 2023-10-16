package com.kyowon.sms.wells.web.service.stock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaInstallationReqdDtInService;
import com.kyowon.sms.wells.web.service.stock.converter.WsnaMdProductOutOfStorageSaveConverter;
import com.kyowon.sms.wells.web.service.stock.dto.WsnaMdProductOutOfStorageMgtDto.SaveReq;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaMdProdcutOutOfStorageSaveDvo;
import com.kyowon.sms.wells.web.service.stock.mapper.WsnaMdProductOutOfStorageSaveMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class WsnaMdProductOutOfStorageSaveService {

    private final WsnaMdProductOutOfStorageSaveMapper mapper;

    private final WsnaMdProductOutOfStorageSaveConverter converter;

    private final MessageResourceService messageResourceService;

    private final WctaInstallationReqdDtInService installationReqdDtInService;

    @Transactional
    public int saveMdProductOutOfStorages(List<SaveReq> dtos) {
        int processCount = 0;

        List<WsnaMdProdcutOutOfStorageSaveDvo> dvos = converter.mapSaveReqToMdProductOutOfStorageSaveDvo(dtos);
        for (WsnaMdProdcutOutOfStorageSaveDvo dvo : dvos) {

            // 0.동일 키값으로 결과가 저장되었는지 체크한다.
            int existCnt = mapper.selectExistSvpdCstSvWkRsIz(dvo);
            BizAssert.isTrue(existCnt == 0, "MSG_ALT_EXIST_FSH_WK_LIST_RTRY_CONF");

            // 1.배정테이블 업데이트
            mapper.updateSvpdCstSvasIstAsnIz(dvo);
            //dvo.setSvProcsCn(messageResourceService.getMessage("MSG_ALT_INST_PCSV_OSTR_FSH"));
            dvo.setSvProcsCn("MD택배상품 출고완료");
            // 2.작업결과 IU
            mapper.insertSvpdCstSvWkRsIz(dvo);

            // 3.출고 확정시 일자(설치일자,배송예정일자) 현재날짜 지정 (판매시스템 연계)
            String sppDueDt = DateUtil.getNowDayString(); // 배송예정일자
            dvo.setIstDt(DateUtil.getNowDayString()); // 설치일자

            String cntrNo = dvo.getCntrNo();
            String cntrSn = dvo.getCntrSn();
            String istDt = dvo.getIstDt();

            int result = installationReqdDtInService.saveInstallReqdDt(cntrNo, cntrSn, istDt, "", sppDueDt);
            if (result > 0) {
                mapper.updateSvpdCstSvExcnIz(dvo);
            }

            processCount += 1;
        }

        return processCount;
    }

}
