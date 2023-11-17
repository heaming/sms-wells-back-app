package com.kyowon.sms.wells.web.withdrawal.idvrve.dto;

import javax.validation.constraints.NotBlank;

import com.sds.sflex.common.utils.DbEncUtil;
import com.sds.sflex.common.utils.StringUtil;
import io.swagger.annotations.ApiModel;

/**
 * <pre>
 * 입금내역조회(모바일) DTO
 * </pre>
 *
 * @author jaehaYeon
 * @since 2023-06-07
 */
public class WwdbDepositItemizationDto {

    // *********************************************************
    // Object Dto
    // *********************************************************

    // *********************************************************
    // Request Dto
    // *********************************************************
    // 입금내역조회(모바일) Request Dto
    @ApiModel("WwdbDepositItemizationDto-SearchReq")
    public record SearchReq(
        String cstNo, /*고객번호*/
        String startDt, /*조회시작일*/
        String finishDt /*조회종료일*/
    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금내역조회(모바일) Result Dto
    @ApiModel("WwdbDepositItemizationDto-SearchRes")
    public record SearchRes(
        String dpMesCd, /*입금수단코드*/
        String dpMesCdNm, /*입금수단코드명*/
        String dpTpCd, /*입금유형코드*/
        String dpTpCdNm, /*입금유형코드명*/
        String rveDvCd, /*수납구분코드*/
        String rveDvCdNm, /*수납구분코드명*/
        String rveCd, /*수납코드*/
        String rveCdNm, /*수납코드명*/
        String dpDtm, /*입금일자*/
        String dpAmt, /*입금금액*/
        String cntrNo, /*계약번호*/
        String cntrSn, /*계약일련번호*/
        String pdCd, /*상품코드*/
        String pdNm /*상품명*/

    ) {}

    // *********************************************************
    // Result Dto
    // *********************************************************
    // 입금내역조회_엔지니어별(모바일) Result Dto
    @ApiModel("WwdbDepositItemizationDto-SearchDepositItemizationsByEngineersRes")
    public record SearchDepositItemizationsByEngineersRes(
        String prtnrNo, /*파트너번호*/
        String prtnrNm, /*파트너명*/
        String rveAmt /*수납금액*/
    ) {}
}
