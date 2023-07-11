package com.kyowon.sms.wells.web.contract.ordermgmt.service;

import com.kyowon.sflex.common.common.dto.SujiewonDto;
import com.kyowon.sflex.common.common.service.SujiewonService;
import com.kyowon.sms.wells.web.contract.common.service.WctzHistoryService;
import com.kyowon.sms.wells.web.contract.ordermgmt.converter.WctaReceiptBulkUploadConverter;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.CreateProspectCstReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.ValidateReq;
import com.kyowon.sms.wells.web.contract.ordermgmt.dto.WctaReceiptBulkUploadDto.ValidateRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPdBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslRcmdIzDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.mapper.WctaReceiptBulkUploadMapper;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class WctaReceiptBulkUploadService {

    private final WctaReceiptBulkUploadConverter converter;
    private final WctaReceiptBulkUploadMapper mapper;
    private final SujiewonService sujiewonService;
    private final WctzHistoryService historyService;

    public ValidateRes validateProspects(ValidateReq req) {
        ValidateRes.ValidateResBuilder builder = ValidateRes.builder();

        try {
            /* 상세 주소 기준으로 수지원넷에 요청하고 정제된 주소 정보를 조회 한다. 화면에서 조용히 실행하기 위해 biz exception 으로 감싸 rethrow 한다. */
            String searchWord = (req.adr1() + (" " + req.adr2())).trim();
            SujiewonDto.FormatRes formatRes = sujiewonService.getFormattedAddressesForRestApi(searchWord);

            if (formatRes.adrCd().startsWith("IF_ERR")) {
                throw new BizException("주소를 확인해 주시기 바랍니다.");
            }

            builder.adr(formatRes);
        } catch (Exception e) {
            throw new BizException(e.getMessage());
        }

        String pdCd = req.basePdCd();

        WctaPdBasDvo pdBasDvo = mapper.selectPdBasByPk(pdCd).orElseThrow(() -> new BizException("상품코드를 확인해 주시길 바랍니다."));
        builder.pdBas(pdBasDvo);

        if (StringUtil.isNotBlank(req.svPdCd())) {
            boolean svExist = mapper.isExistServiceProduct(req.svPdCd());
            BizAssert.isTrue(svExist, "서비스상품코드를 확인해 주시길 바랍니다.");
        }
        return builder.build();
    }

    /**
     * 가망고객기본 단건 조회
     */
    WctaPspcCstBasDvo getPspcCstBasByPk(String pspcCstId) {
        return mapper.selectPspcCstBasByPk(pspcCstId).orElseThrow(() -> new BizException("MSG_ALT_SVE_ERR"));
    }

    @Transactional(timeout = 600)
    public int createProspectCsts(List<CreateProspectCstReq> reqs) {
        reqs.forEach(this::createProspectCst);
        return 1;
    }

    @Transactional
    void createProspectCst(CreateProspectCstReq req) {
        WctaPspcCstBasDvo pspcCstBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstBasDvo(req);
        String pspcCstId = createPspcCst(pspcCstBasDvo);
        WctaPspcCstCnslBasDvo pspcCstCnslBasDvo = converter.mapCreateProspectCstReqToWctaPspcCstCnslBasDvo(req);
        pspcCstCnslBasDvo.setPspcCstId(pspcCstId);
        String pspcCstCnslId = createPspcCstCnsl(pspcCstCnslBasDvo);
        WctaPspcCstCnslRcmdIzDvo pspcCstCnslRcmdIzDvo = new WctaPspcCstCnslRcmdIzDvo(pspcCstCnslId, 1); /* 생성이니 일련번호는 1이 아닐까?*/
        pspcCstCnslRcmdIzDvo.setPdCd(req.basePdCd());
        createPspcCstCnslRcmdIz(pspcCstCnslRcmdIzDvo);
    }

    @Transactional
    String createPspcCst(WctaPspcCstBasDvo dvo) {
        String pspcCstId = mapper.selectPspcCstIdForNewPspcCstBas();
        dvo.setPspcCstId(pspcCstId);
        int result = mapper.insertPspcCstBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstChHistory(pspcCstId);
        return pspcCstId;
    }

    @Transactional
    String createPspcCstCnsl(WctaPspcCstCnslBasDvo dvo) {
        String pspcCstCnslId = mapper.selectPspcCstCnslIdForNewPspcCstCnslBas();
        dvo.setPspcCstCnslId(pspcCstCnslId);
        int result = mapper.insertPspcCstCnslBas(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslChHistory(pspcCstCnslId);
        return pspcCstCnslId;
    }

    @Transactional
    void createPspcCstCnslRcmdIz(WctaPspcCstCnslRcmdIzDvo dvo) {
        int result = mapper.insertPspcCstCnslRcmdIz(dvo);
        BizAssert.isTrue(result == 1, "저장실패");
        historyService.createPspcCstCnslRchHistory(dvo.getPspcCstCnslId(), dvo.getPspcCstCnslSn());
    }


}
