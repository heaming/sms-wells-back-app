package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * W-SV-U-0279M01 재고마스터갱신
 * </pre>
 *
 * @author hyewon.kim 김혜원
 * @since 2023.03.13
 */
@Setter
@Getter
public class WsnaStockMasterRenewalDvo {

    String baseYm;
    String wareNo;
    String pdCd;

    // 기초재고 갱신용
    Integer pitmQtyA;
    Integer pitmQtyE;
    Integer pitmQtyR;
    Integer pitmQtyB;

    // 월별재고 갱신용
    Integer chekQtyA;
    Integer chekQtyB;
    Integer chekQtyE;
    Integer chekQtyR;
    Integer buyA;
    Integer buyB;
    Integer normIA;
    Integer normIE;
    Integer normIR;
    Integer normIB;
    Integer alloIA;
    Integer alloIE;
    Integer alloIR;
    Integer alloIB;
    Integer transIA;
    Integer transIE;
    Integer transIR;
    Integer transIB;
    Integer retIiA;
    Integer retIiE;
    Integer retIiR;
    Integer retIiB;
    Integer retEiA;
    Integer retEiE;
    Integer retEiR;
    Integer retEiB;
    Integer etcIA;
    Integer etc1IA;
    Integer etcIE;
    Integer etc1IE;
    Integer etc1IR;
    Integer etc1IB;
    Integer normOA;
    Integer normOE;
    Integer normOR;
    Integer normOB;
    Integer alloOA;
    Integer alloOE;
    Integer alloOR;
    Integer alloOB;
    Integer transOA;
    Integer transOE;
    Integer transOR;
    Integer transOB;
    Integer retIoA;
    Integer retIoE;
    Integer retIoR;
    Integer retIoB;
    Integer retEoA;
    Integer retEoE;
    Integer retEoR;
    Integer retEoB;
    Integer saleOA;
    Integer saleOB;
    Integer disOA;
    Integer disOE;
    Integer disOR;
    Integer disOB;
    Integer etcOA;
    Integer etcOE;
    Integer etcOR;
    Integer etcOB;
    Integer refurOA;
    Integer refurOE;
    Integer refurOR;
    Integer refurOB;
    Integer etc1OA;
    Integer etc1OE;
    Integer etc1OR;
    Integer etc1OB;
    Integer wrkOA;
    Integer wrkOE;
    Integer wrkOR;
    Integer wrkOB;

    // 시점재고 갱신용
    String itmPdCd;
    Integer pitmStocAGdQty;
    Integer pitmStocEGdQty;
    Integer pitmStocRGdQty;
    Integer pitmStocBGdQty;

    // 이동재고 갱신용
    Integer buffQtyA;
    Integer buffQtyE;
    Integer buffQtyR;
    Integer buffQtyB;

}
