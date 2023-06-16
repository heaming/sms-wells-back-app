package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;

public class WwdbDepositDetailDto {
    public record SearchReq(
        String sellTpCd, // 판매유형  
        String dpDvCd, // 입금구분  
        String startDt,
        String endDt,
        String cntrNo,
        String cntrSn,
        String stClctamPrtnrNo, // 집금담당자 사번 시작
        String enClctamPrtnrNo, // 집금담당자 사번 끝
        String stFstRgstUsrId, // 입력담당자 사번 시작
        String enFstRgstUsrId,

        String dpMesCd, // 입금수단  
        String dpTpCd, // 입금유형  
        String vncoDvCd, // van사 구분
        String ogTpCd // 조직유형
    ) {

    }
    public record SearchRes(
        String cntrNo,
        String cntrSn,
        String cntr,
        String dpNo, //입금번호
        String cstNo, //고객번호
        String cstKnm, //고객명
        String ogCd, //지점코드
        String prtnrKnm, //판매자
        String sellPrtnrNo, //판매파트너번호
        String rveCd, // 수납코드
        String sellTpCd, // 판매유형  
        String dpDvCd, // 입금구분 
        String dpDt,
        //        String startDt, //입금일자 시작
        //        String endDt, // 입금일자 끝
        String perfDt, //실적일자
        String dpAmt, //입금액
        String fnitNm, //카드구분
        String crcdnoEncr, //카드번호
        String crdcdIstmMcn, //할부
        String crdcdAprno, //승인번호
        String crcdonrNm, //카드주명
        String dpCprcnfNo, //대사번호
        String cntrCanDtm, //취소일자
        String rgstKnm, //입력담당자
        String fstRgstUsrId,
        //        String stFstRgstUsrId, // 입력담당자 사번 시작
        //        String enFstRgstUsrId,
        String clctamPrtnrNm, // 집금담당자
        String clctamPrtnrNo,
        //        String stClctamPrtnrNo, // 집금담당자 사번 시작
        //        String enClctamPrtnrNo, // 집금담당자 사번 끝

        String dpMesCd, // 입금수단  
        String dpTpCd, // 입금유형  
        String vncoDvCd, // van사 구분
        String ogTpCd // 조직유형
    ) {
        public SearchRes {
            if (!StringUtil.isEmpty(crcdnoEncr)) {
                crcdnoEncr = DbEncUtil.dec(crcdnoEncr);
            }
        }
    }

}
