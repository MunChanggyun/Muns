import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeForm, initialze, login } from '../../modules/auth';
import AuthForm from '../../components/Auth/AuthForm';
import { withRouter } from 'react-router-dom';
import { check } from '../../modules/user';

const LoginContainer = ({ history }) => {
    const dispatch = useDispatch();
    const [error, setError] = useState(null);
    const { form, auth, authError, user } = useSelector(({ auth, user }) => ({
        form: auth.login,
        auth: auth.auth, 
        authError: auth.authError, 
        user: user.user
    }));

    // input chnage
    const onChange = e => {
        const {value, name} = e.target;

        dispatch(changeForm({
            form: 'login',
            key: name,
            value
        }))
    }

    const onSubmit = e => {
        e.preventDefault();
        
        const { userId, password } = form;

        dispatch(login({userId, password}));
    }

    // input init
    useEffect(() => {
        dispatch(initialze('login'));
    },[dispatch]);

    useEffect(() => {
        if (authError) {
            //alert('로그인중 오류가 발생했습니다.');
            setError("로그인 실패");
            return;
        }

        if (auth) {
            // alert('로그인 성공');
            dispatch(check());
            //dispatch(initialze('login'));
        }
    },[auth, authError, dispatch]);

    useEffect(() => {
        if (user) {
            history.push("/post");
        }
    }, [user, dispatch]);

    return (
        <AuthForm 
            type='login'
            form={form}
            onChange={onChange}
            onSubmit={onSubmit}
            error={error}
        />
    );
}

export default withRouter(LoginContainer);




