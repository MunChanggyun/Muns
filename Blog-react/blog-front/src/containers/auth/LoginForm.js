import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField, initializeForm, login } from '../../modules/auth';
import AuthForm from '../../components/auth/AuthForm';
import { withRouter } from 'react-router-dom';
import { check } from '../../modules/user';
import qs from 'qs';

const LoginForm = ({ history }) => {
    const dispatch = useDispatch();
    const [error, setError] = useState(null);
    const { form, auth, authError, user } = useSelector(({ auth, user }) => ({       // modules/auth 파일의 initalState 정보를 받아온다.
        form: auth.login,
        auth: auth.auth,
        authError : auth.authError,
        user: user.user
    }));

    // 인풋 변경 이벤트 핸들러
    const onChange = e => {
        const { value, name } = e.target;

        dispatch(
            changeField({
                form: 'login',
                key: name,
                value
            })
        );
    };

    // 폼 등록 이벤트 핸들러
    const onSubmit = e => {
        e.preventDefault();
        
        const { username, password } = form;

        dispatch(login({ username, password }));
    };

    // 컴포넌트가 처음 렌더링 될 때 form을 초기화
    useEffect(() => {
        dispatch(initializeForm('login'));
    }, [dispatch]);

    // 로그인 성공/ 실패처리
    useEffect(() => {
        if (authError) {
            console.log("오류발생 :: ", authError);
            setError("로그인 실패");
            return;
        }

        if (auth) {
            console.log("로그인 성공", auth);
            dispatch(check());
        }
    }, [auth, authError, dispatch]);

    
    useEffect(() => {
        if (user) { 
            history.push('/');  // 홈화면으로 이동

            // 로그인 상태 유지
            try {
                localStorage.setItem('user', qs.stringify(user));
            } catch (e) {
                console.log("localStorage is not working");
            }
        }
    }, [history, user]);

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

export default withRouter(LoginForm);