package com.kyowon.sms.wells.web.fee.calculation.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WfebB2bDtlFeeDvo {

  private String ddtnYm; /* 공제년월 */
  private String coCd; /* 회사코드 */
  private String ogTpCd; /* 조직유형코드 */
  private String prtnrNo; /* 파트너번호 */
  private String feeDdtnTpCd;/* 수수료공제유형코드 */
  private String feeTcntDvCd;/* 수수료차수구분코드 */
  private String spmtDsbDvCd; /* 추가지급구분코드 */
  private String feeDdtnCrtCd; /* 수수료공제생성코드 */
  private Long feeDdctam; /* 수수료공제금액 */
  private String feeCtrRsonCn; /* 수수료조정사유내용 */
  private Long feeDdtnCnfmAmt; /* 수수료공제확정금액 */
  private String feeCtrOgTpCd; /* 수수료조정조직유형코드 */
  private String feeCtrPrtnrNo; /* 수수료조정파트너번호 */
  private String dtaDlYn; /* 데이터삭제여부 */

}
