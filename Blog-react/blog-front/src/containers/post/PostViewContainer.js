import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { readPost, unloadPost} from '../../modules/post';
import PostViewer from '../../components/post/PostViewer';
import PostActionButtons from '../../components/post/PostActionButtons';
import { setOriginalPost } from '../../modules/write';
import { removePost } from '../../lib/api/posts';

const PostViewContainer = ({ match, history }) => {
    const { postId } = match.params;

    const dispatch = useDispatch();

    const { post, error, loading, user } = useSelector(({ post, loading, user }) => ({
        post: post.post,
        error: post.error,
        loading: loading['post/RAED_POST'],
        user: user.user
    }));

    const onEdit = () => {
        dispatch(setOriginalPost(post));
        history.push('/write');
    }

    const onRemove = async () => {
        try {
            await removePost(postId);
            history.push('/');
        } catch (e) {
            console.log(e);
        }
    };

    const ownPost = (user && user._id) === (post && post.user._id);

    useEffect(() => {
        dispatch(readPost(postId));

        return () => {
            dispatch(unloadPost());
        }
    }, [dispatch, postId]);

    return <PostViewer post={post} loading={loading} error={error} actionButtons={<PostActionButtons onEdit={onEdit} onRemove={onRemove}/>}/>
}

export default withRouter(PostViewContainer);