import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, { createRequestActionType } from '../lib/createRequestSaga';
import { takeLatest } from 'redux-saga/effects';
import * as postAPI from '../api/post';

const [LIST, LIST_SUCCESS, LIST_FAILURE] = createRequestActionType("post/LIST");
const [UPDATE, UPDATE_SUCCESS, UPDATE_FAILURE] = createRequestActionType("post/UPDATE");
const [INSERT, INSERT_SUCCESS, INSERT_FAILURE] = createRequestActionType("post/INSERT");
const [DELETE, DELETE_SUCCESS, DELETE_FAILURE] = createRequestActionType("post/DELETE");
const [POSITION, POSITION_SUCCESS, POSITION_FAILURE] = createRequestActionType("post/POSITION");
const [COLOR, COLOR_SUCCESS, COLOR_FAILURE] = createRequestActionType("post/COLOR");

export const postList = createAction(LIST);
export const updatePost = createAction(UPDATE, ({ _id, title, content, top, left }) => 
({_id, title, content, top, left}));
export const insertPost = createAction(INSERT, ({top, left, color}) => ({top, left, color}));
export const deletePost = createAction(DELETE, ({ _id }) => 
({_id}));
export const movePost = createAction(POSITION, ({ id, top, left }) =>
({ id, top, left }));
export const changeColor = createAction(COLOR, ({ _id, color}) => ({ _id, color}));

const postListSaga = createRequestSaga(LIST, postAPI.list);
const postUpdateSaga = createRequestSaga(UPDATE, postAPI.update);
const postInsertSaga = createRequestSaga(INSERT, postAPI.insert);
const postDeleteSaga = createRequestSaga(DELETE, postAPI.remove);
const postMoveSaga = createRequestSaga(POSITION, postAPI.position);
const postChangeColorSaga = createRequestSaga(COLOR, postAPI.changeColor);

export function* PostSaga() {
    yield takeLatest(LIST, postListSaga);
    yield takeLatest(UPDATE, postUpdateSaga);
    yield takeLatest(INSERT, postInsertSaga);
    yield takeLatest(DELETE, postDeleteSaga);
    yield takeLatest(POSITION, postMoveSaga);
    yield takeLatest(COLOR, postChangeColorSaga);
};

const initState = {
    posts: [],
    postError : null
}

const posts = handleActions({
    [LIST_SUCCESS]: (state, {payload: posts}) => ({
        ...state,
        posts,
        postError: null
    }),
    [LIST_FAILURE]: (state, {payload: error}) => ({
        ...state,
        posts: null,
        postError: error
    }),
    [UPDATE_SUCCESS]: (state, {payload : post}) => ({
        ...state,
        postError: null
    }),
    [UPDATE_FAILURE]: (state, {payload: error}) => ({
        ...state,
        ...posts,
        postError: error
    }),
    [INSERT_SUCCESS]: (state, {payload: post}) => ({
        ...state, 
        posts: state.posts.concat(post),
        postError: null
    }),
    [INSERT_FAILURE]: (state ,{payload: error}) => ({
        ...state,
        postError: error
    }),
    [DELETE_SUCCESS]: (state) => ({
        ...state,
        postError: null
    }),
    [DELETE_FAILURE]: (state, {payload: error}) => ({
        ...state,
        postError: error
    }),
    // [POSITION_SUCCESS]: (state, { payload: post }) => ({
    //     ...state,
    //     posts: [...posts, post]
    // }),
    // [POSITION_FAILURE]: (state, { payload: error }) => ({
    //     ...state,
    //     postError: error
    // })

    // posts: [...posts, posts.map(ps => { 
    //     if (ps._id === post._id ) {
    //         return {...ps, post}   
    //     }
    // })],
    // posts: state.posts.map(post => post._id === checkPost._id ? 
    //     {...post, color: checkPost.color} : post),
    // [COLOR_SUCCESS]: (state, {payload: checkPost}) => ({
    //     ...state,
    //     posts: state.posts.map(post => {
    //         return {...post, color: checkPost.color};
    //     }),
    //     postError: null
    // }),
    // [COLOR_FAILURE]: (state, {payload: error}) => ({
    //     ...state,
    //     postError: error
    // })

}, initState);

export default posts;