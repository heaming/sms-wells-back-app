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
        int index = 0;
        String baseYmCheck = "";
        String ogTpCdCheck = "";
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
            // 1. 첫 item만 정합성 체크한다.
            if (index == 0) {
                baseYmCheck = saveDvo.getBaseYm(); // 첫row 년월 체크용
                ogTpCdCheck = saveDvo.getOgTpCd(); // 첫row 조직 유형 체크용
                // 1.2 예상확정구분코드가 00상태면 확정건수 체크후 있으면 에러 없으면 삭제후 데이터저장진행
                if ("00".equals(saveDvo.getEtCnfmDvCd())) {
                    int count = mapper.selectLifeFeeValidKeyCount(saveDvo); // 이미 확정된 건수 체크
                    if (count > 0) {
                        throw new BizException("MSG_ALT_CNFM_NO_RENEW_DATA"); // 확정된 DATA는 갱인이 불가능합니다.
                    } else {
                        mapper.deleteLifeFeeSync(saveDvo);
                    }
                }
                // 1.3 예상확정구분코드가 01상태면 전부 지우고 데이터 저장 진행
                if ("01".equals(saveDvo.getEtCnfmDvCd())) {
                    mapper.deleteLifeFeeSync(saveDvo);
                }
            }

            // 1.4 첫row의 baseym과 저장하는 baseym이 다르면 에러
            if (!baseYmCheck.equals(saveDvo.getBaseYm())) {
                throw new BizException("MSG_ALT_CHK_DT"); // 날짜를 확인해 주세요.
            }
            // 1.5 첫row의 ogTpCd와 저장하는 ogTpCd가 다르면 에러
            if (!ogTpCdCheck.equals(saveDvo.getOgTpCd())) {
                throw new BizException("MSG_TXT_OG_TP_CD_ERROR"); // 조직유형코드가 올바르지 않습니다.
            }
            // 1.6 데이터 저장
            mapper.insertLifeFeeSync(saveDvo);
            index++;
        }
        return new String[]{"S", "데이터 등록이 성공하였습니다."};
    }
}
