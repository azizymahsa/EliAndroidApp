package com.reserv.myapplicationeli.models.model;

/**
 * Created by elham.bonyani on 1/22/2018.
 */

public class Error {


        private int Code;
        public final int getCode()
        {
            return Code;
        }
        public final void setCode(int value)
        {
            Code = value;
        }

        private String DetailedMessage;
        public final String getDetailedMessage()
        {
            return DetailedMessage;
        }
        public final void setDetailedMessage(String value)
        {
            DetailedMessage = value;
        }

        private String Language;
        public final String getLanguage()
        {
            return Language;
        }
        public final void setLanguage(String value)
        {
            Language = value;
        }

        private String Message;
        public final String getMessage()
        {
            return Message;
        }
        public final void setMessage(String value)
        {
            Message = value;
        }
    }


