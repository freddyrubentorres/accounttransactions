export const environment = {
    production: false,
    /*clients*/
    urlPostClients: 'http://localhost:8080/accounttransactions_back/api/clients',
    urlGetClients: 'http://localhost:8080/accounttransactions_back/api/clients/identification/',
    urlPatchClients: 'http://localhost:8080/accounttransactions_back/api/clients/identification/',
    /*accounts*/
    urlPostAccounts: 'http://localhost:8080/accounttransactions_back/api/accounts',
    urlGetAccounts: 'http://localhost:8080/accounttransactions_back/api/accounts/identification/',
    urlPatchAccounts: 'http://localhost:8080/accounttransactions_back/api/accounts/accountNumber/',
    /*transactions*/
    urlPostTransactions: 'http://localhost:8080/accounttransactions_back/api/transactions',
    /*reports*/
    urlGetReports: 'http://localhost:8080/accounttransactions_back/api/reports'
};