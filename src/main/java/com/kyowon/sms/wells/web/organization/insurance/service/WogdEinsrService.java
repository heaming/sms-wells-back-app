package com.kyowon.sms.wells.web.organization.insurance.service;

import com.kyowon.sms.common.web.organization.common.dvo.ZogzAppendingFileRelationDvo;
import com.kyowon.sms.common.web.organization.common.service.ZogzAppendingFileRelationService;
import com.kyowon.sms.common.web.organization.hmnrsc.dvo.ZogcApointSaveDvo;
import com.kyowon.sms.common.web.organization.hmnrsc.mapper.ZogcTransferMapper;
import com.kyowon.sms.common.web.organization.insurance.converter.ZogdEinsrConverter;
import com.kyowon.sms.common.web.organization.insurance.dto.ZogdEinsrDto.*;
import com.kyowon.sms.common.web.organization.zcommon.constants.OgConst;
import com.kyowon.sms.wells.web.service.stock.dvo.WsnaWarehouseCloseCheckDvo;
import com.kyowon.sms.wells.web.service.stock.service.WsnaWarehouseCloseCheckService;
import com.sds.sflex.common.docs.service.AttachFileService;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.exception.BizException;
import com.sds.sflex.system.config.validation.BizAssert;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * <pre>
 * 고용보험 Service
 * </pre>
 *
 * @author
 * @since 2023-09-20
 */
@Service
@RequiredArgsConstructor
public class WogdEinsrService {

    private final ZogdEinsrConverter zogdEinsrConverter;
    private final ZogcTransferMapper zogcTransferMapper;
    private final ZogzAppendingFileRelationService appendingFileRelationService;
    private final AttachFileService attachFileService;
    private final WsnaWarehouseCloseCheckService snaWarehouseCloseCheckService;

    public int createStoppageApplcation(SaveStoppageApplcationReq dto) throws Exception {
        int processCount = 0;
        ZogcApointSaveDvo dvo = zogdEinsrConverter.mapSaveStoppageApplcationReqToZogcApointSaveDvo(dto);
        dvo.setDtaDlYn("N");
        UserSessionDvo session = SFLEXContextHolder.getContext().getUserSession();
        dvo.setPcpOgTpCd(session.getOgTpCd());
        dvo.setPcpPrtnrNo(session.getEmployeeIDNumber());

        WsnaWarehouseCloseCheckDvo warehouseCloseCheckDvo = new WsnaWarehouseCloseCheckDvo();
        warehouseCloseCheckDvo.setOgTpCd(dvo.getOgTpCd());
        warehouseCloseCheckDvo.setPrtnrNo(dvo.getPrtnrNo());
        List<String> checks = snaWarehouseCloseCheckService.getWarehouseCloseCheck(warehouseCloseCheckDvo);

        if (CollectionUtils.isNotEmpty(checks)) {
            String result = checks.get(0);

            if ("00".equals(result)) {
                // 재고가 없을 때
                if (StringUtils.isNotEmpty(dvo.getAplcSn())) { //수정
                    processCount = zogcTransferMapper.updateAppointPartner(dvo);
                } else { //인서트
                    String aplcSn = zogcTransferMapper.selectNewAplcSn();
                    dvo.setAplcSn(aplcSn);
                    processCount = zogcTransferMapper.insertAppointPartner(dvo);
                }

                // 휴업/복귀 - (휴업신청서) 첨부파일 등록
                if (CollectionUtils.isNotEmpty(dto.attachLeaveFiles())) {
                    if (StringUtils.isEmpty(dto.attachLeaveDtlDocumentId())) {
                        ZogzAppendingFileRelationDvo apnFileRelDvo = ZogzAppendingFileRelationDvo.builder()
                            .apnFileRelDvCd(OgConst.FileRelDvCd.GAOOR_FILE_RELDV_CD.getCode())
                            .ogTpCd(dvo.getOgTpCd())
                            .prtnrNo(dvo.getPrtnrNo())
                            .refkVal1(dvo.getAplcSn())
                            .apnFileKndCd(OgConst.AtthGrpId.ATG_OGC_LEAVE.getCode())
                            .build();
                        appendingFileRelationService
                            .createAppendingFileRelationByAttachFFileDocId(dto.attachLeaveFiles(), apnFileRelDvo);
                    } else {
                        attachFileService.saveAttachFiles(
                            OgConst.AtthGrpId.ATG_OGC_LEAVE.getCode(), dto.attachLeaveDtlDocumentId(), dto.attachLeaveFiles()
                        );
                    }
                } else {
                    throw new BizException("MSG_ALT_NCELL_REQUIRED_ITEM", "휴업신청서");
                }

                // 휴업/복귀 - (휴업신청 증빙서류) 첨부파일 등록
                if (CollectionUtils.isNotEmpty(dto.attachLeaveDtlFiles())) {
                    if (StringUtils.isEmpty(dto.attachLeaveDtlDocumentId())) {
                        ZogzAppendingFileRelationDvo apnFileRelDvo = ZogzAppendingFileRelationDvo.builder()
                            .apnFileRelDvCd(OgConst.FileRelDvCd.GAOOR_FILE_RELDV_CD.getCode())
                            .ogTpCd(dvo.getOgTpCd())
                            .prtnrNo(dvo.getPrtnrNo())
                            .refkVal1(dvo.getAplcSn())
                            .apnFileKndCd(OgConst.AtthGrpId.ATG_OGC_LEAVE_DTL.getCode())
                            .build();
                        appendingFileRelationService
                            .createAppendingFileRelationByAttachFFileDocId(dto.attachLeaveDtlFiles(), apnFileRelDvo);
                    } else {
                        attachFileService.saveAttachFiles(
                            OgConst.AtthGrpId.ATG_OGC_LEAVE_DTL.getCode(), dto.attachLeaveDtlDocumentId(),
                            dto.attachLeaveDtlFiles()
                        );
                    }
                } else {
                    throw new BizException("MSG_ALT_NCELL_REQUIRED_ITEM", "휴업신청서 증빙서류");
                }
            } else {
                if (checks.contains("01")) {
                    // 1.2 품목입고내역, 서비스품목재고내역 이동재고수량 체크
                    BizAssert.isTrue(processCount == 1, "MSG_ALT_MMT_STOC_EXST_PROCS_IMPSB");
                } else if (checks.contains("02")) {
                    // 1.3 월별품목재고내역 시점재고수량 체크
                    BizAssert.isTrue(processCount == 1, "MSG_ALT_PITM_STOC_MINUS_EXST_PROCS_IMPSB");
                }
            }
        }
        return processCount;
    }
}
