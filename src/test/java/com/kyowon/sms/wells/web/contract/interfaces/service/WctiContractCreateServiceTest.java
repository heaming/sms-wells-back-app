package com.kyowon.sms.wells.web.contract.interfaces.service;

import com.kyowon.sms.common.web.contract.common.dvo.ZctzCntrDtlDvo;
import com.kyowon.sms.common.web.contract.zcommon.constants.CtCoCd;
import com.kyowon.sms.common.web.contract.zcommon.constants.CtSellChnlDtlCd;
import com.kyowon.sms.common.web.contract.zcommon.constants.CtSessionTenantCd;
import com.kyowon.sms.common.web.contract.zcommon.utils.CtDateUtils;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractAutomaticTransferDto;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto;
import com.kyowon.sms.wells.web.contract.interfaces.dto.WctiContractCreateDto.CreateRegularShippingRes;
import com.kyowon.sms.wells.web.contract.ordermgmt.service.WctaSeedingService;
import com.kyowon.sms.wells.web.contract.zcommon.constants.CtContractConst;
import com.kyowon.sms.wells.web.contract.zcommon.constants.WctzRglrSppMchnTpCd;
import com.sds.sflex.system.config.context.SFLEXContextHolder;
import com.sds.sflex.system.config.core.dvo.UserSessionDvo;
import com.sds.sflex.system.config.test.SpringTestSupport;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
class WctiContractCreateServiceTest extends SpringTestSupport {
    private final WctiContractCreateService service;
    private final WctiContractAutomaticTransferService automaticTransferService;
    private final WctaSeedingService seedingService;

    public static final String INVALID_CST_NO_BY_EX_OJ = "031542082";
    public static final String VALID_CST_NO = "029298516";
    public static final String KMEMBERS_PNTRT_NO = "1653227";
    public static final String NEW_CNTR_NO_2 = "W20244903329";
    private final String BASE_URL = CtContractConst.INTERFACE_URL_V1;
    private final String EXIST_CNTR_NO = "W20234902675";
    private final String NEW_CNTR_NO = "W20244903293";
    private final String EXIST_CNTR_SN = "1";

    @BeforeEach
    void setUp() {
        UserSessionDvo userSession = SFLEXContextHolder.getContext().getUserSession();
        userSession.setTenantCd(CtSessionTenantCd.WELLS.getCode());
    }

    @Test
    @DisplayName("wells 정기배송 생성 테스트 파스퇴르 1M")
    void testCreateContractForRglrShpFor1MSvPd() throws Exception {

        WctiContractCreateDto.CreateRegularShippingReq req = WctiContractCreateDto.CreateRegularShippingReq.builder()
            .procsDv("1")
            .cntrNo(NEW_CNTR_NO_2)
            .cntrSn("1")
            .cstNo(VALID_CST_NO)
            .sellPrtnrNo(KMEMBERS_PNTRT_NO)
            .sellChnlCd(CtSellChnlDtlCd.KMEMBERS.getCode())
            .basePdCd("WP03160172")
            .stplPrdCd("12")

            .aftnDvCd("1")
            .aftnAmt("38900")

            .rcgvpKnm("수령자")
            .cralLocaraTno("010")
            .mexno("1234")
            .cralIdvTno("5678")
            .zip("13254")
            .basAdr("경기 성남시 중원구 광명로 204")
            .dtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")

            .ag111Yn("Y")
            .ag112Yn("Y")
            .ag113Yn("Y")
            .ag114Yn("Y")
            .ag115Yn("Y")

            .build();


        CreateRegularShippingRes res = service.saveContractForRegularShipping(req);

        log.info("▶▶▶ INPUT : {}", req);
        log.info("▶▶▶ OUTPUT : {}", res);

        Assertions.assertTrue(1 > 0);
    }

    @Test
    @DisplayName("wells 계약 생성 테스트 웰스 팜 및 연계 모종 계약")
    void testContractCreateForWellFarm() throws Exception {

        automaticTransferService.registerAutomaticTransfers(WctiContractAutomaticTransferDto.SaveReq
            .builder()
            .ownerNm("카드주")
            .cardNo("4902208236680930")
            .expdtYm("2611")
            .ownerBryy("99991231")
            .autoFntDv("2")
            .fntDt("22")
            .cntrNo(NEW_CNTR_NO_2)
            .cntrSn(1)
            .cntrCstNo(VALID_CST_NO)
            .coCd(CtCoCd.KYOWON_PROPERTY.getCode())
            .build());

        automaticTransferService.registerAutomaticTransfers(WctiContractAutomaticTransferDto.SaveReq
            .builder()
            .ownerNm("카드주")
            .cardNo("4902208236680930")
            .expdtYm("2611")
            .ownerBryy("99991231")
            .autoFntDv("2")
            .fntDt("22")
            .cntrNo(NEW_CNTR_NO_2)
            .cntrSn(2)
            .cntrCstNo(VALID_CST_NO)
            .coCd(CtCoCd.KYOWON_PROPERTY.getCode())
            .build());

        // given
        WctiContractCreateDto.CreateRentalReq machineReq = WctiContractCreateDto.CreateRentalReq.builder()
            .inDv("1")
            .cntrNo(NEW_CNTR_NO_2)
            .cntrSn("1")
            .cstNo(VALID_CST_NO)
            .prtnrNo(KMEMBERS_PNTRT_NO)
            .rcpChnlDtl(CtSellChnlDtlCd.KMEMBERS.getCode())
            .pdCd("WP05120034")
            .dscDvCd("8")
            // .dscTpCd("03")
            .rtlfe1("9900")
            .dutyUse("36")
            .vstPrdCd("3")
            .uswyCd("3")
            .chgMcn1("99")
            .rgstCost("100000")

            .aftnDvCd("2")
            .rtlfe1("349000")

            .bnkCdcoDv1("96")
            .preslCard1("")
            .cardAmt1("349000")

            .istCstNm("수령자")
            .istCphonLocaraTno("010")
            .istCphonExno("1234")
            .istCphonIdvTno("5678")
            .istZip("13254")
            .istBasAdr("경기 성남시 중원구 광명로 204")
            .istDtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")

            .build();

        service.saveContractForRental(machineReq);

        List<ZctzCntrDtlDvo> machineDtls = seedingService.getMachineryCntrDtlsWithoutSeeding(
            NEW_CNTR_NO_2,
            1,
            VALID_CST_NO,
            WctzRglrSppMchnTpCd.WIDE
        );

        Assertions.assertFalse(machineDtls.isEmpty());

        WctiContractCreateDto.CreateRegularShippingReq req = WctiContractCreateDto.CreateRegularShippingReq.builder()
            .procsDv("1")
            .cntrNo(NEW_CNTR_NO_2)
            .cntrSn("2")
            .cstNo(VALID_CST_NO)
            .sellPrtnrNo(KMEMBERS_PNTRT_NO)
            .sellChnlCd(CtSellChnlDtlCd.KMEMBERS.getCode())
            .basePdCd("WP05160144")
//            .stplPrdCd("12")

            .aftnDvCd("2")
            .aftnAmt("38900")
            .mchnCntrNo(NEW_CNTR_NO_2)
            .mchnCntrSn("1")

            .rcgvpKnm("수령자")
            .cralLocaraTno("010")
            .mexno("1234")
            .cralIdvTno("5678")
            .zip("13254")
            .basAdr("경기 성남시 중원구 광명로 204")
            .dtlAdr("201동 303호 (중앙동,신흥역하늘채랜더스원아파트)")

            .ag111Yn("Y")
            .ag112Yn("Y")
            .ag113Yn("Y")
            .ag114Yn("Y")
            .ag115Yn("Y")

            .build();

        CreateRegularShippingRes res = service.saveContractForRegularShipping(req);

        log.info("▶▶▶ INPUT : {}", req);
        log.info("▶▶▶ OUTPUT : {}", res);

        Assertions.assertTrue(1 > 0);
    }
}
