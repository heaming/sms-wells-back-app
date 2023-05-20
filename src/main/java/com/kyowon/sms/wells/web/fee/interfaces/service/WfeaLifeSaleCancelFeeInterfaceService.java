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
        saveDvo.setBaseYm(dto.aksdte());
        saveDvo.setEtCnfmDvCd(dto.itm01());
        saveDvo.setLifCntrNo(dto.itm02());
        saveDvo.setLifCntrOcTn(dto.akdseq());
        saveDvo.setLifCntrStatCd(dto.aksgub());
        saveDvo.setSellDvCd(dto.aksale());
        saveDvo.setPrtnrNo(dto.akdcde());
        saveDvo.setBrmgrPrtnrNo(dto.akdbon());
        saveDvo.setLifPdCd(dto.akcode());
        saveDvo.setLifPdNm(dto.itm03());
        saveDvo.setRcpdt(dto.akcrte());
        saveDvo.setCntrDt(dto.akslte());
        saveDvo.setCanDt(dto.akcdte());
        saveDvo.setTotDsbOjDvCd(dto.aktcnt());
        saveDvo.setSlOcAcuAmt(dto.akmamt());
        saveDvo.setDpAcuAmt(dto.akiamt());
        saveDvo.setFlpymTn(dto.akiseq());
        saveDvo.setWelsCntrNo(dto.itm04());
        saveDvo.setWelsCntrSn(dto.itm05());
        saveDvo.setFeeDsbYm(dto.akgdym());
        saveDvo.setFeeRedfYm(dto.akhdym());
        saveDvo.setCnfmYn(dto.aklock());
        saveDvo.setDtaDlYn(dto.itm06());
        saveDvo.setFstRgstDtm(dto.akwday());
        saveDvo.setFstRgstUsrId(dto.akwdsp());
        saveDvo.setFstRgstPrgId(dto.akwpgm());
        saveDvo.setFstRgstDeptId(dto.itm07());
        saveDvo.setFnlMdfcDtm(dto.akuday());
        saveDvo.setFnlMdfcUsrId(dto.akudsp());
        saveDvo.setFnlMdfcPrgId(dto.akupgm());
        saveDvo.setFnlMdfcDeptId(dto.itm08());

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
