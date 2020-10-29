import React, { useState } from 'react';
import styled from 'styled-components';
import palette from '../../lib/Palette';
import Post from './Post';

const StyledDiv = styled.div`
    background: ${palette.green[0]};
    maring-top: 3rem;
`;

const StyledButtonImg = styled.img`
    margin-left: 1rem;
    width: 3rem;
    heigth: 3rem;
`;

const StyledWrapDiv = styled.div`
    position: relative;
`;

const PostTemplate = ({ children }) => {
    const [posts, setPosts ] = useState([]);

    const onClick = e => {
        const newPost = {
            title: 'test title',
            content: 'test content',
            publishedDate: '',
            top: 30,
            left: 30
        }

        setPosts([...posts, newPost]);
    }

    return (
        <>
            <StyledDiv>
                <StyledButtonImg onClick={onClick} src="/images/plus.png"/>
                <StyledWrapDiv>
                    {posts && posts.map((post, index) => (
                        <Post 
                            key={index} 
                            title={post.title}
                            content={post.content} 
                            publishedDate={post.publishedDate} 
                            top={post.top}
                            left={post.left}
                        />
                    ))}
                </StyledWrapDiv>
            </StyledDiv>
        </>
    );
}

export default PostTemplate;