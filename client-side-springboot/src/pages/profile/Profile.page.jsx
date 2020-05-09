import React, { Component } from 'react'
import UserService from '../../services/user.service'
import loanService from '../../services/loan.service'

class ProfilePage extends Component {

    constructor(props) {
        super(props)
        if(!UserService.currentUserValue){
            this.props.history.push("/")
            return
        }

        this.state = {
            user: UserService.currentUserValue,
            transactions: []
        }
        
    }

    componentDidMount(){
        this.setState({
            transactions: {loading: true}
        })

        const user = this.state.user
        loanService.filterTransactionPerUser(user.id).then(transactions => {
            this.setState({transactions: transactions.data})
        })
    }

    render() {
        const {user, transactions} = this.state
        return (
            <div className="col-md-12">
                <div className="jumbotron">
        <h1 className="display-4">Hello, {user.username}</h1>

                </div>
                {transactions.loading && <em>Loading transactions.....</em>}
                {transactions.length && 
                <table className="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Loan Type</th>
                            <th scope="col">Date Issued</th>
                            <th scope="col">Loan Amount</th>
                            <th scope="col">Repayment Period</th>
                            <th scope="col">Loan Purpose</th>
                        </tr>
                    </thead>
                    <tbody>
                        { transactions.map((transaction, index) => (
                            <tr key={transaction.id}>
                                <th scope="row">{index + 1}</th>
                                <td>{transaction.loan.type}</td>
                                <td>{transaction.loan.dateIssued}</td>
                                <td>{transaction.loan.amount}</td>
                                <td>{transaction.loan.period}</td>
                                <td>{transaction.loan.purpose}</td>
                                
                            </tr> 

                        ))
                     
                        }
                    </tbody>

                </table>
                
                
                }
                
            </div>
        )
    }
}

export default ProfilePage
