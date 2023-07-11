package com.kyowon.sms.wells.web.contract.ordermgmt.mapper;

import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPdBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslBasDvo;
import com.kyowon.sms.wells.web.contract.ordermgmt.dvo.WctaPspcCstCnslRcmdIzDvo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface WctaReceiptBulkUploadMapper {
    boolean isExistProduct(String pdCd);

    boolean isExistServiceProduct(String pdCd);

    Optional<WctaPdBasDvo> selectPdBasByPk(String pdCd);

    String selectPspcCstIdForNewPspcCstBas();

    Optional<WctaPspcCstBasDvo> selectPspcCstBasByPk(String pspcCstId);

    int insertPspcCstBas(WctaPspcCstBasDvo dvo);

    String selectPspcCstCnslIdForNewPspcCstCnslBas();

    int insertPspcCstCnslBas(WctaPspcCstCnslBasDvo dvo);

    int insertPspcCstCnslRcmdIz(WctaPspcCstCnslRcmdIzDvo dvo);
}
