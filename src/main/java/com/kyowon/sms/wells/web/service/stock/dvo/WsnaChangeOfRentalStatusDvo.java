package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WsnaChangeOfRentalStatusDvo {
    String svpdSapCd;/* SAP코드 */
    String svpdPdCd; /* 품목코드 */
    String svpdNmAbbr1; /* 품목명 */
    String istDt; /* 설치일자 */
    String fnlVstFshDt; /* 작업일자 */
    String reqdDt; /* 철거요청일자 */
    String fnlItmGdCd; /* 등급 */
    String deptNm; /* 담당센터 */
    String useQty; /* 사용수량 */
    String cntrDtlNo; /* 고객번호 */
    String rcgvpKnm; /* 고객명 */
    String ostrConfDt; /* 확인일자 */
    String ostrDt; /* 전산반품일자 */
    String rtngdProcsTpCd; /* 반품처리유형 */
    String rmkCn; /* 특이사항 */
    String rentalAssetStat; /* 랜탈관련상태값 */
    String cntrNo; /* 고객번호 */
    String cntrSn; /* 고객일련번호*/
    String rtngdRvpyProcsYn; /* 반품수불처리여부 */
    String hgrWareNo; /* 상위창고번호 */
    String factoryDisposalGb; /* 물류폐기, 공장폐기 임시구분 */
    String svpdItemGr; /* 품목상품구분 */

}
