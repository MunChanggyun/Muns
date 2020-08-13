import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector} from 'react-redux';
import { changeField, initializeForm, register } from '../../modules/auth';
import AuthForm from '../../components/auth/AuthForm'; 
import { check }  from '../../modules/user';
import { withRouter } from 'react-router-dom';  // 회원가입 성공시 페이지 이동
import qs from 'qs';

const RegisterForm = ({ history }) => {
    const dispatch = useDispatch();
    const [error, setError] = useState(null);
    const { form, auth, authError, user } = useSelector(({ auth, user }) => ({   // store 에서 state를 가져오는 기능
        form: auth.register,
        auth: auth.auth,
        authError : auth.authError,
        user: user.user
    })); 

     // 인풋 변경 이벤트 핸들러
     const onChange = e => {
        const { value, name } = e.target;

        dispatch(
            changeField({
                form: 'register',
                key: name,
                value
            })
        );
    };

    // 폼 등록 이벤트 핸들러
    const onSubmit = e => {
        e.preventDefault();
        
        const { username, password, passwordConfirm } = form;

        if ([username, password, passwordConfirm].includes('')) {
            setError('회원정보를 모두 입력해 주세요');
            return;
        }

        if (password !== passwordConfirm) {
            setError('비밀번호가 일치하지 않습니다.');
            dispatch(changeField({form: 'register', key: 'password', value:''}));
            dispatch(changeField({form: 'register', key: 'passwordConfirm', value:''}));
            return;
        }

        dispatch(register({ username, password }));
    };

    // 컴포넌트가 처음 렌더링 될 때 form을 초기화
    useEffect(() => {
        dispatch(initializeForm('register'));
    }, [dispatch]);

    // 회원가입 성공/ 실패처리
    useEffect(() => {
        if (authError) {
            if (authError.response.status === 409) {
                setError('이미 존재하는 계정입니다.');
                return;
            }

            setError("회원가입 실패");
            return;
        }

        if (auth) {
            console.log("회원가입 성공", auth);
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
            type='register'
            form={form}
            onChange={onChange}
            onSubmit={onSubmit}
            error={error}
        />
    );
};

export default withRouter(RegisterForm);