import React from 'react'
import AuthTamplate from '../components/Auth/AuthTamplate';
import AuthForm from '../components/Auth/AuthForm';

const RegistePage = () => {
    return (
        <AuthTamplate>
            <AuthForm 
                type="register"
            />
        </AuthTamplate>
    );
}

export default RegistePage;