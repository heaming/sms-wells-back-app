package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.common.dvo.*;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WctaReceiptBulkUploadMapper {
    boolean isExistProduct(String pdCd);

    boolean isExistServiceProduct(String pdCd);

    Optional<WctzPdBasDvo> selectPdBasByPk(String pdCd);

    String selectPspcCstIdForNewPspcCstBas();

    Optional<WctzPspcCstBasDvo> selectPspcCstBasByPk(String pspcCstId);

    int insertPspcCstBas(WctzPspcCstBasDvo dvo);

    String selectPspcCstCnslIdForNewPspcCstCnslBas();

    int insertPspcCstCnslBas(WctzPspcCstCnslBasDvo dvo);

    int insertPspcCstCnslRcmdIz(WctzPspcCstCnslRcmdIzDvo dvo);

    boolean isExistCstBas(WctzCstBasDvo basDvo);

    int insertProspectCustomers(List<WctaBulkProspectCustomerDvo> dvos);

    Optional<WctaRentalFinalPriceDvo> selectRentalPdPrcFnlDtl(WctaRentalFinalPriceDvo req);

    Optional<WctzMmPrtnrIzDvo> selectAlncmpDgPrtnr(String alncmpDgPrtnrMapngCd, String alncmpDgPrtnrOgTpCd);

    String selectCntrPrtnrRelIdForNewCntrPrtnrRel();

    String selectCntrCstRelIdForNewCntrCstRel();

    String selectCntrPdRelIdForNewCntrPdRel();

    int insertBulkRentals(List<WctaBulkRentalDvo> dvos);

    List<WctzPdRelDvo> selectPdRels(String basePdCd);

    List<WctzPdRelDvo> selectPdRels(String basePdCd, String pdRelTpCd);
}
