import React, {useState, useEffect} from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { Link } from 'react-router-dom'
import { createBrowserHistory } from 'history'
import {
    faUser,
    faUserPlus,
    faSignInAlt,
    faHome,
    faSignOutAlt,
    faBars
} from '@fortawesome/free-solid-svg-icons'

import User from '../models/user'
import userService from '../services/user.service'

function Navbar(props){
    const [history, setHistory] = useState(createBrowserHistory())
    const [currentUser, setcurrentUser] = useState(new User())
    const [errorMessage, seterrorMessage] = useState("")

    useEffect(()=>{
        userService.currentUser.subscribe(data => {
            setcurrentUser(data)
        })  
    },[])

    function logout(){
        userService.logout().then(data => {
            history.push("/home")
        }, error => {
                seterrorMessage("Unexpected error occured")          
        })
    }
    
        return (
            <div>
              
                
                    <nav className="navbar navbar-expand navbar-light">
                     
                        <div className="navbar-nav mr-auto">
                        <Link class="navbar-brand" to="/">
                            <img src="/images/logo4.png" width="120" height="60" alt="logo" />
                        </Link>            
                        </div>
                        <div className="navbar-nav mr-auto">
                            <ul className="navbar-nav" >
                                <li class="nav-item dropdown">
                                    <Link className="nav-link dropdown-toggle" to="#" id="navbardrop" data-toggle="dropdown">
                                        Products & Services |
                  </Link>
                                    <div className="dropdown-menu">
                                        <Link className="dropdown-item" to="#">Membership</Link>
                                        <Link className="dropdown-item" to="#">Savings</Link>
                                        <Link className="dropdown-item" to="#">Credit</Link>
                                        <Link className="dropdown-item" to="#">Banking</Link>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <Link className="nav-link dropdown-toggle" to="#" id="navbardrop" data-toggle="dropdown">
                                        Resource centre |
                  </Link>
                                    <div className="dropdown-menu">
                                        <Link className="dropdown-item" to="#">Downloads</Link>
                                        <Link className="dropdown-item" to="#">Tenders</Link>
                                        <Link className="dropdown-item" to="#">Payment modes</Link>
                                        <Link className="dropdown-item" to="#">FAQs</Link>
                                    </div>
                                </li>
                                <li class="nav-item dropdown">
                                    <Link className="nav-link dropdown-toggle" to="#" id="navbardrop" data-toggle="dropdown">
                                        Reports |
                  </Link>
                                    <div className="dropdown-menu">
                                        <Link className="dropdown-item" to="#">Financial</Link>
                                        <Link className="dropdown-item" to="#">R2</Link>
                                        <Link className="dropdown-item" to="#">R3</Link>
                                        <Link className="dropdown-item" to="#">R4</Link>
                                    </div>
                                </li>

                                <li className="nav-item">
                                    <Link className="nav-link" to="#">About us |</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="#">Contact |</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="#">Blog</Link>
                                </li>
                            </ul>
                        </div>
                        {
                        currentUser  ? (
                        <div className="navbar-nav ml-auto">
                            <Link to="/profile" className="nav-item nav-link">
                                <FontAwesomeIcon icon={faUser} /> {currentUser.username}
                            </Link>
                            <Link to="/login" onClick={() => logout()} className="nav-item">
                                <FontAwesomeIcon icon={faSignOutAlt} /> Log out
                            </Link>

                        </div>
                        )
                        :
                        (
                        <div className="navbar-nav ml-auto">
                            <Link to="/register" className="nav-item nav-link">
                                <FontAwesomeIcon icon={faUserPlus} /> Register
                            </Link>
                            <Link to="/login" className="nav-item nav-link">
                                <FontAwesomeIcon icon={faSignInAlt} /> Login
                            </Link>
                        </div>
                        )
                        }

                    </nav>
                
                
            </div>
        )
    
}

export default Navbar

