import React, {useEffect} from 'react';
import { withRouter} from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import Header from '../../components/common/Header';
import { logout } from '../../modules/user' ;
import { initialze } from '../../modules/auth';
import { randomPalette } from '../../lib/Palette';
import { insertPost } from '../../modules/posts';

const HeaderContainer = ({history}) => {
    const { user } = useSelector(({ user }) => ({
        user: user.user
    }));

    const dispatch = useDispatch();

    const onLogout = () => {
        
        dispatch(logout());
        dispatch(initialze());
        history.push("/");
    };

    const onAddPost = e => {
        const ramdomColor =  randomPalette();

        const newPost = {
            top: Math.floor(Math.random() * 900),
            left: Math.floor(Math.random() * 1000),
            color: ramdomColor
        }

        dispatch(insertPost(newPost));
    }

    return (
        <>
            <Header user={user} onLogout={onLogout} onAddPost={onAddPost}/>
        </>
    );
}

export default withRouter(HeaderContainer);
