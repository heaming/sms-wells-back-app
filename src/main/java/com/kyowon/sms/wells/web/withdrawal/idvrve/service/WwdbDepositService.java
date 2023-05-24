package com.kyowon.sms.wells.web.withdrawal.idvrve.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kyowon.sms.common.web.withdrawal.idvrve.mapper.ZwwdbEtcDepositMapper;
import com.kyowon.sms.common.web.withdrawal.zcommon.dvo.ZwdzWithdrawalReceiveDvo;
import com.kyowon.sms.common.web.withdrawal.zcommon.service.ZwdzWithdrawalService;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dto.WwdbDepositDto;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositComparisonComfirmationDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbDepositProcessingResultDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.dvo.WwdbIntegrationDepositInfoDvo;
import com.kyowon.sms.wells.web.withdrawal.idvrve.mapper.WwdbDepositMapper;
import com.sds.sflex.common.utils.DateUtil;
import com.sds.sflex.system.config.core.service.MessageResourceService;
import com.sds.sflex.system.config.response.SaveResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WwdbDepositService {

    private final WwdbDepositMapper mapper;
    private final ZwwdbEtcDepositMapper depositeMapper;
    private final MessageResourceService messageResourceService;
    private final ZwdzWithdrawalService zwdzWithdrawalService;

    /**
     * 입금처리－합계체크용 (W-WD-S-0003)
     * @param dto
     * @return
     */
    public SaveResponse saveDepositDetails(
        WwdbDepositDto.SaveReq dto
    ) {
        List<WwdbDepositProcessingResultDvo> results = new ArrayList<WwdbDepositProcessingResultDvo>();

        String resultCd = "";
        String resultCntn = "";
        // 파리미터 체크(계약번호)
        if (StringUtils.isEmpty(dto.cardNo())) {
            resultCd = "1";
            resultCntn = messageResourceService
                .getMessage("MSG_ALT_NCELL_REQUIRED_VAL", messageResourceService.getMessage("MSG_TXT_CNTR_NO"));
        }
        // 파리미터 체크(업무유형코드)
        if (StringUtils.isEmpty(dto.sellTpCd())) {
            resultCd = "1";
            resultCntn = messageResourceService
                .getMessage("MSG_ALT_NCELL_REQUIRED_VAL", messageResourceService.getMessage("MSG_TXT_TASK_TYPE"));
        }
        // 파리미터 체크(업무구분코드)
        if (StringUtils.isEmpty(dto.sellTpDtlCd())) {
            resultCd = "1";
            resultCntn = messageResourceService
                .getMessage("MSG_ALT_NCELL_REQUIRED_VAL", messageResourceService.getMessage("MSG_TXT_TASK_DIV"));
        }

        if (StringUtils.isEmpty(resultCd)) {

            // 판매유형코드가 렌탈/리스, 멤버십 인 경우
            List<WwdbIntegrationDepositInfoDvo> itgDeposits = mapper.selectIntegrationDepositInfos(
                WwdbDepositDto.SearchReq.builder().cntrNo(dto.cntrNo()).build()
            );

            for (WwdbIntegrationDepositInfoDvo itgDeposit : itgDeposits) {
                WwdbDepositProcessingResultDvo result = new WwdbDepositProcessingResultDvo();
                WwdbDepositComparisonComfirmationDvo dpCrcnfDvo = new WwdbDepositComparisonComfirmationDvo();
                // 조회된 통합입금 데이터의 입금금액 바탕으로 입금 대사처리
                // TODO : 입금대사 기준 확립 후 금액 처리 필요
                // 1. 통합입금기본 테이블의 수납요청번호에 해당하는 수납요청상세 데이터 조회
                // 수납요청상세 데이터 조회
                List<WwdbIntegrationDepositInfoDvo> rveAkDtls = mapper
                    .selectReceiveAskDetailInfos(itgDeposit.getItgDpNo());

                if (!ObjectUtils.isEmpty(rveAkDtls)) {
                    // 입금된 금액 추출
                    Integer itgDpAmt = Integer.parseInt(itgDeposit.getDpAmt());
                    // 조회된 수납요청상세 데이터의 수납요청금액에 따라 계산
                    for (WwdbIntegrationDepositInfoDvo rveAkDtl : rveAkDtls) {
                        Integer rveAmt = Integer.parseInt(rveAkDtl.getRveAkAmt());
                        Integer dpCprcnfAmt = 0;

                        // TODO : 입금대사 기준 확립 후 금액 계산

                        // 2. 수납요청상세 데이터의 각 계약번호,계약일련번호 별 입금금액 계산 후 입금대사기본 테이블 Insert
                        // 입금대사기본 테이블 Insert
                        // 입금대사번호 PK 조회
                        String dpCrcnfNo = depositeMapper.selectEtcDepositPk();
                        dpCrcnfDvo.setDpCprcnfNo(dpCrcnfNo);
                        dpCrcnfDvo.setKwGrpCoCd(itgDeposit.getKwGrpCoCd());
                        dpCrcnfDvo.setRveCoCd(itgDeposit.getRveCoCd());
                        dpCrcnfDvo.setRveCd(itgDeposit.getRveCd());
                        dpCrcnfDvo.setDpDvCd(itgDeposit.getDpDvCd());
                        dpCrcnfDvo.setDpMesCd(itgDeposit.getDpMesCd());
                        dpCrcnfDvo.setDpTpCd(itgDeposit.getDpTpCd());
                        dpCrcnfDvo.setRveDvCd(rveAkDtl.getRveDvCd());
                        dpCrcnfDvo.setSellTpCd(rveAkDtl.getSellTpCd());
                        dpCrcnfDvo.setSellTpDtlCd(rveAkDtl.getSellTpDtlCd());
                        dpCrcnfDvo.setPdClsfId(rveAkDtl.getPdClsfId());
                        dpCrcnfDvo.setDpAmt(String.valueOf(dpCprcnfAmt));
                        dpCrcnfDvo.setItgDpNo(itgDeposit.getItgDpNo());
                        dpCrcnfDvo.setCntrNo(itgDeposit.getCntrNo());
                        dpCrcnfDvo.setCntrSn(itgDeposit.getCntrSn());
                        dpCrcnfDvo.setIncmdcYn(itgDeposit.getIncmdcYn());
                        dpCrcnfDvo.setRveOjDrmNo1(rveAkDtl.getRveAkOjDrmNo1());
                        dpCrcnfDvo.setRveOjDrmNo2(rveAkDtl.getRveAkOjDrmNo2());
                        // 입금대사기본 Insert
                        mapper.insertDepositComparisonComfirmation(dpCrcnfDvo);
                    }

                    // 3. 하나의 통합입금번호에 대해 입금대사 처리 완료 후 수납기본, 수납상세 Insert
                    ZwdzWithdrawalReceiveDvo receiveDvo = new ZwdzWithdrawalReceiveDvo();
                    receiveDvo.setKwGrpCoCd(itgDeposit.getKwGrpCoCd());
                    receiveDvo.setRveAkNo(itgDeposit.getRveAkNo());
                    receiveDvo.setRvePhCd("");
                    String rveDt = DateUtil.getNowDayString();
                    if (!StringUtils.isEmpty(itgDeposit.getDpDtm())) {
                        rveDt = itgDeposit.getDpDtm();
                    }
                    receiveDvo.setRveDt(rveDt);
                    receiveDvo.setRveAmt(itgDeposit.getDpAmt());
                    // 수납기본 Insert
                    String rveNo = zwdzWithdrawalService.createReceive(receiveDvo);
                    receiveDvo.setRveNo(rveNo);
                    receiveDvo.setItgDpNo(itgDeposit.getItgDpNo());
                    // 수납상세 Insert
                    zwdzWithdrawalService.createReceiveDetail(receiveDvo);

                    // 4. 입금된 데이터가 가상계좌인 경우 가상계좌입금배분내역 Insert
                    mapper.insertVirtualAccountDistribution(itgDeposit.getItgDpNo());

                    // TODO : 5. 통합입금기본 테이블의 입금금액 - 총 입금대사금액 계산 후 임금금액이 남는 경우 처리 방식 확인
                }
            }
        }

        return SaveResponse
            .builder()
            .data(results)
            .build();
    }
}
