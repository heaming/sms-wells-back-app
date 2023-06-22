package com.kyowon.sms.wells.web.fee.interfaces.service;

import com.kyowon.sms.wells.web.fee.interfaces.dto.WfeaLifeSaleCancelFeeInterfaceDto;
import com.kyowon.sms.wells.web.fee.interfaces.dvo.WfeaLifeSaleCancelFeenterfaceDvo;

import com.kyowon.sms.wells.web.fee.interfaces.mapper.WfeaLifeSaleCancelFeeInterfaceMapper;
import com.sds.sflex.system.config.exception.BizException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class WfeaLifeSaleCancelFeeInterfaceService {

    private final WfeaLifeSaleCancelFeeInterfaceMapper mapper;

    @Transactional
    public String[] updateLifeFeeSync(WfeaLifeSaleCancelFeeInterfaceDto.SaveReq dto) throws Exception {
        // DTO > DVO
        WfeaLifeSaleCancelFeenterfaceDvo saveDvo = new WfeaLifeSaleCancelFeenterfaceDvo();
        saveDvo.setBaseYm(dto.aKSDTE());
        saveDvo.setEtCnfmDvCd(dto.iTM01());
        saveDvo.setLifCntrNo(dto.iTM02());
        saveDvo.setLifCntrOcTn(dto.aKDSEQ());
        saveDvo.setLifCntrStatCd(dto.aKSGUB());
        saveDvo.setSellDvCd(dto.aKSALE());
        saveDvo.setPrtnrNo(dto.aKDCDE());
        saveDvo.setBrmgrPrtnrNo(dto.aKDBON());
        saveDvo.setLifPdCd(dto.aKCODE());
        saveDvo.setLifPdNm(dto.iTM03());
        saveDvo.setRcpdt(dto.aKCRTE());
        saveDvo.setCntrDt(dto.aKSLTE());
        saveDvo.setCanDt(dto.aKCDTE());
        saveDvo.setTotDsbOjDvCd(dto.aKTCNT());
        saveDvo.setSlOcAcuAmt(dto.aKMAMT());
        saveDvo.setDpAcuAmt(dto.aKIAMT());
        saveDvo.setFlpymTn(dto.aKISEQ());
        saveDvo.setWelsCntrNo(dto.iTM04());
        saveDvo.setWelsCntrSn(dto.iTM05());
        saveDvo.setFeeDsbYm(dto.aKGDYM());
        saveDvo.setFeeRedfYm(dto.aKHDYM());
        saveDvo.setCnfmYn(dto.aKLOCK());
        saveDvo.setDtaDlYn(dto.iTM06());
        saveDvo.setFstRgstDtm(dto.aKWDAY());
        saveDvo.setFstRgstUsrId(dto.aKWDSP());
        saveDvo.setFstRgstPrgId(dto.aKWPGM());
        saveDvo.setFstRgstDeptId(dto.iTM07());
        saveDvo.setFnlMdfcDtm(dto.aKUDAY());
        saveDvo.setFnlMdfcUsrId(dto.aKUDSP());
        saveDvo.setFnlMdfcPrgId(dto.aKUPGM());
        saveDvo.setFnlMdfcDeptId(dto.iTM08());

        String cnfmYn = mapper.selectLifeFeeValidKey(saveDvo);
        // 1.1 마감여부? 체크 후 마감이면 에러 CNFM_YN = 'Y'면 에러
        if ("Y".equals(cnfmYn)) {
            throw new BizException("MSG_ALT_CNFM_NO_RENEW_DATA"); // 확정된 DATA는 갱인이 불가능합니다.
        } else {
            // 1.2 그외 처리한다.
            int resultCnt = mapper.updateLifeFeeSync(saveDvo);
            if (resultCnt <= 0) {
                throw new BizException("MSG_ALT_SVE_ERR"); // 응 서버에러
            } else {
                return new String[] { "S", "데이터 등록이 성공하였습니다." };
            }
        }
    }
}
