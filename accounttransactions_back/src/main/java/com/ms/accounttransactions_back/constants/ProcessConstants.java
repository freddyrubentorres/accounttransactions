package com.ms.accounttransactions_back.constants;

/**
 * @author : Freddy Torres
 * file :  ProcessConstants
 * @since : 3/4/2025, jue
 **/

public class ProcessConstants {
    //CLIENTS
    public static final String API_PATH = "/api";
    public static final String API_PATH_CLIENTS = API_PATH+"/clients";
    public static final String API_PATH_CLIENTS_BY_IDENTIFICACION = "/identification/{identification}";
    public static final String API_PATH_CLIENTS_PATCH_ID = "/{id}";
    //ACCOUNTS
    public static final String API_PATH_ACCOUNTS = API_PATH+"/accounts";
    public static final String TRANSACTION_ACCOUNT_OPENING = "APERTURA DE CUENTA";
    public static final String API_PATH_ACCOUNTS_BY_ACCOUNTNUMBER = "/accountNumber/{accountNumber}";
    public static final String API_PATH_ACCOUNTS_PUT_ACCOUNTNUMBER = "/{accountNumber}";
    //TRANSACTION
    public static final String API_PATH_TRANSACTION = API_PATH+"/transactions";
    public static final String MSG_INSUFFICIENT_BALANCE="Saldo no disponible.";
    //REPORTS
    public static final String API_PATH_REPORTS = API_PATH+"/reports";
}
