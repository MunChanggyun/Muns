import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palette';
import Rsponsive from '../common/Responsive';
import Tags from '../common/Tags';
import SubInfo from '../common/SubInfo';

const PostViewerBlock = styled(Rsponsive)`
    margin-top: 4rem;
`;

const PostHead = styled.div`
    border-bottom: 1px solid ${palette.gray[2]};
    padding-bottom: 3rem;
    margin-bottom: 3rem;

    h1 {
        fron-size: 3rem;
        line-height: 1.5;
        margin: 0;
    }
`;

const PostContent = styled.div`
    font-size: 1.3125rem;
    color: ${palette.gray[8]};
`;

const PostViewer = ({ post, loading, error, actionButtons }) => {
    if (error) {
        if (error.response && error.response.status === 400) {
            return <PostViewerBlock>존재하지 않는 포스트 입니다.</PostViewerBlock>
        }
        return <PostViewerBlock>유효하지 않은 포스트 입니다.</PostViewerBlock>
    }

    if (loading || !post) {
        return null;
    }

    const { title, body, user, publishedDate, tags } = post;


    return (
        <PostViewerBlock>
            <PostHead>
                <h1>{title}</h1>
                <SubInfo  username={user.username} publishedDate={publishedDate} hasMarginTop/>
                <Tags tags={tags} />
            </PostHead>
            {actionButtons}
            <PostContent 
                dangerouslySetInnerHTML={{ __html: body}}
            />
        </PostViewerBlock>
    );
}

export default PostViewer;