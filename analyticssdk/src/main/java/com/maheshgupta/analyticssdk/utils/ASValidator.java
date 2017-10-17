package com.maheshgupta.analyticssdk.utils;

import android.content.Context;

import com.maheshgupta.analyticssdk.utils.ASError;

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

    public static boolean userIdValidator(String user_id) {
        if (user_id == null) {
            throwException(ASError.user_id_error);
            return false;
        }
        return true;
    }

    public static void throwException(String exception) {
        throw new IllegalStateException(exception);
    }

}
