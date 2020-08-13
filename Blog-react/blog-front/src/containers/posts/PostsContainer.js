import React,{useEffect} from 'react';
import { withRouter } from 'react-router-dom';
import PostList from '../../components/posts/PostList';
import { listPosts } from '../../modules/posts';
import { useDispatch, useSelector } from 'react-redux';
import qs from 'qs';

const PostsContainer = ({ location }) => {
    const dispatch = useDispatch();
    const { posts, error, loading, user} = useSelector(({ posts, loading, user}) => ({
        posts: posts.posts,
        error: posts.error,
        loading: loading['posts/LIST_POST'],
        user: user.user
    }));

    useEffect(() => {
        const { tag, username, page } = qs.parse(location.search, {
            ignoreQeuryPrefix: true
        });
        dispatch(listPosts({tag, username, page}));
    }, [dispatch, location.search]);

    return (
        <PostList 
            loading={loading}
            error={error}
            posts={posts}
            showWriteButton={user}
        />
    );
}

export default withRouter(PostsContainer);