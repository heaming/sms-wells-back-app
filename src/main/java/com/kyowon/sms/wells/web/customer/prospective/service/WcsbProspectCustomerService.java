package com.kyowon.sms.wells.web.customer.prospective.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kyowon.sflex.common.common.dvo.FormatAddressDvo;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sflex.common.zcommon.constants.CmSujiewonConst;
import com.kyowon.sms.wells.web.customer.prospective.converter.WcsbProspectCustomerConverter;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryReq;
import com.kyowon.sms.wells.web.customer.prospective.dto.WcsbCustomerInterfaceDto.CreateNewReceiptInquiryRes;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbInterfaceResultDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbNewReceiptInquiryDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.dvo.WcsbProspectCustomerCnslBasDvo;
import com.kyowon.sms.wells.web.customer.prospective.mapper.WcsbProspectCustomerMapper;
import com.sds.sflex.common.utils.StringUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WcsbProspectCustomerService {
    private final WcsbProspectCustomerConverter converter;
    private final SujiewonService sujiewonService;
    private final WcsbProspectCustomerMapper mapper;

    @Transactional
    public CreateNewReceiptInquiryRes createNewReceiptInquiry(CreateNewReceiptInquiryReq dto) throws Exception {
        WcsbInterfaceResultDvo ifResDvo = new WcsbInterfaceResultDvo();
        //- resultMap.put("CHEK", "Y")
        //- resultMap.put("RS_MSG", "자료가 등록되었습니다.")
        ifResDvo.setCheck("Y");
        ifResDvo.setRsMsg("자료가 등록되었습니다.");

        WcsbNewReceiptInquiryDvo paramDvo = converter.mapCreateNewReceiptInquiryReqToNewReceiptInquiryDvo(dto);
        //현재일자
        Calendar calendar = Calendar.getInstance(Locale.KOREA);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowDate = dateFormat.format(calendar.getTime());
        //1, Input 값 치환
        //  - PSPC_CST_INFLW_DT = "0" or PSPC_CST_INFLW_DT = ""  → java sysdate
        if ("0".equals(dto.pspcCstInflwDt()) || "".equals(dto.pspcCstInflwDt())) {
            paramDvo.setPspcCstInflwDt(nowDate);
        }
        //  - APLC_SN = "0" or APLC_SN = "" → 1
        if ("0".equals(dto.aplcSn()) || "".equals(dto.aplcSn())) {
            paramDvo.setAplcSn("1"); //가망고객상담기본 테이블 set
        }
        //2. 공통 수지넷 주소정보조회API 호출 후 주소ID return (https://kyowon.atlassian.net/wiki/spaces/ForKUS/pages/83624010/- 참조)
        //  - inputAddress= ZIP1 || ZIP2 || RNADR || RDADR || RDADR2 TODO: TOBE에서는 우편번호는 넣지 않긴한데..
        //  - addressType = "B"
        // 주소정보 수신했을 경우만 수지원넷 호출
        if (StringUtil.isNotEmpty(paramDvo.getRnadr())) {
            String formattedAddress = "";
            if (StringUtil.isNotEmpty(paramDvo.getRnadr())) {
                formattedAddress = formattedAddress + paramDvo.getRnadr();
            }
            if (StringUtil.isNotEmpty(paramDvo.getRnadr()) && StringUtil.isNotEmpty(paramDvo.getRdadr())) {
                formattedAddress = formattedAddress + " " + paramDvo.getRdadr();
            }
            if (StringUtil.isNotEmpty(paramDvo.getRnadr()) && StringUtil.isNotEmpty(paramDvo.getRdadr())
                && StringUtil.isNotEmpty(paramDvo.getRdadr2())) {
                formattedAddress = formattedAddress + " " + paramDvo.getRdadr2();
            }

            String adrZip = "";
            if (StringUtils.isNotEmpty(paramDvo.getZip1())) {
                adrZip = adrZip + paramDvo.getZip1().trim();
            }
            if (StringUtils.isNotEmpty(paramDvo.getZip1()) && StringUtils.isNotEmpty(paramDvo.getZip2())) {
                adrZip = adrZip + paramDvo.getZip2().trim();
            }

            FormatAddressDvo addrDvo = sujiewonService.getFormattedAddressForIFWithAdrZip(
                formattedAddress, CmSujiewonConst.FORMAT_TYPE_ROAD_ADDRESS, adrZip
            );

            String adrId = addrDvo.getAdrId(); //가망고객상담기본 테이블 set
            paramDvo.setAdrId(adrId);
        }
        //3. 가망고객기본, 가망고객변경이력 적재 - 참조.쿼리 Sheet
        //   - 가망고객ID 채번
        //   - INSERT INTO TB_SSOP_PSPC_CST_BAS
        //   - INSERT INTO TB_SSOP_PSPC_CST_CH_HIST
        WcsbProspectCustomerBasDvo pspcCstBasParam = converter.mapCreatePspcCstBas(paramDvo);
        //가망고객 기본 insert
        mapper.insertPspcCstBas(pspcCstBasParam);
        String pspcCstId = pspcCstBasParam.getPspcCstId();//가망고객ID(등록된ID)
        mapper.insertPspcCstChHist(pspcCstId);
        //4. 가망고객상담기본, 가망고객상담변경이력 적재 - 참조.쿼리 Sheet
        //   - 가망고객상담ID 채번
        //   - INSERT INTO TB_SSOP_PSPC_CST_CNSL_BAS
        //   - INSERT INTO TB_SSOP_PSPC_CST_CNSL_CH_HIST
        WcsbProspectCustomerCnslBasDvo cnslBasParam = converter.mapCreatePspcCstCnslBas(paramDvo);
        cnslBasParam.setPspcCstId(pspcCstId);
        mapper.insertPspcCstCnslBas(cnslBasParam);
        mapper.insertPspcCstCnslChHist(cnslBasParam.getPspcCstCnslId(), pspcCstId);
        return converter.mapCreateNewReceiptInquiryRes(ifResDvo);
    }

}
