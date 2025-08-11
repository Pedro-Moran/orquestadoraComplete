package com.bbva.pfmh.dto.fmc7.constant;

public class PFMHC005Constant {

    private PFMHC005Constant() {
        throw new IllegalStateException("Utility class PFMHC005Constant");
    }

    public static final class StateRollback {
        private StateRollback() {
            throw new IllegalStateException("Utility class StateRollback");
        }

        public static final String NO_ROLLBACK_ERROR = "ENR";
        public static final String YES_ROLLBACK_ERROR = "EWR";
        public static final String OK_SUCCESSFUL_TRANSACTION = "OK";
    }

    public static final class HostProperties {
        private HostProperties() {
            throw new IllegalStateException("Utility class HostProperties");
        }

        public static final String FFMM7 = "FMC7";
        public static final String CICS_KEY_CONNECTION = "cicsConnection";
        public static final String FMC7 = "FMC7";
    }

    public static final class HostFMC7FieldsIn {
        private HostFMC7FieldsIn() {
            throw new IllegalStateException("Utility class HostFMC7FieldsIn");
        }

        public static final String NUMCLIE = "NUMCLIE";
        public static final String INDPAGI = "INDPAGI";
        public static final String TAMPAGI = "TAMPAGI";
    }

    public static final class HostFMC7FieldsOut {
        private HostFMC7FieldsOut() {
            throw new IllegalStateException("Utility class HostFMC7FieldsOut");
        }

        public static final String IDCONTR = "IDCONTR";
        public static final String NUMCUOT = "NUMCUOT";
        public static final String SALDISP = "SALDISP";
        public static final String DMONESD = "DMONESD";
        public static final String IDSUBPR = "IDSUBPR";
        public static final String DSUBPRO = "DSUBPRO";
        public static final String IDMONFN = "IDMONFN";
        public static final String SALCONT = "SALCONT";
        public static final String VALCUOT = "VALCUOT";
        public static final String CTIPNUM = "CTIPNUM";
        public static final String DTIPNUM = "DTIPNUM";
    }
}
