package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kyowon.sms.wells.web.withdrawal.idvrve.converter.WwdbCorporationDepositSspMgtConverter;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.CreateReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchReq;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbCorporationDepositSspMgtDto.SearchRes;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbCorporationDepositSspMgtDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbCorporationDepositSspMgtMapper;
import com.kyowon.sms.wells.web.withdrawal.idvrve.util.WithdrawalExcelUtil;
import com.sds.sflex.common.common.dvo.ExcelMetaDvo;
import com.sds.sflex.common.common.service.ExcelReadService;
import com.sds.sflex.common.utils.StringUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.datasource.PageInfo;
import com.sds.sflex.system.config.datasource.PagingResult;
import com.sds.sflex.system.config.validation.BizAssert;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class WwdbCorporationDepositSspMgtService {

    private final WwdbCorporationDepositSspMgtMapper mapper;
    private final ExcelReadService excelReadService;
    private final MessageResourceService messageResourceService;
    private final WwdbCorporationDepositSspMgtConverter converter;

    @Transactional
    public PagingResult<SearchRes> getselectCorporationDepositSspPages(SearchReq dto, PageInfo pageInfo) {
        return mapper.selectCorporationDepositSspMgt(dto, pageInfo);
    }

    @Transactional
    public List<SearchRes> getselectCorporationDepositSspExcels(SearchReq dto) {
        return mapper.selectCorporationDepositSspMgt(dto);
    }

    @Transactional
    public List<WwdbCorporationDepositSspMgtDvo> getUploadExcels(MultipartFile file) throws Exception {

        Map<String, String> headerTitle = new LinkedHashMap<>();
        headerTitle.put("alncmpSuscOrdNo", messageResourceService.getMessage("MSG_TXT_SUSC_ORD_NO")); /*구독주문번호*/
        headerTitle.put("cstY", messageResourceService.getMessage("MSG_TXT_CST_Y")); /*고객년도*/
        headerTitle.put("cstCd", messageResourceService.getMessage("MSG_TXT_CST_CD")); /*고객코드*/
        headerTitle.put("cstSeqn", messageResourceService.getMessage("MSG_TXT_CST_SEQN")); /*고객순번*/
        headerTitle.put("sellTpCd", messageResourceService.getMessage("MSG_TXT_SEL_TYPE")); /*판매유형*/
        headerTitle.put("alncmpDpAmt", messageResourceService.getMessage("MSG_TXT_DEPOSIT_AMT")); /*입금액*/
        headerTitle.put("alncmpFee", messageResourceService.getMessage("MSG_TXT_FEE")); /*수수료*/
        headerTitle.put("sumAmt", messageResourceService.getMessage("MSG_TXT_SUM_AMT")); /*합계금액*/

        ExcelMetaDvo meta = new ExcelMetaDvo(1, headerTitle);

        List<WwdbCorporationDepositSspMgtDvo> dvo = new ArrayList<WwdbCorporationDepositSspMgtDvo>();

        List<WwdbCorporationDepositSspMgtDvo> dvos = excelReadService
            .readExcel(file, meta, WwdbCorporationDepositSspMgtDvo.class);

        for (WwdbCorporationDepositSspMgtDvo depositSspMgtDvo : dvos) {
            if (!ObjectUtils.isEmpty(depositSspMgtDvo.getCstY())) {
                depositSspMgtDvo
                    .setAlncmpSuscOrdNo(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getAlncmpSuscOrdNo()));
                depositSspMgtDvo.setCstY(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getCstY()));
                depositSspMgtDvo.setCstCd(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getCstCd()));
                depositSspMgtDvo.setCstSeqn(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getCstSeqn()));
                depositSspMgtDvo.setSellTpCd(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getSellTpCd()));
                depositSspMgtDvo
                    .setAlncmpDpAmt(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getAlncmpDpAmt()));
                depositSspMgtDvo.setAlncmpFee(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getAlncmpFee()));
                depositSspMgtDvo.setSumAmt(WithdrawalExcelUtil.formatObjToString(depositSspMgtDvo.getSumAmt()));

                dvo.add(depositSspMgtDvo);

                log.info("========================");
                //                log.info(depositSspMgtDvo.toString());
                log.info("========================");

            }
        }

        if (dvo.size() > 0) {

            for (int i = 0; i < dvo.size(); i++) {
                WwdbCorporationDepositSspMgtDvo vailDvo = dvo.get(i);

                /* 데이터 검증 */
                if (StringUtil.isBlank(vailDvo.getAlncmpSuscOrdNo())) { //구독주문번호
                    BizAssert.hasText(
                        vailDvo.getAlncmpSuscOrdNo(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("alncmpSuscOrdNo"),
                            vailDvo.getAlncmpSuscOrdNo()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getCstY())) { //고객년도
                    BizAssert.hasText(
                        vailDvo.getCstY(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("cstY"),
                            vailDvo.getCstY()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getCstCd())) { //고객코드
                    BizAssert.hasText(
                        vailDvo.getCstCd(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("cstCd"),
                            vailDvo.getCstCd()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getCstSeqn())) { //고객순번
                    BizAssert.hasText(
                        vailDvo.getCstSeqn(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("cstSeqn"),
                            vailDvo.getCstSeqn()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getSellTpCd())) { //판매유형
                    BizAssert.hasText(
                        vailDvo.getSellTpCd(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("sellTpCd"),
                            vailDvo.getSellTpCd()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getAlncmpDpAmt())) { //입금액
                    BizAssert.hasText(
                        vailDvo.getAlncmpDpAmt(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("alncmpDpAmt"),
                            vailDvo.getAlncmpDpAmt()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getAlncmpFee())) { //수수료
                    BizAssert.hasText(
                        vailDvo.getAlncmpFee(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("alncmpFee"),
                            vailDvo.getAlncmpFee()}
                    );
                }

                if (StringUtil.isBlank(vailDvo.getSumAmt())) { //합계금액
                    BizAssert.hasText(
                        vailDvo.getSumAmt(), "MSG_ALT_INVALID_UPLOAD_DATA",
                        new String[] {String.valueOf(i + 1), headerTitle.get("sumAmt"),
                            vailDvo.getSumAmt()}
                    );
                }
            }
        }

        return dvo;
    }

    @Transactional
    public int createCorporationDepositSsps(List<CreateReq> dtos) throws Exception {
        int processCount = 0;

        for (CreateReq req : dtos) {

            WwdbCorporationDepositSspMgtDvo dvo = converter.mapSaveReq(req);
            processCount += mapper.insertCorporationDepositSspMgt(dvo);
        }
        return processCount;
    }
}
