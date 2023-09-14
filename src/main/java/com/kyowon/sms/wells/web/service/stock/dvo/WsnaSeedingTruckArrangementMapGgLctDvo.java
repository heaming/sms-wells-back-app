package com.kyowon.sms.wells.web.service.stock.dvo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * K-W-SV-U-0302M01 모종 출하대차 MAP dvo
 * </pre>
 *
 * @author heymi.cho
 * @since 2023-09-07
 */

@Getter
//@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(cacheStrategy = EqualsAndHashCode.CacheStrategy.LAZY)
public class WsnaSeedingTruckArrangementMapGgLctDvo {
    private final String ggLctCd;
    private final String ggLctNm;
    //    private List<WsnaSeedingTruckArrangementMapSeedDvo> seeds;
    //    private int hashCode;

    //    public WsnaSeedingTruckArrangementMapGgLctDvo(String ggLctCd, String ggLctNm) {
    //        this.ggLctCd = ggLctCd;
    //        this.ggLctNm = ggLctNm;
    //        this.hashCode = Objects.hash(ggLctCd, ggLctNm);
    //    }

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o)
    //            return true;
    //        if (o == null || getClass() != o.getClass())
    //            return false;
    //        WsnaSeedingTruckArrangementMapGgLctDvo that = (WsnaSeedingTruckArrangementMapGgLctDvo)o;
    //        return ggLctCd == that.ggLctCd && ggLctNm == that.ggLctNm;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return this.hashCode;
    //    }
}
