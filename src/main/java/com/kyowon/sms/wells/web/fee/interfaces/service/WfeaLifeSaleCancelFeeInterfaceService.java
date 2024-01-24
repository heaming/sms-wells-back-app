package com.kyowon.sms.wells.web.fee.interfaces.service;

import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto;
import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto.IfRequest;
import com.kyowon.sms.wells.web.fee.interfaces.dvo.WfeaLifeSaleCancelFeenterfaceDvo;

import com.kyowon.sms.wells.web.fee.interfaces.mapper.WfeaLifeSaleCancelFeeInterfaceMapper;
import com.sds.sflex.system.config.exception.BizException;
import lombok.RequiredArgsConstructor;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WfeaLifeSaleCancelFeeInterfaceService {

    private final WfeaLifeSaleCancelFeeInterfaceMapper mapper;

    @Transactional
    public String[] updateLifeFeeSync(WfeaLifeSaleCancelFeeInterfaceDto.SaveReq dto) throws Exception {
        // 배치형
        for (IfRequest item : dto.list()) {
            // DTO > DVO
            WfeaLifeSaleCancelFeenterfaceDvo saveDvo = new WfeaLifeSaleCancelFeenterfaceDvo();
            saveDvo.setBaseYm(item.baseYm());
            saveDvo.setEtCnfmDvCd(item.etCnfmDvCd());
            saveDvo.setLifCntrNo(item.lifCntrNo());
            saveDvo.setLifCntrOcTn(item.lifCntrOcTn());
            saveDvo.setLifCntrStatCd(item.lifCntrStatCd());
            saveDvo.setSellDvCd(item.sellDvCd());
            saveDvo.setPrtnrNo(item.prtnrNo());
            saveDvo.setBrmgrPrtnrNo(item.brmgrPrtnrNo());
            saveDvo.setLifPdCd(item.lifPdCd());
            saveDvo.setLifPdNm(item.lifPdNm());
            saveDvo.setRcpdt(item.rcpdt());
            saveDvo.setCntrDt(item.cntrDt());
            saveDvo.setCanDt(item.canDt());
            saveDvo.setTotDsbOjDvCd(item.totDsbOjDvCd());
            saveDvo.setSlOcAcuAmt(item.slOcAcuAmt());
            saveDvo.setDpAcuAmt(item.dpAcuAmt());
            saveDvo.setFlpymTn(item.flpymTn());
            saveDvo.setCntrNo(item.welsCntrNo());
            saveDvo.setCntrSn(item.welsCntrSn());
            saveDvo.setFeeDsbYm(item.feeDsbYm());
            saveDvo.setFeeRedfYm(item.feeRedfYm());
            saveDvo.setCnfmYn(item.cnfmYn());
            saveDvo.setDtaDlYn(item.dtaDlYn());
            saveDvo.setFstRgstDtm(item.fstRgstDtm());
            saveDvo.setFstRgstUsrId(item.fstRgstUsrId());
            saveDvo.setFstRgstPrgId(item.fstRgstPrgId());
            saveDvo.setFstRgstDeptId(item.fstRgstDeptId());
            saveDvo.setFnlMdfcDtm(item.fnlMdfcDtm());
            saveDvo.setFnlMdfcUsrId(item.fnlMdfcUsrId());
            saveDvo.setFnlMdfcPrgId(item.fnlMdfcPrgId());
            saveDvo.setFnlMdfcDeptId(item.fnlMdfcDeptId());
            if (StringUtils.isNotEmpty(item.ogTpCd())) {
                String ogTpCd = item.ogTpCd();
                if ("1".equals(item.ogTpCd())) {
                    ogTpCd = "E01";
                }
                if ("2".equals(item.ogTpCd())) {
                    ogTpCd = "W01";
                }
                if ("7".equals(item.ogTpCd())) {
                    ogTpCd = "W02";
                }
                saveDvo.setOgTpCd(ogTpCd);
            }
            String cnfmYn = mapper.selectLifeFeeValidKey(saveDvo);
            // 2024.01.24 예상확정구분코드 들어오는게 00이아니면 에러
            if (!"00".equals(item.etCnfmDvCd())) {
                 throw new BizException("MSG_ALT_NOT_CNFM_STAT"); // 확정상태가 아닙니다.
            }
            // 1.1 마감여부? 체크 후 마감이면 에러 CNFM_YN = 'Y'면 에러
            if ("Y".equals(cnfmYn)) {
                throw new BizException("MSG_ALT_CNFM_NO_RENEW_DATA"); // 확정된 DATA는 갱인이 불가능합니다.
            } else {
                // 1.2 그외 처리한다.
                int resultCnt = mapper.updateLifeFeeSync(saveDvo);
                if (resultCnt <= 0) {
                    throw new BizException("MSG_ALT_SVE_ERR"); // 응 서버에러
                }
            }
        }
        return new String[]{"S", "데이터 등록이 성공하였습니다."};
    }
}
