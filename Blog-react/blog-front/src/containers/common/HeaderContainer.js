import React from 'react';
import { useSelector, useDispatch } from 'react-redux';
import Header from '../../components/common/Header';
import { logout } from '../../modules/user';

/**
 * 로그인한 회원정보 표시를 위한 redux처리
 */
const HeaderContainer = () => {
    const { user } = useSelector(({ user }) => ({user: user.user}));
    const dispatch = useDispatch();
    
    const onLogout = () => {
        dispatch(logout());
    }

    return <Header user={user} onLogout={onLogout} />;
}

export default HeaderContainer;