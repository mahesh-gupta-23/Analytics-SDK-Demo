package com.maheshgupta.analyticssdk;

import android.content.Context;

public class ASValidator {

    public static boolean initializationValidator(Context context, String api_key) {
        if (context == null) {
            throwException(ASError.context_error);
            return false;
        } else if (api_key == null) {
            throwException(ASError.api_error);
            return false;
        }

        return true;
    }

    static void throwException(String exception) {
        throw new IllegalStateException(exception);
    }

}
