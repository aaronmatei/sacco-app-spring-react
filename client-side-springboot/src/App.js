import React, {Component} from 'react';
import {Router,Route,Switch} from 'react-router-dom'
import {createBrowserHistory} from 'history'
import LoginPage from './pages/login/Login.page'
import RegisterPage from './pages/register/Register.page'
import ProfilePage from './pages/profile/Profile.page'
import LoanDetailPage from './pages/loanDetail/Loan.Detail.Page'
import HomePage from './pages/home/Home.page'
import Navbar from './components/navbar'






class App extends Component {
  constructor(props) {
    super(props) 
    this.state = {
      history: createBrowserHistory(),

    } 
  
  }



  render(){
    const {history} = this.state
    
    return (
      <Router history={history}>
        <div>  
          <Navbar />
          <div className="container">
            <Switch>
              <Route exact path="/" component={HomePage}/>
              <Route exact path="/home" component={HomePage} />
              <Route exact path="/login" component={LoginPage} />
              <Route exact path="/register" component={RegisterPage} />
              <Route exact path="/profile" component={ProfilePage} />
              <Route exact path="/detail/:id" component={LoanDetailPage} />

            </Switch>
          </div>
        </div>

      </Router>
    );
  }
}

export default App;
