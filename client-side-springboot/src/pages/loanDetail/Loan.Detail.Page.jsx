import React, { Component } from 'react'
import loanService from '../../services/loan.service'

class LoanDetail extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            loan: JSON.parse(localStorage.getItem("currentLoan")),
            users: []
        }
    }

    componentDidMount(){
        this.findUsersWithLoan()
    }

    findUsersWithLoan(){
        loanService.findUsersWithLoan(this.state.id).then(users => {
            this.setState({users: users})
        })
    }


    render() {
        const {users} = this.state
        return (
            <div className="col-md-12">
                <div className="jumbotron">
                    <h1 className="display-4">Loan ID: {this.state.id}</h1>
                    <h1 className="display-4">Loan of type: {this.state.loan.type}</h1>
                    {users.length &&
                        <table className="table table-striped">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Username</th>                                   
             
                                </tr>
                            </thead>
                            <tbody>
                                {users.map((user, index) => (
                                    <tr key={user.id}>
                                        <th scope="row">{index + 1}</th>
                                        <td>{user}</td>                                                    
                                    </tr>
                                ))
                                }
                            </tbody>

                        </table>


                    }
                </div>                
            </div>
        )
    }
}

export default LoanDetail
