import React, { useState, useEffect } from 'react'
import userService from '../../services/user.service'
import loanService from '../../services/loan.service'
import User from '../../models/user'
import Transaction from '../../models/transaction'



const HomePage =(props)=>{
   

    const [loans, setLoans] = useState([])
    const [loading, setLoading] = useState(false)
    const [errorMessage, setErrorMessage] = useState("")
    const [infoMessage, setInfoMessage] = useState("")
    const [currentUser, setCurrentUser] = useState(new User())
 

    useEffect(()=>{
        userService.currentUser.subscribe(data => {
            setCurrentUser(data)           
        })
        getLoans()
      
                       
    }, []) 

    function getLoans(){        
        loanService.findAllLoans().then(res =>{
        let data = res.data
        console.log(data)
        setLoans(data)   

        }, error =>{
            console.log(error);
        })
           
        
    }

       
    

    function transact(loan){
        if(!currentUser){
            setErrorMessage("To borrow a loan, you MUST sign in")
            return
        }
        var transaction = new Transaction(currentUser.id, loan)
        loanService.createTransaction(transaction).then(data => {
            setInfoMessage("You have applied for a loan. We will get back to you....")
            console.log(data.data);

        }, error => {
            setErrorMessage("Unexpected error occurred.")
            console.log(error);

        })
    }

    function detail(loan){
        localStorage.setItem("currentLoan", JSON.stringify(loan))
        props.history.push("/detail/"+loan.id)
    }

  
        return (
            <div className="col-md-12">
                {infoMessage && 
                <div className="alert alert-success">
                    <strong>Successful!</strong> {infoMessage}
                    <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>

                    </button>
                </div>
                
                }
                {errorMessage &&
                    <div className="alert alert-danger">
                        <strong>Error!</strong> {errorMessage}
                        <button type="button" className="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true">&times;</span>

                        </button>
                    </div>

                }

                {loading && <em>Loading Data.....</em>}
                {loans.length &&
                    <table className="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Loan Type</th>
                                <th scope="col">Date Issued</th>
                                <th scope="col">Loan Amount</th>
                                <th scope="col">Repayment Period</th>
                                <th scope="col">Loan Purpose</th>
                                <th scope="col">Details</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>

                            {loans.map((loan, index)=>(

                                     <tr key={loan.id}>
                                        <th scope="row">{index+1}</th>
                                        <td>{loan.type}</td>
                                        <td>{loan.dateIssued}</td>
                                        <td>{loan.amount}</td>
                                        <td>{loan.period}</td>
                                        <td>{loan.purpose}</td>
                                        <td>
                                            <button className="btn btn-info" onClick={() => detail(loan)}>View</button>
                                        </td>
                                        <td>
                                            <button className="btn btn-info" onClick={() => transact(loan)}>Borrow</button>
                                        </td>

                                    </tr>




                            ))}
                            
                           

                          
                        </tbody>

                    </table>


                }
                
            </div>
        )
    }


export default HomePage
