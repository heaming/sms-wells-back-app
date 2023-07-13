package com.kyowon.sms.wells.web.closing.expense.dvo;

import com.sds.sflex.system.config.annotation.DBDecField;
import com.sds.sflex.system.config.annotation.DBEncField;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WdcdSecuritiesDvo {
    private String requestAmount;
    private String opcsAdjSmryDvCd;
    private String ojApyCn;
    private String usrSmryCn;
    private String opcsCardUseIzId;    /*운영비카드사용내역ID*/
    private String useDtm;              /*사용일시*/
    private String dgr1LevlOgId;        /*(hidden)총괄단조직ID*/
    private String dgr1LevlOgNm;        /*총괄단명*/
    @DBEncField
    @DBDecField
    private String crcdnoEncr;            /*카드번호*/
    private String mrcNm;                 /*가맹점명*/
    private String mrcTobzNm;            /*가맹점업종명*/
    private String mrcAdrCn;             /*가맹점주소내용*/
    private String cardAprno;             /*카드승인번호*/
    private String domTrdAmt;               /*사용금액*/
    private String domTrdAmt1;               /*사용금액 테스트*/
    private String opcsAdjExcdYn;           /*운영비정산제외여부*/
    private String opcsAdjSmryDvNm;    /*운영비정산적요구분명*/
    private String mrcTobzCd;            /*(hidden)가맹점업종코드*/
    private String cdcoCd;                /*(hidden)카드사코드*/
    private String opcsAdjBtn;       /*원천세정산버튼*/
    private String adjCls;             /*정산여부*/
    private String dgr2LevlOgId;             /*(hidden)지역단ID*/
    private String domTrdSumAmt;
    private String adjOgId;
    private String opcsAdjNo; /*운영비정산번호*/
}
