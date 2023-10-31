package com.kyowon.sms.wells.web.closing.sales.dvo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * <pre>
 * 매출확정생성 서비스 WELLS매출확정기본 Table DVO
 * </pre>
 *
 * @author WOO SEUNG MIN
 * @since 2023-06-22
 */
@Getter
@Setter
@ToString
public class WdcbSapSalesCompulsionCreateDvo {
    private String baseYm; /*매출월*/
    private String zsslrq; /*매출전표요청번호*/
    private String zsslsq; /*일련번호*/
    private String zsiodt; /*입출고일자*/
    private String zsslty; /*매출유형*/
    private String zsorty; /*주문유형*/
    private String zsmtrl; /*SAP자재코드*/
    private String zsmtrlNm; /*자재명*/
    private Integer zsqntt; /*수량*/
    private Integer zssamt; /*공급가*/
    private Integer zsvamt; /*VAT*/
    private String zsrtrn; /*반품여부*/
    private String zsfree; /*무상여부*/
    private String zsprft; /*사은품여부*/
    private String zstaxg; /* 과/면세구분*/
    private String zsblty; /*세금계산서발행기준*/
    private String zsmngr; /*담당자코드*/
    private String zsreal; /*수불여부*/
    private String zssasl; /*저장위치*/
    private String lggubn; /*구분*/
    private String lgmgub; /*매출구분*/
    private String lgjdte; /*입력일*/
    private String transYn; /*전송여부*/
    private String zsifdt; /*IF전송일*/
    private String zssadt; /*SAP반영일*/
    private String zsslhq; /*사업부서*/
    private String zsdept; /*조직정보*/
    private String zscust; /*고객정보ID*/
    private String updActDt; /*수정일시*/
    private String updActId; /*수정자*/
    private String regActDt; /*등록일시*/
    private String regActId; /*등록자*/
    private String orglFnlMdfcDtm; /*최초수정시간*/
}
