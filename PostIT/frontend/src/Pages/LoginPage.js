import React from 'react';
import LoginContainer from '../containers/auth/LoginContainer';
import AuthTamplate from '../components/Auth/AuthTamplate';

const LoginPage = () => {
    return (
        <AuthTamplate>
            <LoginContainer/>
        </AuthTamplate>
    );
}

export default LoginPage;