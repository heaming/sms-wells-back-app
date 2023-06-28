package com.kyowon.sms.wells.web.fee.interfaces.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfeaLifeSaleCancelFeenterfaceDvo {

    private String baseYm; /* 기준년월 */
    private String etCnfmDvCd; /* 예상확정구분코드 */
    private String lifCntrNo; /* 라이프계약번호 */
    private int lifCntrOcTn; /* 라이프계약발생회차 */
    private String lifCntrStatCd; /* 라이프계약상태코드 */
    private String sellDvCd; /* 판매구분코드 */
    private String prtnrNo; /* 파트너번호 */
    private String brmgrPrtnrNo; /* 지점장파트너번호 */
    private String lifPdCd; /* 라이프상품코드 */
    private String lifPdNm; /* 라이프상품명 */
    private String rcpdt; /* 접수일자 */
    private String cntrDt; /* 계약일자 */
    private String canDt; /* 취소일자 */
    private String totDsbOjDvCd; /* 총지급대상구분코드 */
    private int slOcAcuAmt; /* 매출발생누적금액 */
    private int dpAcuAmt; /* 입금누적금액 */
    private int flpymTn; /* 완납회차 */
    private String welsCntrNo; /* 웰스계약번호 */
    private String welsCntrSn; /* 웰스계약일련번호 */
    private String feeDsbYm; /* 수수료지급년월 */
    private String feeRedfYm; /* 수수료되물림년월 */
    private String cnfmYn; /* 확정여부 */
    private String dtaDlYn; /* 데이터삭제여부 */
    private String fstRgstDtm; /* 최초등록일시 */
    private String fstRgstUsrId; /* 최초등록사용자ID */
    private String fstRgstPrgId; /* 최초등록프로그램ID */
    private String fstRgstDeptId; /* 최초등록부서ID */
    private String fnlMdfcDtm; /* 최종수정일시 */
    private String fnlMdfcUsrId; /* 최종수정사용자ID */
    private String fnlMdfcPrgId; /* 최종수정프로그램ID */
    private String fnlMdfcDeptId; /* 최종수정부서ID */
}
