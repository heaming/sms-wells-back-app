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
public class WdcbSlCnfmBasDvo {
    private String cntrNo; /*계약번호*/
    private int cntrSn; /*계약일련번호*/
    private String slRcogDt; /*매출인식일자*/
    private int slCnfmSn; /*매출확정일련번호*/
    private String kwGrpCoCd; /*교원그룹회사코드*/
    private String bzHdqDvCd; /*사업본부구분코드*/
    private String ogTpCd; /*조직유형코드*/
    private String prtnrNo; /*파트너번호*/
    private String pdHclsfId; /*상품대분류ID*/
    private String pdMclsfId; /*상품중분류ID*/
    private String pdLclsfId; /*상품소분류ID*/
    private String pdCd; /*상품코드*/
    private String dgCstId; /*대표고객ID*/
    private String cstNo;/*고객번호*/
    private String copnDvCd;/*법인격구분코드*/
    private String bzrno;/*사업자등록번호*/
    private String sellTpCd;/*판매유형코드*/
    private String sellTpDtlCd;/*판매유형상세코드*/
    private String sellInflwChnlDtlCd;/*판매유입채널상세코드*/
    private String sapPdDvCd; /*SAP상품구분코드*/
    private int sellQty; /*판매수량*/
    private int sellAmt; /*판매금액*/
    private int sellAmtVat; /*판매금액부가가치세*/
    private int sellSplAmt; /*판매공급금액*/
    private int cntrTam; /*계약총액*/
    private int subscAmt; /*청약금액*/
    private int rentalRgstCost; /*렌탈등록비*/
    private int rentalRgstCostVat; /*렌탈등록비부가가치세*/
    private int rentalAmt; /*렌탈금액*/
    private int rentalDscAmt; /*렌탈할인금액*/
    private int rentalPtrm; /*렌탈기간*/
    private int rentalTn; /*렌탈회차*/
    private int istmPcamAmt; /*할부원금금액*/
    private int istmFeeLvyAmt; /*할부수수료부과금액*/
    private int istmAmt; /*할부금액*/
    private int istmMcn; /*할부개월수*/
    private int mmIstmAmt; /*월할부금액*/
    private int dscAmt; /*할인금액*/
    private int cntramDpAmt; /*계약금입금금액*/
    private int bilDscAmt; /*청구할인금액*/
    private int ovrCtrDpAmt; /*초과조정입금금액*/
    private int prmTn; /*선납회차*/
    private int totPrmAmt; /*총선납금액*/
    private int prmExpAmt; /*선납예정금액*/
    private String prmStrtY; /*선납시작년도*/
    private String prmStrtMm; /*선납시작월*/
    private String prmEndY; /*선납종료년도*/
    private String prmEndMm; /*선납종료월*/
    private int prmMcn; /*선납개월수*/
    private int prmDscr; /*선납할인율*/
    private int prmDscAmt; /*선납할인금액*/
    private int prmDpAmt; /*선납입금금액*/
    private int prmRfndAmt; /*선납환불금액*/
    private int prmRplcAmt; /*선납대체금액*/
    private int prmSlAmt; /*선납매출금액*/
    private int nomSlAmt; /*정상매출금액*/
    private int spmtSlAmt; /*추가매출금액*/
    private int nomDscAmt; /*정상할인금액*/
    private int spmtDscAmt; /*추가할인금액*/
    private int slCtrAmt; /*매출조정금액*/
    private int slCanAmt; /*매출취소금액*/
    private String slStpYn; /*매출중지여부*/
    private String cntrStlmFshDtm; /*계약결제완료일시*/
    private String cntrStrtdt; /*계약시작일자*/
    private String canDt; /*취소일자*/
    private int slDc; /*매출일수*/
    private int svAmt; /*서비스금액*/
    private int nomIntAmt; /*정상이자금액*/
    private int intVat; /*이자부가가치세*/
    private int mlgSlAmt; /*마일리지매출금액*/
    private String ostrDtm; /*출고일시*/
    private String sppDtm; /*배송일시*/
    private String istDtm; /*설치일시*/
    private String reqdDtm; /*철거일시*/
    private String svDt; /*서비스일자*/
    private int pvdaOjPcam; /*현재가치할인차금대상원금*/
    private int pvdaAmt; /*현재가치할인차금금액*/
    private String slRcogClsfCd; /*매출인식분류코드*/
    private String slRcogDvCd; /*매출인식구분코드*/
    private String lgstSppMthdCd; /*물류배송방식코드*/
    private String lgstItmGdCd; /*물류품목등급코드*/
    private int reimPcsvCs; /*변상택배비용*/
    private int pcsvReimAmt; /*택배변상금액*/
    private String iostWareCd; /*입출고창고코드*/
    private String sapMatEvlClssVal; /*SAP자재평가클래스값*/
    private String sapSlTpCd; /*SAP매출유형코드*/
    private String sapBizDvCd; /*SAP업무구분코드*/
    private String sapBzHdqInfCd; /*SAP사업본부정보코드*/
    private int slAmt; /*매출금액*/
    private int vat; /*부가가치세*/
    private String iostDt; /*입출고일자*/
    private String ctrlOrdTpCd;
    private String sapMatCd; /*SAP자재코드*/
    private int slQty; /*매출수량*/
    private String rtngdYn; /*반품여부*/
    private String frisuYn; /*무상여부*/
    private String cscnCd; /*코스트센터코드*/
    private String wbsCd; /*WBS코드*/
    private String sapPurpMatCd; /*SAP목적자재코드*/
    private String fgptYn; /*사은품여부*/
    private String sapTxnDtfrCd; /*SAP과세면세코드*/
    private String sapTxinvPblBaseCd; /*SAP세금계산서발행기준코드*/
    private String rvpyYn; /*수불여부*/
    private String saveGdsYn; /*저장물품여부*/
    private String sapPlntCd; /*SAP플랜트코드*/
    private String sapSaveLctVal; /*SAP저장위치값*/
    private String slpAkNo; /*매출전표요청번호*/
    private String sapTrsDt; /*SAP전송일자*/
    private String sapRfdt; /*SAP반영일자*/
    private String sapSlpno; /*SAP전표번호*/
    private String sapSlipMsg; /*SAP전표메시지*/
    private String slipSlDt; /*전표매출일자*/
    private String slipPblPrdCd; /*전표발행주기코드*/
    private String dtaDlYn; /*데이터삭제여부*/
}
