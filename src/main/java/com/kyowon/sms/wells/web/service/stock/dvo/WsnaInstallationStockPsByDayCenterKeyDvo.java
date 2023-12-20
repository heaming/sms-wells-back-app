package com.kyowon.sms.wells.web.service.stock.dvo;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class WsnaInstallationStockPsByDayCenterKeyDvo {
    private String ogCd;
    private String pdCd;

    private int hashCode;

    public WsnaInstallationStockPsByDayCenterKeyDvo(String ogCd, String pdCd) {
        this.ogCd = ogCd;
        this.pdCd = pdCd;
        this.hashCode = Objects.hash(ogCd, pdCd);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        WsnaInstallationStockPsByDayCenterKeyDvo that = (WsnaInstallationStockPsByDayCenterKeyDvo)o;
        return ogCd == that.ogCd && pdCd == that.pdCd;
    }

    @Override
    public int hashCode() {
        return this.hashCode;
    }
}
