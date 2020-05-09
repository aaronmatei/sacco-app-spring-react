import axios from 'axios'
let API_URL = "http://localhost:8765/api/loan/service/"


class LoanService{
    createTransaction(transaction){
        return axios.post(API_URL + "transact", JSON.stringify(transaction),
        {headers: {"Content-Type":"application/json; charset=UTF-8"}})
    }

    filterTransactionPerUser(userId){
        return axios.get(API_URL+"user/"+userId,
            { headers: { "Content-Type": "application/json; charset=UTF-8" } }
        )
    }
    findUsersWithLoan(loanId){
        return axios.get(API_URL + "loan/" + loanId,
            { headers: { "Content-Type": "application/json; charset=UTF-8" } }
        )
    }

    findAllLoans = () => {
        return axios.get(API_URL + "all",
            { headers: { "Content-Type": "application/json; charset=UTF-8" } }
        )
    }




}
export default new LoanService()