import React from 'react';
import HeaderContainer from '../containers/common/HeaderContainer';
import PostTemplate from '../components/post/PostTemplate';
import PostContainer from '../containers/post/PostContainer';

const postPage = () => {
    return (
        <>
            <HeaderContainer />
            <PostContainer />
        </>
    );
}

export default postPage;