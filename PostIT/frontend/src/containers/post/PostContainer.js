import React, { useEffect, useState, memo, useCallback } from 'react';
import styled from 'styled-components';
import palette, { randomPalette } from '../../lib/Palette';
import Post from '../../components/post/Post';
import { withRouter } from 'react-router-dom';
import { postList, updatePost, insertPost, deletePost, changeColor } from '../../modules/posts';
import { useDispatch, useSelector } from 'react-redux';

const StyledDiv = styled.div`
    background: ${palette.green[0]};
    height: calc(100vh - 60px);
`;

const StyledButtonImg = styled.img`
    margin-left: 1rem;
    width: 3rem;
    heigth: 3rem;
`;

const StyledWrapDiv = styled.div`
    position: absolute;
`;

const PostContainer = ({ history }) => {
    const dispatch = useDispatch();

    const { posts, error, loading, user} = useSelector(({posts, loading, user}) =>({
            posts: posts.posts,
            error: posts.error,
            loading: loading['post/LIST'],
            user: user.user
    }));

    const onClick = e => {
        const ramdomColor =  randomPalette();

        const newPost = {
            top: Math.floor(Math.random() * 900),
            left: Math.floor(Math.random() * 1000),
            color: ramdomColor
        }

        dispatch(insertPost(newPost));
        dispatch(postList());
    }

    const onFocusOut = (index) => e => {
        let udtPost = posts.map((post, idx) => {
            if (index === idx) {
                return {...post, [e.target.name]: e.target.value};
            }
        })
        .filter((post, idx) => index === idx);

        const {_id, title, content, top, left} = udtPost[0];

        dispatch(updatePost({_id, title, content, top, left}));
        dispatch(postList());
    }

    const onRemove = (index) => e => {
        let udtPost = posts.map((post, idx) => {
            if (index === idx) {
                return {...post, [e.target.name]: e.target.value};
            }
        })
        .filter((post, idx) => index === idx);

        const {_id} = udtPost[0];

        dispatch(deletePost({_id}));
        dispatch(postList());
    }

    const onChangeColor = (index) => e => {
        let udtPost = posts.map((post, idx) => {
            if (index === idx) {
                return {...post, [e.target.name]: e.target.value};
            }
        })
        .filter((post, idx) => index === idx);

        const {_id} = udtPost[0];

        let ramdomColor = randomPalette();

        let isSameColor = true;

        while (isSameColor) {
            

            if (udtPost[0].color !== ramdomColor) {
                isSameColor = false;
            } 

            ramdomColor = randomPalette();
        }

        if (!isSameColor) {
            dispatch(changeColor({_id, color: ramdomColor}));
            dispatch(postList());
        }

    }

    useEffect(() => {
        if (user === null) {
            history.push("/");
        }

        dispatch(postList());
    },[user, dispatch]);
        
    return (
        <>
            <StyledDiv id="wrap">
                <StyledButtonImg onClick={onClick} src="/images/plus.png"/>
                <StyledWrapDiv>
                    {posts && posts.map((post, index) => (
                        <Post 
                            key={post._id} 
                            post={post}
                            onBlur={onFocusOut(index)}
                            onDelete={onRemove(index)}
                            onChangeColor={onChangeColor(index)}
                        />
                    ))}
                </StyledWrapDiv>
            </StyledDiv>
        </>
    );
};

export default withRouter(PostContainer);