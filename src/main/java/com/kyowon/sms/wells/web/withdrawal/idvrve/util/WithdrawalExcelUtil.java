package com.kyowon.sms.wells.web.withdrawal.idvrve.util;

public class WithdrawalExcelUtil {
    public static String formatObjToString(final Object obj) {
        if (obj == null) {
            return null;
        } else if (obj instanceof String) {
            return obj.toString();
        } else if (obj instanceof Double) {
            return String.format("%d", ((Double)obj).intValue());
        }
        return null;
    }
}
