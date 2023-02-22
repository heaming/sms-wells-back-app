package com.kyowon.sms.wells.web.bond.transfer.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;

public class WbnaCollectorAssignDto {
    /**
     * 집금자배정 검색
     * @param bzHdqDvCd 사업부코드
     * @param clctamDvCd 집금구분코드
     * @param cstNo 고객번호
     * @param nwYn 신규여부
     * @param clctamPrtnrNo 집금담당자
     */
    @ApiModel("WbnaCollectorAssignDto-SearchReq")
    public record SearchReq(
        @NotBlank
        String bzHdqDvCd,
        String clctamDvCd,
        String cstNo,
        String nwYn,
        String clctamPrtnrNo
    ) {}

    @ApiModel("WbnaCollectorAssignDto-SearchRes")
    public record SearchRes(
        String clctamPrtnrNo, /* 집금담당자번호			*/
        String clctamPrtnrKnm, /* 집금담당자명			*/
        Integer asnRat, /* 배정비중            */
        Integer woCstn, /* 전체 고객수          */
        Integer woCntrCt, /* 전체 계약수          */
        Long rwoTrgAmt, /* 전체 대상금액         */
        Long woDlqAmt, /* 전체 연체금액         */
        Long woThmChramAmt, /* 전체 당월금액         */
        Long woDlqAddAmt, /* 전체 연체가산금액      */
        Long woRsgBorAmt, /* 전체 위약금액         */
        Integer rentalCstn, /* 렌탈 고객수          */
        Integer rentalCntrCt, /* 렌탈 계약수          */
        Long rentalTrgAmt, /* 렌탈 대상금액         */
        Long rentalDlqAmt, /* 렌탈 연체금액         */
        Long rentalThmChramAmt, /* 렌탈 당월금액         */
        Long rentalDlqAddAmt, /* 렌탈 연체가산금액      */
        Long rentalRsgBorAmt, /* 렌탈 위약금액         */
        Integer leaseCstn, /* 금융리스 고객수       */
        Integer leaseCntrCt, /* 금융리스 계약수       */
        Long leaseTrgAmt, /* 금융리스 대상금액      */
        Long leaseDlqAmt, /* 금융리스 연체금액      */
        Long leaseThmChramAmt, /* 금융리스 당월금액      */
        Long leaseDlqAddAmt, /* 금융리스 연체가산금액    */
        Long leaseRsgBorAmt, /* 금융리스 위약금액      */
        Integer geMshCstn, /* 일반멤버십 고객수      */
        Integer geMshCntrCt, /* 일반멤버십 계약수      */
        Long geMshTrgAmt, /* 일반멤버십 대상금액     */
        Long geMshDlqAmt, /* 일반멤버십 연체금액     */
        Long geMshThmChramAmt, /* 일반멤버십 당월금액     */
        Long geMshDlqAddAmt, /* 일반멤버십 연체가산금액   */
        Long geMshRsgBorAmt, /* 일반멤버십 위약금액     */
        Integer hcrMshCstn, /* 홈케어멤버십 고객수     */
        Integer hcrMshCntrCt, /* 홈케어멤버십 계약수     */
        Long hcrMshTrgAmt, /* 홈케어멤버십 대상금액    */
        Long hcrMshDlqAmt, /* 홈케어멤버십 연체금액    */
        Long hcrMshThmChramAmt, /* 홈케어멤버십 당월금액    */
        Long hcrMshDlqAddAmt, /* 홈케어멤버십 연체가산금액 */
        Long hcrMshRsgBorAmt, /* 홈케어멤버십 위약금액    */
        Integer spayCstn, /* 일시불 고객수         */
        Integer spayCntrCt, /* 일시불 계약수         */
        Long spayTrgAmt, /* 일시불 대상금액       */
        Long spayDlqAmt, /* 일시불 연체금액       */
        Long spayThmChramAmt, /* 일시불 당월금액       */
        Long spayDlqAddAmt, /* 일시불 연체가산금액     */
        Long spayRsgBorAmt, /* 일시불 위약금액       */
        Integer rglrSppCstn, /* 정기배송 : 고객수     */
        Integer rglrSppCntrCt, /* 정기배송 : 계약수     */
        Long rglrSppTrgAmt, /* 정기배송 : 대상금액    */
        Long rglrSppDlqAmt, /* 정기배송 : 연체금액    */
        Long rglrSppThmChramAmt, /* 정기배송 : 당월금액    */
        Long rglrSppDlqAddAmt, /* 정기배송 : 연체가산금액  */
        Long rglrSppRsgBorAmt /* 정기배송 : 위약금액    */
    ) {}

    @ApiModel("WbnaCollectorAssignDto-SearchDetailRes")
    public record SearchDetailRes(
        String clctamPrtnrNo, /* 담당자번호		*/
        String clctamPrtnrKnm, /* 담당자명		*/
        String lstmmClctamDvCd, /* 전월담당집금구분   */
        String lstmmClctamPrtnrNo, /* 전월담당자 번호      */
        String lstmmClctamPrtnrKnm, /* 전월담당자       */
        String cntrNo, /* 계약번호        */
        String cntrSn, /* 계약일련번호      */
        String cstKnm, /* 고객명         */
        String cstNo, /* 고객번호        */
        String pdNm, /* 상품구분        */
        Integer dlqMcn, /* 연체개월        */
        Long trgAmt, /* 대상금액        */
        Long dlqAmt, /* 연체금액        */
        Long thmChramAmt, /* 당월요금        */
        Long dlqAddAmt, /* 연체가산금       */
        Long rsgBorAmt, /* 위약금액        */
        String lwmTpCd, /* 법조치유형       */
        String lwmDtlTpCd, /* 법조치상세       */
        String lwmDt, /* 법조치일자       */
        String dfltDt, /* 채불등록일자      */
        String addr /* 주소           */
    ) {}

}
